package me.junholee.springbootdeveloper.service.Football;

import jakarta.transaction.Transactional;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.dto.Player.*;
import me.junholee.springbootdeveloper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}

    public Player save(Player player) { return playerRepository.save(player);}

    public List<Player> findAll(){ return playerRepository.findAll(); }

    @Transactional
    public List<Player> findTeamPlayer(long id){ return playerRepository.findByTeamId(id);}

    @Transactional
    public List<TopScoreResponseDTO> scoreSort(List<Player> player, int num){
        player.sort(Comparator.comparing(Player::getPlayer_goals).reversed());
        List<Player> topPlayer = player.subList(0,Math.min(player.size(),num));
        return topPlayer.stream().map(TopScoreResponseDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public List<TopAssistResponseDTO> assistSort(List<Player> player, int num){
        player.sort(Comparator.comparing(Player::getPlayer_assists).reversed());
        List<Player> topPlayer = player.subList(0,Math.min(player.size(),num));
        return topPlayer.stream().map(TopAssistResponseDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public List<TopKeyPassResponseDTO> keyPassesSort(List<Player> player, int num){
        player.sort(Comparator.comparing(Player::getPlayer_key_passes).reversed());
        List<Player> topPlayer = player.subList(0,Math.min(player.size(),num));
        return topPlayer.stream().map(TopKeyPassResponseDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public List<TopRatingResponseDTO> ratingSort(List<Player> player, int num){
        player.sort(Comparator.comparing(Player::getPlayer_rating).reversed());
        List<Player> topPlayer = player.subList(0,Math.min(player.size(),num));
        return topPlayer.stream().map(TopRatingResponseDTO::new).collect(Collectors.toList());
    }
}
