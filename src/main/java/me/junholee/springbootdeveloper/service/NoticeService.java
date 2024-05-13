package me.junholee.springbootdeveloper.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class NoticeService {

    private final ResourceLoader resourceLoader;

    public NoticeService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getNoticeContent() throws IOException {
        // 내장 리소스에서 텍스트 파일을 읽어옵니다.
        Resource resource = resourceLoader.getResource("/Notice.txt");

        // 파일을 읽기 위한 BufferedReader를 생성합니다.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            // 파일의 내용을 한 줄씩 읽어서 StringBuilder에 추가합니다.
            while ((line = br.readLine()) != null) {
                content.append(line);
                content.append("\n"); // 개행 문자를 추가하여 각 줄을 구분합니다.
            }
            // 마지막에 개행 문자가 추가된 문자열을 반환합니다.
            return content.toString();
        }
    }
}