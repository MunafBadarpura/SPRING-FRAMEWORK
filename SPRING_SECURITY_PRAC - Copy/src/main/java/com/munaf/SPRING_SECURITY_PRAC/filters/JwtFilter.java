package com.munaf.SPRING_SECURITY_PRAC.filters;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.UserRepository;
import com.munaf.SPRING_SECURITY_PRAC.services.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtFilter(JwtService jwtService, UserRepository userRepository, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestHeader = request.getHeader("Authorization");
            if (requestHeader == null || !requestHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwtToken = requestHeader.split("Bearer ")[1];
            String tokenType = jwtService.getTokenType(jwtToken);
            if (!"ACCESS".equals(tokenType)) {
                throw new JwtException("Invalid token type: " + tokenType + ". ACCESS token required.");
            }
            Long userId = jwtService.getUserIdFromJwtToken(jwtToken);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity userEntity = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User id not found with Id : " + userId));

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            filterChain.doFilter(request, response);
        }catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }

    }

}


/*
STEPS :
1. Take Authorization Header : final String requestHeader = request.getHeader("Authorization");
2. Check requestHeader is not empty and start with Bearer
    : if (requestHeader == null && !requestHeader.startsWith("Bearer")) filterChain.doFilter(request, response); return;
3. Extract Jwt Token from header : String jwtToken = requestHeader.split("Bearer ")[1];
4. Get user id from token : Long userId = jwtService.getUserIdFromJwtToken(jwtToken);
5. Check userId is not null and context holder is null
    : if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null)
6. Get userEntity from id : UserEntity userEntity = userRepository.findById(userId);
7. Create UsernamePasswordAuthenticationToken
    : UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity, null, null);
8. Set details in usernamePasswordAuthenticationToken
    :  usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
9. Set SecurityContextHolder : SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
*/