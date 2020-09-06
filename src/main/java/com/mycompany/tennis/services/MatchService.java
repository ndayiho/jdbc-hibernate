package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();

    }

    public void deleteMatch(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            matchRepository.delete(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void createMatch(MatchDto matchDto) {
        Transaction transaction = null;
        Match match = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            match = new Match();
            match.setEpreuve(epreuveRepository.findById(matchDto.getEpreuve().getId()));
            match.setVainqueur(joueurRepository.findById(matchDto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.findById(matchDto.getFinaliste().getId()));

            Score score=new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(matchDto.getScoreFullDto().getSet1());
            score.setSet2(matchDto.getScoreFullDto().getSet2());
            score.setSet3(matchDto.getScoreFullDto().getSet3());
            score.setSet4(matchDto.getScoreFullDto().getSet4());
            matchRepository.create(match);
            transaction.commit();
            System.out.println("Match created");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void tapisVert(Long id) {
        Transaction transaction = null;
        Match match = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            match = matchRepository.findById(id);
            Joueur ancienFinalist = match.getFinaliste();
            match.setFinaliste(match.getVainqueur());
            match.setVainqueur(ancienFinalist);
            match.getScore().setSet1((byte) 0);
            match.getScore().setSet2((byte) 0);
            match.getScore().setSet3((byte) 0);
            match.getScore().setSet4((byte) 0);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MatchDto getMatch(Long id) {
        MatchDto matchDto = null;
        Transaction transaction = null;
        Match match = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            match = matchRepository.findById(id);
            matchDto = new MatchDto();
            matchDto.setId(match.getId());
            matchDto.setId(match.getId());
            JoueurDto joueurFinalistDto = new JoueurDto();
            joueurFinalistDto.setId(match.getFinaliste().getId());
            joueurFinalistDto.setNom(match.getFinaliste().getNom());
            joueurFinalistDto.setPrenom(match.getFinaliste().getPrenom());
            joueurFinalistDto.setSexe(match.getFinaliste().getSexe());
            matchDto.setFinaliste(joueurFinalistDto);
            JoueurDto joueurVainqueur = new JoueurDto();
            joueurVainqueur.setId(match.getVainqueur().getId());
            joueurVainqueur.setNom(match.getVainqueur().getNom());
            joueurVainqueur.setPrenom(match.getVainqueur().getPrenom());
            joueurVainqueur.setSexe(match.getVainqueur().getSexe());
            matchDto.setVainqueur(joueurVainqueur);

            EpreuveFullDto epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setType(match.getEpreuve().getType());
            epreuveDto.setYear(match.getEpreuve().getYear());
            TournoiDto tournoiDto = new TournoiDto();
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
