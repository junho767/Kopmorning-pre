package me.junholee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "picture")
    private String picture;

    @Column(name = "reason")
    private String reason;

    @Column(name="name")
    private String name;

    @Column(nullable = true)
    private Long year;

    @Builder
    public User(String nickname,String password, String email, String picture) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.picture = picture;
    }

    public User update(String picture) {
        this.picture = picture;
        return this;
    }

    public User modify_User(String name, String reason, long year){
        this.name = name;
        this.reason = reason;
        this.year = year;
        return this;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //- 사용자 권한 리스트 반환
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}