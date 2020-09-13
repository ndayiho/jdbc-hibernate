package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    public List<Epreuve> findAll(String codeTournoi) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Query<Epreuve> query = session.createQuery("select e from Epreuve e where e.tournoi.code=:CODE_TOURNOI", Epreuve.class);
        query.setParameter("CODE_TOURNOI",codeTournoi);
        return query.getResultList();
    }
}
