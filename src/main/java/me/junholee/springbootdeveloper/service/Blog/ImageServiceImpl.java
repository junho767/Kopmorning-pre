package me.junholee.springbootdeveloper.service.Blog;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.dto.Articles.ArticleViewResponse;
import me.junholee.springbootdeveloper.dto.ImageDTO.ImageResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import me.junholee.springbootdeveloper.domain.Image;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.ImageDTO.ImageUploadDTO;
import me.junholee.springbootdeveloper.repository.ImageRepository;
import me.junholee.springbootdeveloper.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Value("${file.ProfileImagePath}")
    private String uploadFolder;

    @Override
    public void upload(ImageUploadDTO imageUploadDTO, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        MultipartFile file = imageUploadDTO.getFile();

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        File destinationFile = new File(uploadFolder + imageFileName);

        try {
            file.transferTo(destinationFile);

            Image image = imageRepository.findByUser(user);
            if (image != null) {
                // 이미지가 이미 존재하면 url 업데이트
                image.updateUrl(imageFileName);
            } else {
                // 이미지가 없으면 객체 생성 후 저장
                image = Image.builder()
                        .user(user)
                        .url(imageFileName)
                        .build();
            }
            imageRepository.save(image);
            user.update(image.getUrl());
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ImageResponseDTO findImage(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        Image image = imageRepository.findByUser(user);

        String defaultImageUrl = "/profileImages/anonymous.png";

        if (image == null) {
            return ImageResponseDTO.builder()
                    .url(defaultImageUrl)
                    .build();
        } else {
            return ImageResponseDTO.builder()
                    .url(image.getUrl())
                    .build();
        }
    }
}