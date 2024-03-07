package me.junholee.springbootdeveloper.dto;

import me.junholee.springbootdeveloper.domain.Match;
import me.junholee.springbootdeveloper.domain.Team;

public class MatchRespones {
    private Team home;
    private Team away;
    private int home_score;
    private int away_score;
    private int last_match;

    public MatchRespones(Match match){
        this.home = match.getHome_team();
        this.away = match.getAway_team();
        this.home_score = match.getHome_score();
        this.away_score = match.getAway_score();
        this.last_match = match.getMatch_day();
    }
}
