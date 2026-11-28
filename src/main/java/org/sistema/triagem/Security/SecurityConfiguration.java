package org.sistema.triagem.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CorsConfigurationSource corsConfigurationSource
    ) throws Exception {

        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .csrf(csrf -> csrf.disable())

                .sessionManagement(sessions ->
                        sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/func").permitAll()
                        .requestMatchers(HttpMethod.GET, "/func/nome").authenticated()
                        .requestMatchers(HttpMethod.POST, "/endfunc").permitAll()
                        .requestMatchers(HttpMethod.POST, "/telfunc").permitAll()

                        .requestMatchers(HttpMethod.POST, "/endpac").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pac").permitAll()
                        .requestMatchers(HttpMethod.POST, "/telpac").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pac/perfil").authenticated()
                        .requestMatchers(HttpMethod.POST, "/pac/perfil/find").authenticated()
                        .requestMatchers(HttpMethod.GET, "/pac/**").permitAll()


                        .requestMatchers(HttpMethod.POST, "/med").permitAll()
                        .requestMatchers(HttpMethod.GET, "/med/buscar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/med/perfil").permitAll()

                        .requestMatchers(HttpMethod.GET, "/consulta/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/consulta").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/consulta/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/consulta/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/find/**").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/users").permitAll()

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        securityFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}