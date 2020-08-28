package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Score;
import org.hibernate.Session;

public class ScoreRepositoryImpl {

    public void create(Score score) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(score);
    }

    public Score findById(Long id) {
        //get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Score.class, id);
    }

}
