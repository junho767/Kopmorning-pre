package me.junholee.springbootdeveloper.controller.Auth;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.dto.Auth.SignupRequestDTO;
import me.junholee.springbootdeveloper.dto.MessageDTO;
import me.junholee.springbootdeveloper.service.Auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MessageDTO> signUp(@RequestBody SignupRequestDTO requestDTO) {
        return ResponseEntity.ok(authService.signUp(requestDTO));
    }
}
