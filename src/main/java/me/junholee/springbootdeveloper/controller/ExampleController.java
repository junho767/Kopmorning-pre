package me.junholee.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){ // Model 객체는 HTML 쪽으로 값을 넘겨주는 객체이다.
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("이준호");
        examplePerson.setAge(25);
        examplePerson.setHobbies(List.of("watching soccer", "Game", "Badminton"));

        model.addAttribute("person", examplePerson); // person 객체에 저장
        model.addAttribute("today", LocalDate.now());
        return "example"; // example.html 뷰 조회
    }

    @Getter
    @Setter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
