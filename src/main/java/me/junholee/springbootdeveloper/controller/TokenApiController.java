//package me.junholee.springbootdeveloper.controller;
//
//import lombok.RequiredArgsConstructor;
//import me.junholee.springbootdeveloper.dto.TokenDTO.CreateAccessTokenRequest;
//import me.junholee.springbootdeveloper.dto.TokenDTO.CreateAccessTokenResponse;
//import me.junholee.springbootdeveloper.service.Token.TokenService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class TokenApiController {
//
//    private final TokenService tokenService;
//
//    @PostMapping("/api/token.js")
//    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
//        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new CreateAccessTokenResponse(newAccessToken));
//    }
//}
