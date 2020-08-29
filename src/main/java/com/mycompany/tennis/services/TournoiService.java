package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.TournoiDto;
import com.mycompany.tennis.entity.Tournoi;
import com.mycompany.tennis.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;
    public TournoiService(){
        this.tournoiRepository=new TournoiRepositoryImpl();
    }

    public TournoiDto getTournoi(Long id){
        Transaction transaction = null;
        TournoiDto tournoiDto=null;
        Tournoi tournoi=null;
        try( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            //get session
            transaction = session.beginTransaction();
            tournoi=tournoiRepository.findById(id);
            tournoiDto=new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setNom(tournoi.getNom());
            tournoiDto.setCode(tournoi.getCode());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
       return tournoiDto;
    }

    public void createTournoi(TournoiDto tournoiDto){
        Transaction transaction = null;
        Tournoi tournoi=null;
        try( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            //get session
            transaction = session.beginTransaction();
            tournoi=new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());
            tournoiRepository.create(tournoi);
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
