package me.junholee.springbootdeveloper.config.oauth;

import me.junholee.springbootdeveloper.domain.Role;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.repository.UserRepository;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.UUID;

@Configuration
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String name = oAuth2User.getAttribute("name");
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider+"_"+providerId;
        String picture = oAuth2User.getAttribute("picture");
        String uuid = UUID.randomUUID().toString().substring(0,6);
        String password = bCryptPasswordEncoder.encode("비밀번호"+uuid);

        String email = oAuth2User.getAttribute("email");
        Role role = Role.ROLE_USER;

        User user = userService.findByUserName(username);
        if(user == null){
            user = User.oauth2Register()
                    .nickname(name)
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .picture(picture)
                    .build();
            userRepository.save(user);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
