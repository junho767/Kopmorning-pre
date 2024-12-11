package me.junholee.springbootdeveloper.dto.Auth;

import lombok.*;
import me.junholee.springbootdeveloper.domain.Role;
import me.junholee.springbootdeveloper.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDTO {
    private String email;
    private String password;
    private String nickname;

    public User toMember(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password)) // 입력받은 비밀번호 DB에 암호화해서 저장
                .nickname(nickname)
                .role(Role.ROLE_GUEST)
                .build();
    }
}
