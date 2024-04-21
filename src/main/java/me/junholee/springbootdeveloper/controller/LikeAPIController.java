package me.junholee.springbootdeveloper.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.SessionUser;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.service.Like.LikeService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeAPIController {
    private final UserService userService;
    private final LikeService likeService;
    private final HttpSession httpSession;
    @PostMapping("/up/{id}")
    public ResponseEntity addLike(@PathVariable long id){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userService.findByEmail(sessionUser.getEmail());
        likeService.addLike(user,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
