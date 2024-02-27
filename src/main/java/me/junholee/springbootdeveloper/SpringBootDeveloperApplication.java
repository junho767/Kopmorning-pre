package me.junholee.springbootdeveloper;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.dto.StandingsRequest;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //created_at, update_at 자동 업데이트
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootDeveloperApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
//    @Bean //@Bean 애노테이션이 붙은 메서드를 호출하여 반환된 객체를 빈으로 등록합니다.
//    //ApplicationRunner 는 스프링 부트 애플리케이션이 시작될 때 특정한 로직을 실행할 수 있게 해주는 인터페이스
//    // run() 메서드를 오버라이딩하여 원하는 초기화 로직을 작성
//    public ApplicationRunner initializeData(StandingsRequest standingsRequest) {
//        return args -> {
//            standingsRequest.addStandings();
//        };
//    }
}
