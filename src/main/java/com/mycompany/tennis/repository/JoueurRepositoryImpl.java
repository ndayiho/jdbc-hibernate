package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
    }


    public void delete(Long id) {
        final Joueur joueur = findById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
    }


    public Joueur findById(Long id) {
        //get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Joueur.class, id);
    }


    public List<Joueur> findAll() {
        Session session = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return joueurs;
    }
}
