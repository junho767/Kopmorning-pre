package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByTeamId(long team_id);
}
