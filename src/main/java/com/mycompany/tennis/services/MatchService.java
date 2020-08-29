package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.EpreuveLightDto;
import com.mycompany.tennis.dto.JoueurDto;
import com.mycompany.tennis.dto.MatchDto;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
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
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchDto;
    }

}
