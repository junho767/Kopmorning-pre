package me.junholee.springbootdeveloper.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

// OAuth2 프로토콜 방식을 사용하지 않는 방식(formLogin(), 일반 로그인) 에서는 Spring Security가 "/loginForm" uri로 이동시킴
// Spring Security Filter가 자동으로 "/login" post방식으로 오는 uri를 낚아채어 UserDetalisService타입의 객체의 loadUserByUsername 메소드를 호출해둔다.
// OAuth2 프로톨을 방식을 사용할때에는(oauth2Login()) DefalutOAuth2UserService(여기선 PrincipalOauthUserService가 해당 타입을 상속함) 타입의 loadUser 메소드를 호출한다.
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Autowired private PrincipalOauth2UserService principalOauth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/token").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/loginForm")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginForm")
                        .defaultSuccessUrl("/")
                        .failureUrl("/loginForm")
                        .userInfoEndpoint()
                        .userService(principalOauth2UserService)
                )
                .exceptionHandling(e -> e
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/loginForm"),
                                new AntPathRequestMatcher("/api/**")
                        )
                )
                .requestCache(rc -> rc
                        .requestCache(new CustomRequestCache())
                );

        return http.build();
    }

    /**
     * Custom RequestCache implementation to avoid caching requests to /api/**.
     */
    public static class CustomRequestCache extends HttpSessionRequestCache {
        private static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";
        private RequestMatcher skipRequestMatcher = new AntPathRequestMatcher("/api/**");

        @Override
        public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
            if (!skipRequestMatcher.matches(request)) {
                super.saveRequest(request, response);
            }
        }

        @Override
        public SavedRequest getRequest(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                return (SavedRequest) session.getAttribute(SAVED_REQUEST);
            }
            return null;
        }
    }
}