package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.config.oauth.PrincipalDetails;
import me.junholee.springbootdeveloper.domain.User;
import me.junholee.springbootdeveloper.dto.Football.Standing.StandingsResponseDTO;
import me.junholee.springbootdeveloper.dto.Football.Team.TeamResponseDTO;
import me.junholee.springbootdeveloper.service.Football.StandingService;
import me.junholee.springbootdeveloper.service.Football.TeamService;
import me.junholee.springbootdeveloper.service.Member.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FootBallViewController {
    private final UserService userService;
    private final TeamService teamService;
    private final StandingService standingService;

    @GetMapping("/team/{id}")
    public String getTeam(@RequestParam("id") long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        return "team";
    }
    @GetMapping("/league")
    public String getLeague(Model model){

        StandingsResponseDTO standingsList = standingService.getStandings(2021);

        model.addAttribute("standing",standingsList);
        return "league";
    }
    @GetMapping("/player/{id}")
    public String getPlayer(@RequestParam("id") long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(principalDetails != null) {
            User user= userService.findByEmail(principalDetails.getUser().getEmail());
            model.addAttribute("user", user);
        }

        return "player";
    }
    @GetMapping("/schedule/{id}")
    public String getSchedule(@RequestParam("id") long id){

        return "schedule";
    }
    @GetMapping("/stat/{id}")
    public String getStat(@RequestParam("id") long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        return "stat";
    }
}
