// 토큰을 생성 및 올바른 토큰인지 유효성 검사하고 토큰에서 필요 정보를 가져오는 클래스 3
package me.junholee.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

//    @Value("${JWT_SECRET}")
//    private Key key;
//
//    public String generateToken(User user, Duration expiredAt) {
//        Date now = new Date();
//        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
//    }

    private String makeToken(Date expiry, User user) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // JWT의 헤더에 타입을 설정합니다. 여기서는 JWT 타입을 지정합니다.
                .setIssuer(jwtProperties.getIssuer()) // JWT의 발급자(issuer)를 설정합니다. 보통은 애플리케이션의 이름 또는 도메인을 사용합니다. jwtProperties.getIssuer()는 설정된 발급자 값을 가져옵니다.
                .setIssuedAt(now) //JWT의 발행 시간을 설정합니다. now 변수에는 현재 시간이 저장되어 있습니다.
                .setExpiration(expiry) //JWT의 만료 시간을 설정합니다. expiry 변수에는 설정된 만료 시간이 저장되어 있습니다.
                .setSubject(user.getEmail()) // JWT의 주제(subject)를 설정합니다. 여기서는 사용자의 이메일을 주제로 설정합니다.
                .claim("id", user.getId()) //추가적인 클레임(claim)을 설정합니다. 여기서는 사용자의 ID를 id라는 이름으로 클레임에 추가합니다.
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // JWT를 서명합니다. HS256 알고리즘과 설정된 시크릿 키를 사용하여 JWT를 서명합니다.
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {

            return false;
        }
    }


    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}