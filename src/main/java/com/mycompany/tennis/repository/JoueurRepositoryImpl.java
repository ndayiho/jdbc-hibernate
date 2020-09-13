package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
    }


    public void delete(Long id) {
        final Joueur joueur = findById(id);
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
    }


    public Joueur findById(Long id) {
        //get session
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Joueur.class, id);
    }


    public List<Joueur> findAll() {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Query<Joueur> query = session.createQuery("select j from Joueur j", Joueur.class);
        return query.getResultList();
    }


    public List<Joueur> findAll(char sexe) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Query<Joueur> query = session.createQuery("select j from Joueur j where j.sexe=:SEXE", Joueur.class);
        query.setParameter("SEXE",sexe);
        return query.getResultList();
    }
}
