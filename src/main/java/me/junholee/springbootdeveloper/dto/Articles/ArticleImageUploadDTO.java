package me.junholee.springbootdeveloper.dto.Articles;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ArticleImageUploadDTO {
    private List<MultipartFile> files;
}
