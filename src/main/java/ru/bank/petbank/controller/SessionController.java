package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bank.petbank.services.SessionService;

@RestController
@RequestMapping("/api")
public class SessionController {
    @Autowired
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService authService) {
        this.sessionService = authService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<SessionResponse> login(@RequestBody AuthenticationRequest authRequest) {
        SessionResponse authResponse = sessionService.authenticate(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<SessionResponse> logout(@RequestBody SessionRequest sessionRequest) {
        SessionResponse logoutResponse = sessionService.invalidateSession(sessionRequest.getSessiontoken());
        return ResponseEntity.ok(logoutResponse);
    }

}
