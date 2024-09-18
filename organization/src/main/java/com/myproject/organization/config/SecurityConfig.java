package com.myproject.organization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF korumasını devre dışı bırak
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Tüm istekleri güvenlikten muaf tut
                )
                .formLogin(form -> form.disable())  // Basit bir login formu (istersen disable da edebilirsin)
                .httpBasic(httpBasic -> httpBasic.disable());  // HTTP Basic Authentication'ı devre dışı bırak

        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin123")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}