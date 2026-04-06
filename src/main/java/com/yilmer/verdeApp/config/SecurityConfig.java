package com.yilmer.verdeApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. La portería es pública (Cualquiera se registra)
                        .requestMatchers("/api/auth/**").permitAll()
                        // 2. Swagger es público (Para ver la documentación)
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        // 3. TODO lo demás (Conjuntos, Unidades, Gestión) requiere identificación
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // El navegador te pedirá usuario y contraseña

        return http.build();
    }
}