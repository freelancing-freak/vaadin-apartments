package com.apartments.ui.views.notifications;

import com.apartments.service.notifications.Notification;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

public class NotificationForm extends FormLayout {

    private Notification notification;

    TextField apartmentId = new TextField("Id apartamentu");
    TextField name = new TextField("Nazwa");
    TextField subject = new TextField("Temat");
    TextField phoneNumber = new TextField("Numer telefonu");
    TextArea message = new TextArea("Wiadomość");
    TextField type = new TextField("Typ");
    TextArea errorTrace = new TextArea("Treść błędu");
    TextField isSent = new TextField("Status wysłania");

    Binder<Notification> binder = new Binder<>(Notification.class);

    Button delete = new Button("Usuń");
    Button close = new Button("Zamknij");

    public NotificationForm() {
        addClassName("notification-form");
        binder.bindInstanceFields(this);

        add(apartmentId, name, subject, phoneNumber, message, type, errorTrace, isSent, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickShortcut(Key.ESCAPE);

        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, notification)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(delete, close);
    }

    public void setNotificationBean(Notification notification) {
        this.notification = notification;
        binder.readBean(notification);
    }

    @Getter
    public static abstract class NotificationFormEvent extends ComponentEvent<NotificationForm> {

        private final Notification notification;

        protected NotificationFormEvent(NotificationForm source, Notification notification) {
            super(source, false);
            this.notification = notification;
        }
    }

    public static class DeleteEvent extends NotificationFormEvent {
        DeleteEvent(NotificationForm source, Notification notification) {
            super(source, notification);
        }

    }

    public static class CloseEvent extends NotificationFormEvent {
        CloseEvent(NotificationForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
