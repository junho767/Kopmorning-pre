package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Comment;
import me.junholee.springbootdeveloper.dto.CommentList.CommentRequest;
import me.junholee.springbootdeveloper.dto.CommentList.CommentResponse;
import me.junholee.springbootdeveloper.service.Comment.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentApiController {
    private final CommentService commentService;
    @PostMapping("/articles/{id}/comment")
    public ResponseEntity<Long> CommentSave(@PathVariable Long id, @RequestBody CommentRequest request, Principal principal){
        return ResponseEntity.ok(commentService.commentSave(principal.getName(), id,request));
    }
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Void> CommentDelete(@RequestBody CommentResponse commentResponse){
        commentService.delete(commentResponse.getId());
        return ResponseEntity.ok().build();
    }
    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> CommentUpdate(@RequestBody CommentResponse commentResponse){
        Comment comment = commentService.CommentUpdate(commentResponse.getId(),commentResponse.getComment());
        return ResponseEntity.ok().body(comment);
    }
}
