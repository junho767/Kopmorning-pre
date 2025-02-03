package me.junholee.springbootdeveloper.dto.Football.Standing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandingsResponseDTO {
    private AreaDTO area;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<StandingsDTO> standings;
}
