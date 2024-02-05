package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// JpaRepository는 Spring Data JPA가 기본 CRUD(Create, Read, Update, Delete) 작업을 자동으로 제공하는 인터페이스입니다.
// <RefreshToken, Long>: RefreshToken 엔티티를 관리하며, 엔티티의 기본 키 타입은 Long입니다.
// RefreshTokenRepository는 JpaRepository 인터페이스를 확장합니다.
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
//findByUserId 메서드: 주어진 userId에 해당하는 RefreshToken을 찾습니다. 반환값은 Optional<RefreshToken>으로, 값이 있을 수도 있고 없을 수도 있습니다.
//findByRefreshToken 메서드: 주어진 refreshToken에 해당하는 RefreshToken을 찾습니다. 마찬가지로 Optional<RefreshToken>을 반환합니다.