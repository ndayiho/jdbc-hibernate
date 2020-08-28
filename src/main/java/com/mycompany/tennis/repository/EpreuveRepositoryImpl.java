package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import org.hibernate.Session;

public class EpreuveRepositoryImpl {

    public Epreuve findById(Long id) {
        //get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Epreuve.class, id);
    }

    public void create(Epreuve epreuve) {
        //get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(epreuve);
    }
}
