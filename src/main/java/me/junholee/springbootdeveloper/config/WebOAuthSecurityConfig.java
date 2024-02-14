package me.junholee.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.jwt.TokenProvider;
import me.junholee.springbootdeveloper.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import me.junholee.springbootdeveloper.config.oauth.OAuth2SuccessHandler;
import me.junholee.springbootdeveloper.config.oauth.OAuth2UserCustomService;
import me.junholee.springbootdeveloper.repository.RefreshTokenRepository;
import me.junholee.springbootdeveloper.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        http.authorizeRequests()
                .requestMatchers("/api/token").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll();

        http.oauth2Login()
                // OAuth2 로그인이 시작되는 페이지를 지정합니다. 사용자를 인증 페이지로 리다이렉트합니다.
                .loginPage("/login")
                // OAuth2 인증 요청의 상태를 저장하고 관리하는데 사용되는 인증 요청 저장소를 설정합니다.
                .authorizationEndpoint()
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                // OAuth2 로그인 성공 시 처리할 핸들러를 설정합니다.
                .successHandler(oAuth2SuccessHandler())
                // OAuth2 사용자 정보 엔드포인트의 구성을 시작합니다.
                .userInfoEndpoint()
                // 사용자 정보를 로드하는 데 사용되는 사용자 서비스를 설정합니다.
                .userService(oAuth2UserCustomService);

        http.logout()
                .logoutSuccessUrl("/login");


        http.exceptionHandling()
                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        new AntPathRequestMatcher("/api/**"));


        return http.build();
    }


    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                userService
        );
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
