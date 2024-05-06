package me.junholee.springbootdeveloper.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.SessionUser;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.ImageDTO.ImageUploadDTO;
import me.junholee.springbootdeveloper.service.Blog.ImageService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    /**
     * 프로필 사진 등록
     * @param imageUploadDTO 사진 정보
     * @return 프로필 페이지
     */
    @PostMapping("/upload")
    public String upload(@ModelAttribute ImageUploadDTO imageUploadDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user= principalDetails.getUser();
        String email = user.getEmail();
        imageService.upload(imageUploadDTO, email);
        return "redirect:/myprofil";
    }
}
