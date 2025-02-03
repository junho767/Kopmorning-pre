//회원가입 메서드
package me.junholee.springbootdeveloper.service.Member;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.User.UpdateUserRequest;
import me.junholee.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
}