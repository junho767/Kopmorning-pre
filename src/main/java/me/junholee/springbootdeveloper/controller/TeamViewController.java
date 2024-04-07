package me.junholee.springbootdeveloper.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.domain.SessionUser;
import me.junholee.springbootdeveloper.domain.Team;
import me.junholee.springbootdeveloper.dto.PlayerResponse;
import me.junholee.springbootdeveloper.dto.TeamResponse;
import me.junholee.springbootdeveloper.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TeamViewController {
    private final BlogService blogService;
    private final HttpSession httpSession;
    private final StandingService standingService;
    private final MatchService matchService;
    private final UserService userService;
    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable long id, Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        List<Player> playerlist = playerService.findTeamPlayer(id);
        Team team = teamService.findById(id);
        model.addAttribute("team",team);
        model.addAttribute("user",user);
        model.addAttribute("player",playerlist);
        return "team";
    }
}
