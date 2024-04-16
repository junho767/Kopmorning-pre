//package me.junholee.springbootdeveloper.controller;
//
//import lombok.RequiredArgsConstructor;
//import me.junholee.springbootdeveloper.domain.Team;
//import me.junholee.springbootdeveloper.dto.Team.TeamResponse;
//import me.junholee.springbootdeveloper.service.TeamService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@RequiredArgsConstructor
//@Controller
//public class TeamApiController {
//    private final TeamService teamService;
//    @GetMapping("/api/team/{id}")
//    public ResponseEntity<TeamResponse> getTeam(@PathVariable long id){
//        Team team = teamService.findById(id);
//        return ResponseEntity.ok()
//                .body(new TeamResponse(team));
//    }
//}
