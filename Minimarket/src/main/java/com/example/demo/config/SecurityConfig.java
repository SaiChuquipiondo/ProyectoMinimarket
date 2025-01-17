package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/Ventas/Guardar").permitAll() // Permitir acceso a todos
                .anyRequest().authenticated() // Requerir autenticación para las demás rutas
        )
        .csrf().disable(); // Deshabilitar CSRF si es necesario para pruebas

        return http.build();
    }
}
