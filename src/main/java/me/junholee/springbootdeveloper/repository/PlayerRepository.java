package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
