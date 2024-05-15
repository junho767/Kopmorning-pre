package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Player.*;
import me.junholee.springbootdeveloper.dto.Standing.StandingsResponseDTO;
import me.junholee.springbootdeveloper.dto.Team.TeamResponseDTO;
import me.junholee.springbootdeveloper.service.Football.MatchService;
import me.junholee.springbootdeveloper.service.Football.PlayerService;
import me.junholee.springbootdeveloper.service.Football.StandingService;
import me.junholee.springbootdeveloper.service.Football.TeamService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FootBallViewController {
    private final UserService userService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final StandingService standingService;

    @GetMapping("/team/{id}")
    public String getTeam(@RequestParam("id") long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        List<Player> playerlist = playerService.findTeamPlayer(id);
        TeamResponseDTO team = teamService.findById(id);

        model.addAttribute("player",playerlist);
        model.addAttribute("team",team);

        List<TopScoreResponseDTO> top_score = new ArrayList<>(playerService.scoreSort(playerlist,5));
        List<TopAssistResponseDTO> top_assist = new ArrayList<>(playerService.assistSort(playerlist,5));
        List<TopKeyPassResponseDTO> top_keyPasses = new ArrayList<>(playerService.keyPassesSort(playerlist,5));

        model.addAttribute("topScore",top_score);
        model.addAttribute("topAssist",top_assist);
        model.addAttribute("topKeyPasses",top_keyPasses);


        return "team";
    }
    @GetMapping("/league")
    public String getLeague(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        List<StandingsResponseDTO> standingsList = standingService.findAll().stream()
                .map(StandingsResponseDTO::new)
                .toList();

        List<Player> playerList = playerService.findAll();

        List<TopScoreResponseDTO> top_score = new ArrayList<>(playerService.scoreSort(playerList,5));
        List<TopAssistResponseDTO> top_assist = new ArrayList<>(playerService.assistSort(playerList,5));
        List<TopKeyPassResponseDTO> top_keyPasses = new ArrayList<>(playerService.keyPassesSort(playerList,5));
        List<TopRatingResponseDTO> top_rating = new ArrayList<>(playerService.ratingSort(playerList, 5));

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        model.addAttribute("topScore",top_score);
        model.addAttribute("topAssist",top_assist);
        model.addAttribute("topKeyPass",top_keyPasses);
        model.addAttribute("topRating",top_rating);
        model.addAttribute("standing",standingsList);
        return "league";
    }
}
