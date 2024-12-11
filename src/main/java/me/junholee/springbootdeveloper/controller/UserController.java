package me.junholee.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.Role;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.User.UpdateUserRequest;
import me.junholee.springbootdeveloper.repository.UserRepository;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request){

        return "login";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        user.setRole(Role.ROLE_USER);
        user.setPicture("default_userImage.png");
        String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);

        userRepository.save(user);  //반드시 패스워드 암호화해야함
        return "redirect:/loginForm";
    }
    @PostMapping("/api/myProFil")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        String email = user.getEmail();
        User updateUser = userService.UpdateUser(email, request);

        return ResponseEntity.ok()
                .body(updateUser);
    }
}
