package me.junholee.springbootdeveloper.dto.Football.Team;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.junholee.springbootdeveloper.dto.Football.Standing.AreaDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamResponseDTO {
    private AreaDTO area;
    private long id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    private String address;
    private String website;
    private long founded;
    private String clubColors;
    private String venue;
    private CoachDTO coach;
    private double marketValue;
    private List<SquadDTO> squad;
}
