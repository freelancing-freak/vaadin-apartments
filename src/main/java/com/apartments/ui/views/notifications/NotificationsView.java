package com.apartments.ui.views.notifications;

import com.apartments.service.notifications.Notification;
import com.apartments.service.notifications.NotificationService;
import com.apartments.ui.UINotifications;
import com.apartments.ui.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import java.util.Objects;

@Component
@Scope("prototype")
@Route(value = "notifications", layout = MainLayout.class)
@PageTitle("Notyfikacje | AP")
@PermitAll
public class NotificationsView extends VerticalLayout {

    Grid<Notification> grid = new Grid<>(Notification.class);
    TextField filterText = new TextField();
    NotificationForm form;

    private final NotificationService service;

    public NotificationsView(NotificationService service) {
        this.service = service;

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new NotificationForm();
        form.setWidth("25em");
        form.addListener(NotificationForm.DeleteEvent.class, this::delete);
        form.addListener(NotificationForm.CloseEvent.class, e -> closeEditor());

        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event -> editNotification(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("notification-grid");
        grid.setSizeFull();
        grid.setColumns("id", "createdDate", "subject");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtrowanie ...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void delete(NotificationForm.DeleteEvent event) {
        Notification notification = event.getNotification();
        if (Objects.isNull(notification.getId())) {
            return;
        }
        try {
            service.delete(notification.getId());
            UINotifications.success("Usunięto notyfikację o id: " + notification.getId());
            updateList();
            closeEditor();
        } catch (Exception e) {
            UINotifications.error(e.getMessage());
        }
    }

    public void editNotification(Notification notification) {
        if (notification == null) {
            closeEditor();
        } else {
            form.setNotificationBean(notification);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setNotificationBean(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        try {
            grid.setItems(service.findAll(filterText.getValue()));
        } catch (Exception e) {
            UINotifications.error("Nie udało się wczytać notyfikacji");
        }
    }
}
