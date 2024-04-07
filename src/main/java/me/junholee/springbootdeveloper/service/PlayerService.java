package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}

    public Player save(Player player) { return playerRepository.save(player);}

    public List<Player> findTeamPlayer(long id){ return playerRepository.findByTeamId(id);}
}
