package ru.bank.petbank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "\"user_credential\"")
//@Builder

public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_credential_id")
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "userid", unique = true, nullable = false)
    private Long userId;

//    @OneToOne(mappedBy = "userCredential", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
//    private UserInfo userInfo;

//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
//                mappedBy = "userCredentialSes", fetch = FetchType.EAGER)
//    private List<UserSession> sessionList;

    public UserCredential(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userId = UUID.randomUUID().getMostSignificantBits();
    }

    public UserCredential(){}

//    public void addUserSession(UserSession userSession) {
//        if (sessionList == null) {
//            sessionList = new ArrayList<>();
//        }
//        sessionList.add(userSession);
//        userSession.setUserCredentialSes(this);
//    }

    @Override
    public String toString() {
        return "UserCredential [id=" + id + ", username=" + username + ", password=" + password + " + email= "
                + email + "]";
    }
}
