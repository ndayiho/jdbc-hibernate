package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {
    private final ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void deleteScore(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            scoreRepository.delete(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ScoreFullDto getFullScore(Long id) {
        Score score = null;
        ScoreFullDto scoreFullDto = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            score = scoreRepository.findById(id);
            scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());

            MatchDto matchDto=new MatchDto();
            matchDto.setId(score.getMatch().getId());
            matchDto.setId(score.getMatch().getId());
            JoueurDto joueurFinalistDto=new JoueurDto();
            joueurFinalistDto.setId(score.getMatch().getFinaliste().getId());
            joueurFinalistDto.setNom(score.getMatch().getFinaliste().getNom());
            joueurFinalistDto.setPrenom(score.getMatch().getFinaliste().getPrenom());
            joueurFinalistDto.setSexe(score.getMatch().getFinaliste().getSexe());
            matchDto.setFinaliste(joueurFinalistDto);
            JoueurDto joueurVainqueur=new JoueurDto();
            joueurVainqueur.setId(score.getMatch().getVainqueur().getId());
            joueurVainqueur.setNom(score.getMatch().getVainqueur().getNom());
            joueurVainqueur.setPrenom(score.getMatch().getVainqueur().getPrenom());
            joueurVainqueur.setSexe(score.getMatch().getVainqueur().getSexe());
            matchDto.setVainqueur(joueurVainqueur);

            EpreuveFullDto epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(score.getMatch().getEpreuve().getId());
            epreuveDto.setType(score.getMatch().getEpreuve().getType());
            epreuveDto.setYear(score.getMatch().getEpreuve().getYear());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);
            matchDto.setEpreuve(epreuveDto);
            scoreFullDto.setMatchDto(matchDto);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreFullDto;
    }

}
