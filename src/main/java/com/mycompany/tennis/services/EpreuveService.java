package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
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
}
