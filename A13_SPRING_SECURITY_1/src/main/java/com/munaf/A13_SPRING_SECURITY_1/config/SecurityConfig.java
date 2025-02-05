package com.munaf.A13_SPRING_SECURITY_1.config;

import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Permissions;
import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Role;
import com.munaf.A13_SPRING_SECURITY_1.filters.JwtAuthFilter;
import com.munaf.A13_SPRING_SECURITY_1.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Permission;

/*
1. Form Login :
    httpSecurity
    .formLogin()
    .loginPage("/custom-login") // Custom login page URL
    .loginProcessingUrl("/process-login") // URL for login form submission
    .defaultSuccessUrl("/home", true) // Redirect after successful login
    .failureUrl("/login?error=true") // Redirect after login failure
    .permitAll(); // Allow everyone access to the login page

2. Disable CSRF Protection :
httpSecurity
    .csrf()
    .disable(); // Disables CSRF protection (not recommended for production)

3. Authorization :
httpSecurity
    .authorizeRequests()
    .antMatchers("/public/**").permitAll() // Public endpoints
    .antMatchers("/admin/**,/public/**").hasRole("ADMIN") // Requires ADMIN role
    .anyRequest().authenticated(); // All other requests require authentication

4.Enables CORS support
httpSecurity
    .cors(); // Enables CORS support

5.Example Code
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/public/**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
        .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard", true)
            .permitAll()
        .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .permitAll();
    return http.build();
}

* */


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] publicRoutes = {"/error/**", "/auth/**", "/posts/homepage"};


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())

                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(publicRoutes).permitAll()
                    .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyRole(Role.ADMIN.name(), Role.CREATOR.name())
                    .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyAuthority(Permissions.POST_CREATE.name())
                    .requestMatchers(HttpMethod.PUT,"/posts/**").hasAnyAuthority(Permissions.POST_UPDATE.name())
                    .requestMatchers(HttpMethod.DELETE,"/posts/**").hasAnyAuthority(Permissions.POST_DELETE.name())
                    .anyRequest().authenticated())

                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );
//              .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
