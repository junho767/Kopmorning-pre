package me.junholee.springbootdeveloper.dto.Player;

import lombok.Data;
import me.junholee.springbootdeveloper.domain.Player;

@Data
public class TopScoreResponseDTO {
    private String image;
    private String team_tla;
    private String name;
    private String team_crest;
    private int score;

    public TopScoreResponseDTO(Player player){
        this.score = player.getPlayer_goals();
        this.name = player.getPlayer_name();
        this.image = player.getPlayer_image();
        this.team_tla = player.getTeam().getTeam_tla();
        this.team_crest = player.getTeam().getTeam_crest();
    }
}
