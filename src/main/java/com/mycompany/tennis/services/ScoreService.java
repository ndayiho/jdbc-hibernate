package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {
    private final ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public Score get(Long id) {
        Score score = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            score = scoreRepository.findById(id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }

}
