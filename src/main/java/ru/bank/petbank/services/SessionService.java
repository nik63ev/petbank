package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.petbank.DTO.AuthenticationRequest;
import ru.bank.petbank.DTO.SessionResponse;
import ru.bank.petbank.DTO.Status;
import ru.bank.petbank.model.UserCredential;
import ru.bank.petbank.model.UserSession;
import ru.bank.petbank.repository.UserCredentialRepository;
import ru.bank.petbank.repository.UserSessionRepository;

import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private final UserCredentialRepository userCredentialRepository;

    @Autowired
    private final UserSessionRepository userSessionRepository;

    @Autowired
    public SessionService( UserCredentialRepository userCredentialRepository, UserSessionRepository userSessionRepository) {
        this.userCredentialRepository = userCredentialRepository;
        this.userSessionRepository = userSessionRepository;
    }

    @Value("${session.timeout.seconds:1800}") // 30 минут по умолчанию
    private Integer sessionTimeoutSeconds;

    @Transactional
    public SessionResponse authenticate(AuthenticationRequest authenticationRequest) {
        Optional<UserCredential> userAuthenticate =
                userCredentialRepository.findByUsername(authenticationRequest.getUsername());
        SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setStatus(new Status());
        if (userAuthenticate.isPresent() &&
                authenticationRequest.getPassword().equals(userAuthenticate.get().getPassword())) {

            sessionResponse.getStatus().setMessage("Authenticate Success and Session created");
            sessionResponse.getStatus().setCode(0);
            sessionResponse.setUserId(userAuthenticate.get().getUserId());
            Long sessionToken = createSession(userAuthenticate.get().getUserId());
            sessionResponse.setSessionToken(sessionToken);
        }
        else {
            sessionResponse.getStatus().setMessage("Username or Password incorrect");
            sessionResponse.getStatus().setCode(1);
            sessionResponse.setUserId(null);
            sessionResponse.setSessionToken(null);
        }
        return sessionResponse;
    }

    @Transactional
    public Long createSession(Long userId) {
        String device = "defaults device";
        UserSession userSession = new UserSession(userId, device, sessionTimeoutSeconds);
        userSessionRepository.save(userSession);
        return userSession.getSessionToken();
    }


    @Transactional
    public SessionResponse invalidateSession(Long sessionToken) {
        SessionResponse sessionResponse = new SessionResponse();
        sessionResponse.setStatus(new Status());
        if (userSessionRepository.findBySessionToken(sessionToken).isPresent()) {
            UserSession userSession = userSessionRepository.findBySessionToken(sessionToken).get();

            sessionResponse.getStatus().setMessage("Session invalidated");
            sessionResponse.getStatus().setCode(0);
            sessionResponse.setUserId(userSession.getUserId());
            sessionResponse.setSessionToken(userSession.getSessionToken());
            userSessionRepository.delete(userSession);
        }
        else {
            sessionResponse.getStatus().setMessage("SessionToken is not found");
            sessionResponse.getStatus().setCode(1);
            sessionResponse.setUserId(null);
            sessionResponse.setSessionToken(sessionToken);
        }
        return sessionResponse;
    }
//    public boolean validateSession(String sessionId) {
//        UserSession session = sessions.get(sessionId);
//        return session != null && !session.isExpired();
//    }
//
//    public UserSession getSession(String sessionId) {
//        UserSession session = sessions.get(sessionId);
//        if (session != null && !session.isExpired()) {
//            session.refresh(sessionTimeoutMinutes); // Обновляем время при обращении
//            return session;
//        }
//        return null;
//    }
//
//    public void deleteSession(String sessionId) {
//        sessions.remove(sessionId);
//    }
//
//    @Scheduled(fixedRate = 60000) // Каждую минуту
//    public void cleanupExpiredSessions() {
//        LocalDateTime now = LocalDateTime.now();
//        sessions.entrySet().removeIf(entry -> entry.getValue().isExpired());
//    }



}
