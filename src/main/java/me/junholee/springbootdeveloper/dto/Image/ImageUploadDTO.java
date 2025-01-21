package me.junholee.springbootdeveloper.dto.Image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
// Spring Framework에서 제공하는 인터페이스로, HTTP 요청으로부터 업로드된 파일의 내용을 담고 있습니다.
// 파일의 원본 이름, 크기, MIME 타입 등을 제공하며, 파일의 내용을 바이트 배열 형태로 가져올 수도 있습니다.

@Data
public class ImageUploadDTO {

    private MultipartFile file;

}
