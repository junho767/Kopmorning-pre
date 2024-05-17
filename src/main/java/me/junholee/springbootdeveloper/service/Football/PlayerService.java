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

    public PlayerResponseDTO findById(long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found Player"));
        return new PlayerResponseDTO(player);
    }

    public List<PlayerResponseDTO> convertList(List<Player> players){
        return players.stream()
                .map(PlayerResponseDTO::new)
                .collect(Collectors.toList());
    }
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

    @Transactional
    public List<GkResponseDTO> findGK(List<Player> playerList){
        playerList.sort(Comparator.comparing(Player::getPlayerNumber));
        return playerList.stream()
                .filter(player -> "Goalkeepers".equals(player.getPlayerType()) && player.getPlayerNumber() != 0) // 필터링 조건 추가
                .map(GkResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<DfResponseDTO> findDF(List<Player> playerList){
        playerList.sort(Comparator.comparing(Player::getPlayerNumber));
        return playerList.stream()
                .filter(player -> "Defenders".equals(player.getPlayerType()) && player.getPlayerNumber() != 0) // 필터링 조건 추가
                .map(DfResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<MfResponseDTO> findMF(List<Player> playerList){
        playerList.sort(Comparator.comparing(Player::getPlayerNumber)); // 등번호로 정렬

        return playerList.stream()
                .filter(player -> "Midfielders".equals(player.getPlayerType()) && player.getPlayerNumber() != 0) // 필터링 조건 추가
                .map(MfResponseDTO::new)
                .collect(Collectors.toList()); // 리스트로 수집
    }
    @Transactional
    public List<FwResponseDTO> findFW(List<Player> playerList){
        playerList.sort(Comparator.comparing(Player::getPlayerNumber));
        return playerList.stream()
                .filter(player -> "Forwards".equals(player.getPlayerType()) && player.getPlayerNumber() != 0) // 필터링 조건 추가
                .map(FwResponseDTO::new)
                .collect(Collectors.toList());
    }
}
