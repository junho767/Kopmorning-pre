package me.junholee.springbootdeveloper.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.domain.SessionUser;
import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.service.Football.PlayerService;
import me.junholee.springbootdeveloper.service.Football.TeamService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TeamViewController {
    private final HttpSession httpSession;
    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        List<Player> playerlist = playerService.findTeamPlayer(id);
        List<Player> top_score = playerService.scoreSort(playerlist,5);
        model.addAttribute("topScore",top_score);
//        List<Player> top_assist = playerService.assistSort(playerlist,5);
//        model.addAttribute("topAssist",top_assist);
//        List<Player> top_keyPasses = playerService.keyPassesSort(playerlist,5);
//        model.addAttribute("topKeyPasses",top_keyPasses);
        Team team = teamService.findById(id);
        model.addAttribute("player",playerlist);
        model.addAttribute("team",team);
        model.addAttribute("user",user);
        return "team";
    }
}
