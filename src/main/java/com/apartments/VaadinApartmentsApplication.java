package com.apartments;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@Theme("apartments")
@PWA(name = "Apartments", shortName = "AP", offlinePath = "offline.html", offlineResources = {"./images/offline.png"})
public class VaadinApartmentsApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(VaadinApartmentsApplication.class, args);
    }
}
