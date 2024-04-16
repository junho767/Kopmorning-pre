package me.junholee.springbootdeveloper.dto.TokenDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 해당 클래스의 모든 필드를 이용한 생성자를 자동으로 생성해줍니다.
                    // 즉, 클래스의 모든 필드를 파라미터로 받는 생성자를 만들어주는 역할을 합니다.

public class CreateAccessTokenResponse {
    private String accessToken;
}
