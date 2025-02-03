package com.munaf.SPRING_SECURITY_PRAC.config;

import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permission;
import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role;
import com.munaf.SPRING_SECURITY_PRAC.filters.JwtFilter;
import com.munaf.SPRING_SECURITY_PRAC.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permission.*;
import static com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] publicRoutes = {"/auth/**", "/error", "/success"};

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrfConfigurer) -> csrfConfigurer.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(publicRoutes).permitAll()

                    .requestMatchers("post/**").hasAnyRole(ADMIN.name())
                    .requestMatchers(HttpMethod.GET,"post/**").hasAnyAuthority(POST_VIEW.name())
                    .requestMatchers(HttpMethod.POST, "post/**").hasAnyAuthority(POST_CREATE.name())
                    .requestMatchers(HttpMethod.PUT, "post/**").hasAnyAuthority(POST_UPDATE.name())
                    .requestMatchers(HttpMethod.DELETE, "post/**").hasAnyAuthority(POST_DELETE.name())

                    .requestMatchers("user/**").hasRole(ADMIN.name())

                    .anyRequest().authenticated()
                )
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2SuccessHandler)
                        .failureUrl("/login?error=true")
                )
                .build();
    }

}
