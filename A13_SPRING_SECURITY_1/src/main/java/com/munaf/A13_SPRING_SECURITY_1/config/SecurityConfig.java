package com.munaf.A13_SPRING_SECURITY_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/posts").permitAll()
                    .requestMatchers("admin/**", "/posts/**").hasAnyRole("ADMIN", "MANAGER")
                    .anyRequest().authenticated()
                )
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }


    // creating in memory user for testing
    @Bean
    UserDetailsService getUser() {
        UserDetails adminUser = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN", "MANAGER")
                .build();

        UserDetails normalUser = User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }


    // creating password encoder
    @Bean
    PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

}
