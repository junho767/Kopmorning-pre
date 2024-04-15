package me.junholee.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}

    public Player save(Player player) { return playerRepository.save(player);}


    @Transactional
    public List<Player> findTeamPlayer(long id){ return playerRepository.findByTeamId(id);}

    @Transactional
    public List<Player> scoreSort(List<Player> player,int num){
        player.sort(Comparator.comparing(Player::getPlayer_goals).reversed());
        return player.subList(0,Math.min(player.size(),num));
    }
    @Transactional
    public List<Player> assistSort(List<Player> player,int num){
        player.sort(Comparator.comparing(Player::getPlayer_assists).reversed());
        return player.subList(0,Math.min(player.size(),num));
    }
    @Transactional
    public List<Player> keyPassesSort(List<Player> player,int num){
        player.sort(Comparator.comparing(Player::getPlayer_key_passes).reversed());
        return player.subList(0,Math.min(player.size(),num));
    }
    @Transactional
    public List<Player> ratingSort(List<Player> player,int num){
        player.sort(Comparator.comparing(Player::getPlayer_key_passes).reversed());
        return player.subList(0,Math.min(player.size(),num));
    }
}
