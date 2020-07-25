package com.mycompany.tennis.services;

import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;

public class MatchService {
    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;

    public  MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void createMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

}
