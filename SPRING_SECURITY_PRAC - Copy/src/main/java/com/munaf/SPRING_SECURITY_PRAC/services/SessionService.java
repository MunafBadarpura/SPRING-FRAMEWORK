package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.entities.Session;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.SessionRepository;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class SessionService {

    private final int SESSION_LIMIT = 2;
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public void generateNewSession(UserEntity userEntity, String refreshToken) {

        List<Session> userSessions = sessionRepository.findByUserEntity(userEntity);
        if (userSessions.size() == SESSION_LIMIT) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));
            Session oldestSession = userSessions.getFirst();

            sessionRepository.delete(oldestSession);
        }

        Session newSession = Session.builder()
                .userEntity(userEntity)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);
    }


    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session Not Available For RefreshToken : "+refreshToken));

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
