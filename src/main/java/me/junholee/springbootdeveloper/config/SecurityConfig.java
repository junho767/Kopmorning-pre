package me.junholee.springbootdeveloper.config;

import me.junholee.springbootdeveloper.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// OAuth2 프로토콜 방식을 사용하지 않는 방식(formLogin(), 일반 로그인) 에서는 Spring Security가 "/loginForm" uri로 이동시킴
// Spring Security Filter가 자동으로 "/login" post방식으로 오는 uri를 낚아채어 UserDetalisService타입의 객체의 loadUserByUsername 메소드를 호출해둔다.
// OAuth2 프로톨을 방식을 사용할때에는(oauth2Login()) DefalutOAuth2UserService(여기선 PrincipalOauthUserService가 해당 타입을 상속함) 타입의 loadUser 메소드를 호출한다.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/token").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/loginForm")

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm")

                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/")
                .failureUrl("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        return http.build();
    }
}
