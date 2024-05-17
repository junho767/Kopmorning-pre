package me.junholee.springbootdeveloper.repository;

import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.dto.Player.GkResponseDTO;
import me.junholee.springbootdeveloper.dto.Player.PlayerResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByTeamId(long team_id);
    Player findByTeamIdAndPlayerNumber(long teamId, int playerNumber);
}
