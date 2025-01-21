package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.dto.Football.Match.Matches;
import me.junholee.springbootdeveloper.dto.Football.Standing.StandingsResponseDTO;
import me.junholee.springbootdeveloper.dto.Football.Team.TeamResponseDTO;
import me.junholee.springbootdeveloper.service.Football.MatchService;
import me.junholee.springbootdeveloper.service.Football.StandingService;
import me.junholee.springbootdeveloper.service.Football.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/football")
@RequiredArgsConstructor
public class FootBallController {
    private final StandingService standingService;
    private final TeamService teamService;
    private final MatchService matchService;

    @GetMapping("/standing")
    public ResponseEntity<StandingsResponseDTO> getStanding() {
        long id = 2021;
        return ResponseEntity.ok(standingService.getStandings(id));
    }

    @PostMapping("/team")
    public ResponseEntity<TeamResponseDTO> getTeamInfo(@RequestParam("teamId") long teamId) {
        return ResponseEntity.ok(teamService.getTeamInfo(teamId));
    }

    @PostMapping("/matchList")
    public ResponseEntity<Matches> getMatchList(@RequestParam("leagueId") long leagueId) {
        return ResponseEntity.ok(matchService.getMatchList(leagueId));
    }
}
