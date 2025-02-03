package com.munaf.A13_SPRING_SECURITY_1.filters;

import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.services.JwtService;
import com.munaf.A13_SPRING_SECURITY_1.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String requestTokenHeader = request.getHeader("Authorization"); // Get the Token from the Request Header This header usually contains a token that looks like: "Bearer gfhsbfjhvfgdsvbfjh(token)"
            String token = null;
            Long userId = null;

            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                token = requestTokenHeader.split("Bearer ")[1]; // Extract the Token
                userId = jwtService.getUserIdFromToken(token); // Get the User ID from the Token
            }

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Check if the User ID is Valid and Authentication is Not Already Set
                User user = userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities()); // Create Authentication Object

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Set the Authentication in the Security Context
            }

            filterChain.doFilter(request, response);
        }catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}


/*
    This code is part of a filter in a Java web application
    It checks if the token is valid it sets up the user's authentication detail so that the application knows who is making the request.
* */
