package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Long> {
}
