package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
