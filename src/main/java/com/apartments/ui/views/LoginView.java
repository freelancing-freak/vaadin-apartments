package com.apartments.ui.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Logowowanie | AP")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);
        login.setI18n(createLoginI18n());

        VerticalLayout header = new VerticalLayout();
        header.add(new H1("Apartamenty Admin Panel"));
        header.setAlignItems(Alignment.CENTER);

        add(header, login);
    }

    private LoginI18n createLoginI18n(){
        LoginI18n i18n = LoginI18n.createDefault();

        LoginI18n.Form form = i18n.getForm();
        form.setTitle("Logowanie");
        form.setUsername("Nazwa użytkownika");
        form.setPassword("Hasło");
        form.setSubmit("Zaloguj");

        LoginI18n.ErrorMessage errorMessage = i18n.getErrorMessage();
        errorMessage.setTitle("Błędne dane");
        errorMessage.setMessage("Niepoprawna nazwa użytkownika lub hasło");

        return i18n;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
