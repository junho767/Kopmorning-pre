package me.junholee.springbootdeveloper.service;

import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}

    public Player save(Player player) { return playerRepository.save(player);}
}
