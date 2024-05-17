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
import java.util.stream.Collectors;

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

        List<GkResponseDTO> Gk_player = new ArrayList<>(playerService.findGK(playerlist));
        List<DfResponseDTO> Df_player = new ArrayList<>(playerService.findDF(playerlist));
        List<MfResponseDTO> Mf_player = new ArrayList<>(playerService.findMF(playerlist));
        List<FwResponseDTO> Fw_player = new ArrayList<>(playerService.findFW(playerlist));
        List<TopScoreResponseDTO> top_score = new ArrayList<>(playerService.scoreSort(playerlist,5));
        List<TopAssistResponseDTO> top_assist = new ArrayList<>(playerService.assistSort(playerlist,5));
        List<TopKeyPassResponseDTO> top_keyPasses = new ArrayList<>(playerService.keyPassesSort(playerlist,5));

        model.addAttribute("GK",Gk_player);
        model.addAttribute("DF",Df_player);
        model.addAttribute("MF",Mf_player);
        model.addAttribute("FW",Fw_player);
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
    @GetMapping("/player/{id}")
    public String getPlayer(@RequestParam("id") long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        PlayerResponseDTO player = playerService.findById(id);
        String[] colors = player.getTeamColors().split(" / ");
        String color1 = colors[0].equalsIgnoreCase("Claret") ? "#7F1734" : colors[0].equalsIgnoreCase("Navy Blue") ? "#132257" : colors[0].replace(" ","");
        String color2 = colors[1].equalsIgnoreCase("Navy Blue") ? "#132257": colors[1].replace(" ", "");
        model.addAttribute("color1", color1);
        model.addAttribute("color2", color2);

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        model.addAttribute("player", player);
        return "player";
    }
    @GetMapping("/schedule/{id}")
    public String getSchedule(@RequestParam("id") long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return "schedule";
    }
}
