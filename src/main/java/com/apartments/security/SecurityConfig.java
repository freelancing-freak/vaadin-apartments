package com.apartments.security;

import com.apartments.ui.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends VaadinWebSecurity {

    private static class ApartmentsInMemoryUserDetailsManager extends InMemoryUserDetailsManager {

        public ApartmentsInMemoryUserDetailsManager(String username, String password) {
            createUser(new User(username, "{noop}" + password, Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        // Set default security policy that permits Vaadin internal requests and
        // denies all other
        super.configure(http);
        setLoginView(http, LoginView.class, "/logout");
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(@Value("${admin.username}") String username,
                                                         @Value("${admin.password}") String password) {
        return new ApartmentsInMemoryUserDetailsManager(username, password);
    }
}
