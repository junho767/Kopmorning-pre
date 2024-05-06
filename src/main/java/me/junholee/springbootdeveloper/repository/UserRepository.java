package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //Long 이것은 엔티티 클래스의 기본 키의 데이터 유형을 지정
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
}