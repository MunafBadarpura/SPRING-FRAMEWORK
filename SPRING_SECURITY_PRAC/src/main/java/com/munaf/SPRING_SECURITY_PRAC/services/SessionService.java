package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.entities.Session;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.SessionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepo sessionRepo;

    public void generateSession(UserEntity userEntity, String refreshToken) {
        List<Session> userSessions = sessionRepo.findByUserEntity(userEntity);
        if (userSessions.size() == 2) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));
            Session oldestSession = userSessions.getFirst();
            sessionRepo.delete(oldestSession);
        }

        Session userSession = Session.builder()
                .refreshToken(refreshToken)
                .lastUsedAt(LocalDateTime.now())
                .userEntity(userEntity)
                .build();
        sessionRepo.save(userSession);
    }


    public void validateSession(String refreshToken) {
        Session session = sessionRepo.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session Not Available For RefreshToken : "+refreshToken));

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepo.save(session);
    }


    public String deleteSession(String refreshToken) {
        Session sessionToDelete = sessionRepo.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session Not Valid For This RefreshToken " + refreshToken));
        sessionRepo.delete(sessionToDelete);
        return "LOG OUT DONE";
    }

}
