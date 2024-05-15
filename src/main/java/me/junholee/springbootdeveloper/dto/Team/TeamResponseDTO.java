package me.junholee.springbootdeveloper.dto.Team;


import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Coach;
import me.junholee.springbootdeveloper.domain.Team;

@NoArgsConstructor
@Getter
public class TeamResponseDTO {
    private long id;
    private String name;
    private String tla;
    private String shortName;
    private String crest;
    private String venue;
    private String coach_name;
    private String nationality;

    public TeamResponseDTO(Team team) {
        this.id = team.getId();
        this.name = team.getTeam_name();
        this.tla = team.getTeam_tla();
        this.shortName = team.getTeam_shortName();
        this.crest = team.getTeam_crest();
        this.venue = team.getVenue();
        this.coach_name = team.getCoach().getName();
        this.nationality = team.getCoach().getNationality();
    }
}
