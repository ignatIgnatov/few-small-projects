package com.api.server_01.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.api.server_01.entity.enums.Permission.ADMIN_CREATE;
import static com.api.server_01.entity.enums.Permission.ADMIN_READ;
import static com.api.server_01.entity.enums.Permission.ADMIN_DELETE;
import static com.api.server_01.entity.enums.Permission.ADMIN_UPDATE;
import static com.api.server_01.entity.enums.RoleEntity.ADMIN;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/**").permitAll()
//                            .requestMatchers(GET, "/api/admin/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
//                            .requestMatchers(POST, "/api/admin/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
//                            .requestMatchers(PUT, "/api/admin/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//                            .requestMatchers(DELETE, "/api/admin/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

//                            .requestMatchers("/api/admin/**").hasRole(ADMIN.name())
//
//                            .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                            .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//                            .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                            .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                            .anyRequest()
                            .authenticated();
                })

                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })

                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> {
                    logout
                            .logoutUrl("/logout")
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

                });


        return http.build();
    }
}
