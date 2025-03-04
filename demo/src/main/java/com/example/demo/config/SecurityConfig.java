package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                // Desactivamos CSRF porque es una API REST
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Permitimos acceso a todos a nuestra API de saludos
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/saludo/**").permitAll()
                        // Para cualquier otra ruta, requiere autenticación
                        .anyExchange().authenticated()
                )
                // Configuración para APIs: usamos autenticación básica HTTP
                .httpBasic(httpBasic -> {})
                .build();
    }
}