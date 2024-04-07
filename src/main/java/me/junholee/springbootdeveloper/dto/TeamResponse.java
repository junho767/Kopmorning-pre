package me.junholee.springbootdeveloper.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Team;

@NoArgsConstructor
@Getter
public class TeamResponse {
    private long id;
    private String teamName;
    private String teamTla;
    private String teamShortName;
    private String teamCrest;
    private String venue;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.teamName = team.getTeam_name();
        this.teamTla = team.getTeam_tla();
        this.teamShortName = team.getTeam_shortName();
        this.teamCrest = team.getTeam_crest();
        this.venue = team.getVenue();
    }
}
