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
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TeamViewController {
    private final UserService userService;
    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        List<Player> playerlist = playerService.findTeamPlayer(id);
        Team team = teamService.findById(id);

        model.addAttribute("player",playerlist);
        model.addAttribute("team",team);

        List<Player> top_score = new ArrayList<>(playerService.scoreSort(playerlist,5));
        List<Player> top_assist = new ArrayList<>(playerService.assistSort(playerlist,5));
        List<Player> top_keyPasses = new ArrayList<>(playerService.keyPassesSort(playerlist,5));

        model.addAttribute("topScore",top_score);
        model.addAttribute("topAssist",top_assist);
        model.addAttribute("topKeyPasses",top_keyPasses);


        return "team";
    }
}
