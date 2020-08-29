package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JoueurService {
    private final JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    public void create(Joueur joueur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            joueurRepository.create(joueur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Joueur get(Long id){
        Joueur joueur = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction transaction = session.beginTransaction();
            joueur=joueurRepository.findById(id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joueur;
    }

    public List<Joueur> getAll(){
        return joueurRepository.findAll();
    }

    public void rennomer(Long id, String nom) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Joueur joueur = session.get(Joueur.class, id);
            joueur.setNom(nom);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void changeSex(Long id, Character sexe) {
        Transaction transaction = null;
        try(Session session  = HibernateUtil.getSessionFactory().getCurrentSession()) {
            //get session
            transaction = session.beginTransaction();
            Joueur joueur = session.get(Joueur.class, id);
            joueur.setSexe(sexe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void delete( Long id) {
        Transaction transaction = null;
        try( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            //get session
            transaction = session.beginTransaction();
            joueurRepository.delete(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
