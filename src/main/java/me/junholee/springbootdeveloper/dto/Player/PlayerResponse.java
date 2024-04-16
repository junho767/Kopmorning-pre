package me.junholee.springbootdeveloper.dto.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.junholee.springbootdeveloper.domain.Player;
import me.junholee.springbootdeveloper.domain.Team;

@NoArgsConstructor
@Getter
public class PlayerResponse {
    private Team team; // 해당 플레이어 팀 정보
    private String image; // 플레이어 사진
    private String name; // 플레이어 이름
    private String injured; // 부상 여부
    private String type; // 포지션(전체적인)
    private String birthdate; // 생년월일
    private int number; // 선수 등번호
    private int matchPlayed; // 경기 출전한 수
    private int goals; // 득점
    private float rating; // 평점
    private int substitutesOnBench; // 교체로 시작한 횟수
    private int assists; // 도움
    private int passes; // 패스
    private int dribbleAttempts; // 드리블 시도 횟수
    private int dribbleSucc; // 드리블 성공 횟수
    private int keyPasses; // 키패스 횟수
    private int tackles; // 태클 시도 횟수
    private int age; // 나이

    public PlayerResponse(Player player){
        this.team = player.getTeam();
        this.image = player.getPlayer_image();
        this.name = player.getPlayer_name();
        this.injured = player.getPlayer_injured();
        this.number = player.getPlayer_number();
        this.type = player.getPlayer_type();
        this.matchPlayed = player.getPlayer_match_played();
        this.goals = player.getPlayer_goals();
        this.rating = player.getPlayer_rating();
        this.substitutesOnBench = player.getPlayer_substitutes_on_bench();
        this.assists = player.getPlayer_assists();
        this.passes = player.getPlayer_passes();
        this.dribbleAttempts = player.getPlayer_dribble_attempts();
        this.dribbleSucc = player.getPlayer_dribble_succ();
        this.keyPasses = player.getPlayer_key_passes();
        this.tackles = player.getPlayer_tackles();
        this.birthdate = player.getPlayer_birthdate();
        this.age = player.getPlayer_age();
    }
}
