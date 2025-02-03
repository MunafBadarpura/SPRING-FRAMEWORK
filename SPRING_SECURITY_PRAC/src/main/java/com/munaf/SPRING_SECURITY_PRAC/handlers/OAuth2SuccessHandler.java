package com.munaf.SPRING_SECURITY_PRAC.handlers;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.services.JwtService;
import com.munaf.SPRING_SECURITY_PRAC.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) token.getPrincipal();

        System.out.println(defaultOAuth2User.getAttributes());
        String userName = (String) defaultOAuth2User.getAttributes().get("name");
        String userEmail = (String) defaultOAuth2User.getAttributes().get("email");
        UserEntity user = userService.getUserByEmail(userEmail);

        if (user == null) {
            UserEntity newUser = UserEntity.builder()
                    .username(userName)
                    .email(userEmail)
                    .build();
            user = userService.save(newUser);
        }

        String accessToken = jwtService.accessToken(user);
        String refreshToken = jwtService.refreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        response.sendRedirect("/success?token="+accessToken);
    }
}
