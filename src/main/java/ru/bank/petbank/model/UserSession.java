package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user_Session\"")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_session_id")
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userId;
    @Column(name = "session_token", nullable = false)
    private Long sessionToken;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "device")
    private String device;


//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinColumn(name = "user_credential_id")
//    private UserCredential userCredentialSes;

    public UserSession(Long userId, String device, Integer timeoutSeconds) {
        this.userId = userId;
        this.sessionToken = UUID.randomUUID().getMostSignificantBits();;
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusSeconds(timeoutSeconds);
        this.device = device;
    }

    public UserSession() {}

    @Override
    public String toString() {
        return "UserSession [userId=" + userId + ", sessionToken=" + sessionToken + ", startDate=" + startDate
                + ", endDate=" + endDate + ", device=" + device + "]";
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(endDate);
    }

    public void refresh(Integer timeoutMinutes) {
        this.endDate = LocalDateTime.now().plusMinutes(timeoutMinutes);
    }
}
