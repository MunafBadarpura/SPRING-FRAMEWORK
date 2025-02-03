package com.munaf.A13_SPRING_SECURITY_1.services;

import com.munaf.A13_SPRING_SECURITY_1.entity.Session;
import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final Integer SESSION_LIMIT = 2;

    public void generateSession(User user, String refreshToken) {
        List<Session> userSessions = sessionRepository.findByUser(user);
        if (userSessions.size() >= SESSION_LIMIT) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session oldestSession = userSessions.getFirst();
            sessionRepository.delete(oldestSession);
        }

        Session newSession = Session.builder()
                .refreshToken(refreshToken)
                .user(user)
                .lastUsedAt(LocalDateTime.now())
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session Not Valid For This RefreshToken " + refreshToken));

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

    public String deleteSession(String refreshToken) {
        Session sessionToDelete = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session Not Valid For This RefreshToken " + refreshToken));
        sessionRepository.delete(sessionToDelete);
        return "LOG OUT DONE";
    }
}
