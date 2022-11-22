package com.apartments.ui.views;

import com.apartments.security.SecurityService;
import com.apartments.ui.views.apartments.ApartmentsView;
import com.apartments.ui.views.notifications.NotificationsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Apartamenty Admin Panel");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Wyloguj się", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink apartmentsLink = new RouterLink("Apartamenty", ApartmentsView.class);
        RouterLink notificationsLink = new RouterLink("Notyfikacje", NotificationsView.class);

        apartmentsLink.setHighlightCondition(HighlightConditions.sameLocation());
        notificationsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                new RouterLink("Dashboard", DashboardView.class),
                apartmentsLink,
                notificationsLink
        ));
    }
}
