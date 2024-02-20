// 요청이 오면 헤더값을 비교해서 토큰이 있는지 확인 후 유효 토큰이라면 시큐리티 콘테스트 홀더에 인증 정보를 저장.
// 액세스 토큰 값이 담긴 Authorization 헤더값을 가져와 엑세스 토큰이 유효하다면 인증 정보 저장.
// 2
package me.junholee.springbootdeveloper.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "token=";

    //FilterChain은 여러 필터가 연결된 구조에서 현재 필터가 다음에 적용될 필터 또는 서블릿으로 요청을 전달하는 역할을 합니다.
    // 따라서 이 객체를 사용하여 다음 필터 또는 서블릿으로 요청을 전달할 수 있습니다.
    @Override // 토큰에서 사용자 정보 꺼내기
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws ServletException, IOException {

        String authorizationHeader = request.getQueryString();
        String token = getAccessToken(authorizationHeader);

        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
