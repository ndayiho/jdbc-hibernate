package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.MatchDto;
import com.mycompany.tennis.entity.Match;
import org.hibernate.Session;

public class MatchRepositoryImpl {

    public void create(Match match) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
    }

    public Match findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return  session.get(Match.class,id);
    }

}
