package me.junholee.springbootdeveloper.service.Football;

import me.junholee.springbootdeveloper.domain.Coach;
import me.junholee.springbootdeveloper.repository.CoachRepository;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    public void CoachSave(Coach coach){
        coachRepository.save(coach);
    }
}
