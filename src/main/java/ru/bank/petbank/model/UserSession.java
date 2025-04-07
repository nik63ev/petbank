package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"user_Session\"")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_session_id")
    private Long id;

    @Column(name = "session_token")
    private String sessionToken;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "device")
    private String device;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredentialSes;

    public UserSession(String sessionToken, LocalDateTime startDate, LocalDateTime endDate, String device) {
        this.sessionToken = sessionToken;
        this.startDate = startDate;
        this.endDate = endDate;
        this.device = device;
    }

    public UserSession() {}

    @Override
    public String toString() {
        return "UserSession [id=" + id + ", sessionToken=" + sessionToken + ", startDate=" + startDate + ", endDate="
        + endDate + ", device=" + device + "]";
    }
}
