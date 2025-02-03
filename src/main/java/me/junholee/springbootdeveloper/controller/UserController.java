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
    @Autowired private UserService userService;

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request){
        return "login";
    }
}
