package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;

    public MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void createMatch(Match match) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            matchRepository.create(match);
            scoreRepository.create(match.getScore());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Match get(Long id) {
        Match match = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            match = matchRepository.findById(id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }

}
