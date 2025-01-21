package me.junholee.springbootdeveloper.dto.Image;

import lombok.Data;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Setter
public class ArticleImageUploadDTO {
    private List<MultipartFile> files;
}
