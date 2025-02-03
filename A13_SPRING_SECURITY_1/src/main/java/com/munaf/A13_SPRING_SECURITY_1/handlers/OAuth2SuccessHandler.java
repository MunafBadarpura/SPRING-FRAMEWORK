package com.munaf.A13_SPRING_SECURITY_1.handlers;

import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.services.JwtService;
import com.munaf.A13_SPRING_SECURITY_1.services.UserService;
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

        String userEmail = (String) defaultOAuth2User.getAttributes().get("email");
        String userName = (String) defaultOAuth2User.getAttributes().get("name");
        User user = userService.getUserByEmail(userEmail);

        if (user == null) { // user not exist
            User newUser = User.builder()
                    .name(userName)
                    .email(userEmail)
                    .build();
             user = userService.save(newUser);
        }

        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken =  jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

//        String frontendUrl = "http://localhost:8080/posts/homepage?token="+accessToken;
//        response.sendRedirect(frontendUrl);

        response.sendRedirect("/posts/homepage?token=" + accessToken);
//        getRedirectStrategy().sendRedirect(request, response, frontendUrl);
    }
}
