// package com.example.demo.config;

// import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
// import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
// import org.keycloak.adapters.springsecurity.session.RegisterSessionAuthenticationStrategy;
// import org.keycloak.adapters.springsecurity.session.SessionAuthenticationStrategy;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.session.SessionRegistry;
// import org.springframework.security.core.session.SessionRegistryImpl;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

//     /**
//      * Registers the KeycloakAuthenticationProvider with the authentication manager.
//      */
//     @Autowired
//     public void configureGlobal(AuthenticationManager auth) throws Exception {
//         KeycloakAuthenticationProvider keycloakAuthenticationProvider = new KeycloakAuthenticationProvider();
//         auth.authenticationProvider(keycloakAuthenticationProvider);
//     }

//     /**
//      * Defines the session authentication strategy.
//      */
//     @Override
//     protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//         return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
//     }

//     @Bean
//     public SessionRegistry buildSessionRegistry() {
//         return new SessionRegistryImpl();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         super.configure(http);
//         http
//             .authorizeRequests()
//                 .antMatchers("/customers*").hasRole("USER")
//                 .antMatchers("/admin*").hasRole("ADMIN")
//                 .anyRequest().permitAll();
//     }

//     /**
//      * Configure WebSecurity to allow Keycloak to work correctly.
//      */
//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         // Add any exclusion for static resources if necessary
//         web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//     }
// }
