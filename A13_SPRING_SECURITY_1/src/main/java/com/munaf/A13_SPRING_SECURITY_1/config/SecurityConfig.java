package com.munaf.A13_SPRING_SECURITY_1.config;

import com.munaf.A13_SPRING_SECURITY_1.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/error/**", "/auth/**").permitAll()
//                  .requestMatchers("/admin/**", "/posts/**").hasAnyRole("ADMIN", "MANAGER")
                    .anyRequest().authenticated())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//              .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
