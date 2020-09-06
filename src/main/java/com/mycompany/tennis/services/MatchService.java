package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;

    public MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void createMatch(Match match) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            matchRepository.create(match);
            scoreRepository.create(match.getScore());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public MatchDto getMatch(Long id) {
        MatchDto matchDto = null;
        Transaction transaction =null;
        Match match=null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            match = matchRepository.findById(id);
            matchDto=new MatchDto();
            matchDto.setId(match.getId());
            matchDto.setId(match.getId());
            JoueurDto joueurFinalistDto=new JoueurDto();
            joueurFinalistDto.setId(match.getFinaliste().getId());
            joueurFinalistDto.setNom(match.getFinaliste().getNom());
            joueurFinalistDto.setPrenom(match.getFinaliste().getPrenom());
            joueurFinalistDto.setSexe(match.getFinaliste().getSexe());
            matchDto.setFinaliste(joueurFinalistDto);
            JoueurDto joueurVainqueur=new JoueurDto();
            joueurVainqueur.setId(match.getVainqueur().getId());
            joueurVainqueur.setNom(match.getVainqueur().getNom());
            joueurVainqueur.setPrenom(match.getVainqueur().getPrenom());
            joueurVainqueur.setSexe(match.getVainqueur().getSexe());
            matchDto.setVainqueur(joueurVainqueur);

            EpreuveFullDto epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setType(match.getEpreuve().getType());
            epreuveDto.setYear(match.getEpreuve().getYear());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);
            matchDto.setEpreuve(epreuveDto);

            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setMatchDto(matchDto);
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            matchDto.setScoreFullDto(scoreFullDto);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchDto;
    }

}
