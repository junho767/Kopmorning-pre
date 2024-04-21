package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Image;
import me.junholee.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findByUser(User user);
}
