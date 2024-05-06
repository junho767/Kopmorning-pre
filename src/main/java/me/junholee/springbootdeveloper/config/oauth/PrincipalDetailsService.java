package me.junholee.springbootdeveloper.config.oauth;

import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.repository.UserRepository;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//UserDetailsService는 FormLogin시 loadUserByUsername 메서드로 로그인한 유저가 DB에 저장되어있는지를 찾는다.
//찾으면 앞에서 구현한 Authentication(UserDetails를 구현한 PrincipalDetails)을 반환하여 SecurityContextHolder에 저장할 수 있게 한다.
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);
        if(user!=null){
            return new PrincipalDetails(user);
        }
        return null;
    }
}
