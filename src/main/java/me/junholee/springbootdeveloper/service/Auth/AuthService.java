package me.junholee.springbootdeveloper.service.Auth;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Auth.SignupRequestDTO;
import me.junholee.springbootdeveloper.dto.MessageDTO;
import me.junholee.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageDTO signUp(SignupRequestDTO requestDTO) {
        User user = requestDTO.toMember(passwordEncoder);
        userRepository.save(user);
        return new MessageDTO("로그인 성공");
    }
}
