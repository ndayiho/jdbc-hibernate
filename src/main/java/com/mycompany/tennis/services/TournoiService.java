package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Tournoi;
import com.mycompany.tennis.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;
    public TournoiService(){
        this.tournoiRepository=new TournoiRepositoryImpl();
    }

    public Tournoi getTournoi(Long id){
       return tournoiRepository.findById(id);
    }

    public void createTournoi(Tournoi tournoi){
         tournoiRepository.create(tournoi);
    }

    public void delete( Long id) {
        Transaction transaction = null;
        try( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            //get session
            transaction = session.beginTransaction();
            tournoiRepository.delete(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
