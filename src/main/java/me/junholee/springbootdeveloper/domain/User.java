package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "picture")
    private String picture;

    @Column(name = "reason")
    private String reason;

    @Column(name = "nickname")
    private String nickname;

    @Column(nullable = true)
    private Long year;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId;  // oauth2를 이용할 경우 아이디값

    @Builder(builderClassName = "UserDetailRegister", builderMethodName = "userDetailRegister")
    public User(String username, String password, String email, String picture, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public User(String username, String picture,String nickname ,String password, String email, Role role, String provider, String providerId) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.picture = picture;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String picture) {
        this.picture = picture;
        return this;
    }

    public User modify_User(String nickname, String reason, long year) {
        this.nickname = nickname;
        this.reason = reason;
        this.year = year;
        return this;
    }
}