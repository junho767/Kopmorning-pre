package me.junholee.springbootdeveloper.dto.Player;

import lombok.Data;
import me.junholee.springbootdeveloper.domain.Player;

@Data
public class TopKeyPassResponseDTO {
    private String image;
    private String team_tla;
    private String name;
    private String team_crest;
    private int key_pass;

    public TopKeyPassResponseDTO(Player player){
        this.key_pass = player.getPlayer_key_passes();
        this.name = player.getPlayer_name();
        this.image = player.getPlayer_image();
        this.team_tla = player.getTeam().getTeam_tla();
        this.team_crest = player.getTeam().getTeam_crest();
    }
}
