package me.junholee.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.junholee.springbootdeveloper.dto.Football.Standing.StandingsResponseDTO;
import me.junholee.springbootdeveloper.service.Football.StandingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football")
@RequiredArgsConstructor
public class FootBallController {
    private final StandingService standingService;

    @GetMapping("/standing")
    public ResponseEntity<StandingsResponseDTO> getStanding() {
        long id = 2021;
        return ResponseEntity.ok(standingService.getStandings(id));
    }
}
