package me.junholee.springbootdeveloper.dto.Player;

import lombok.Data;
import me.junholee.springbootdeveloper.domain.Player;

@Data
public class GkResponseDTO {
    private long id;
    private String image;
    private String name;
    private String position;
    private String nationality;

    public GkResponseDTO(Player player){
        this.id = player.getPlayer_key();
        this.image = player.getPlayer_image();
        this.name = player.getPlayer_name();
        this.position = player.getPlayerType();
        this.nationality = player.getPlayerNationality();
    }
}
