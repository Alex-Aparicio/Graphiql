package com.example.demo.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.service.AppUserDetailsService;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfigJwt {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AppUserDetailsService appUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder);

        return builder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authenticationManager(authenticationManager);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/signin", "/signup","/api/users/rol/","/api/roles", "/api/users").permitAll()

                .requestMatchers("/graphql/**", "/playground/**", "/graphiql/**").permitAll()

                .requestMatchers("/api/users/**","/api/roles/**","/api/states/**",
                        "/api/addresses/**").hasRole("ADMIN")

                .requestMatchers("/api/places/**",
                        "/api/favorites/**",
                        "/api/reviews/**",
                        "/api/reviewImages/**",
                        "/api/services/**",
                        "/api/serviceReviews/**",
                        "/api/events/**",
                        "/api/gastronomies/**",
                        "/api/transports/**",
                        "/api/stories/**",
                        "/api/legends/**",
                        "/api/municipalities/**")
                .hasAnyRole("USER", "ADMIN")

                .anyRequest().authenticated());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8081"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
