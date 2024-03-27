//회원가입 메서드
package me.junholee.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.AddUserRequest;
import me.junholee.springbootdeveloper.dto.UpdateUserRequest;
import me.junholee.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
    @Transactional
    //트랜잭션을 관리하기 위해 사용됩니다.
    // 이 어노테이션을 메서드나 클래스에 적용하면, 해당 메서드나 클래스의 실행이 트랜잭션 내에서 이루어지게 됩니다.
    // 즉, 메서드나 클래스의 실행이 한 논리적 단위로 묶여서 일관된 상태를 유지하도록 도와줍니다.
    public User UpdateUser(UpdateUserRequest request){
        User user = findByEmail(request.getEmail());
        user.modify_User(request.getName(), request.getPicture(), request.getReason(), request.getYear());
        return user;
    }
}