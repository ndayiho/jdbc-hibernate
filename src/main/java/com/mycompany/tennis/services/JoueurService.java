package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.JoueurDto;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    private final JoueurRepositoryImpl joueurRepository;
    private List<JoueurDto> joeuersDto = new ArrayList<>();

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

    public Joueur get(Long id) {
        Joueur joueur = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            joueur = joueurRepository.findById(id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joueur;
    }

    public List<JoueurDto> getJoueurs() {
        List<Joueur> joueurs = joueurRepository.findAll();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (Joueur joueur : joueurs) {
                JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                joeuersDto.add(joueurDto);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return joeuersDto;
    }

    public void rennomer(Long id, String nom) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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


    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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
