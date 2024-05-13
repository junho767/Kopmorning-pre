package me.junholee.springbootdeveloper.controller;


import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.service.Like.LikeService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeAPIController {
    private final UserService userService;
    private final LikeService likeService;
    @PostMapping("/up/{id}")
    public ResponseEntity addLike(@PathVariable long id, Principal principal){
        User user = userService.findByUserName(principal.getName());
        likeService.articleLike(user,id);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/up/comment/{id}")
    public ResponseEntity commentLike(@PathVariable long id, Principal principal){

        User user = userService.findByUserName(principal.getName());
        likeService.commentLike(user,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
