package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuverepository;

    public EpreuveService() {
        this.epreuverepository = new EpreuveRepositoryImpl();
    }

    public Epreuve get(Long id) {
        Epreuve epreuve = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            epreuve = epreuverepository.findById(id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return epreuve;
    }

    public void createNewEpreuve(Epreuve epreuve) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            epreuverepository.create(epreuve);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
