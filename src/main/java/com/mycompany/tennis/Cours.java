package com.mycompany.tennis;


import com.mycompany.tennis.entity.*;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.services.JoueurService;
import com.mycompany.tennis.services.MatchService;

public class Cours {
    public static void main(String... args) {
        JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
        //getall gamers
        joueurRepository.findAll();
        //get honore
        Joueur honore=joueurRepository.findById(45l);
        //update Honore
        honore.setNom("Gasana");
        joueurRepository.update(honore);
//        joueurRepository.delete(46l);
        joueurRepository.delete(48l);
        JoueurService joueurService=new JoueurService();
 /*       Joueur kampire=new Joueur();
        kampire.setNom("Kampire");
        kampire.setPrenom("Anastasie");
        kampire.setSexe('F');
        joueurService.createJoueur(kampire);*/
//
//        MatchService matchService=new MatchService();
//        Match match=new Match();
//        Score score =new Score();
//        score.setSet1((byte)3);
//        score.setSet2((byte)4);
//        score.setSet3((byte)6);
//        match.setScore(score);
//        score.setMatch(match);
//        Joueur federer= new Joueur();
//        federer.setId(32L);
//        Joueur murray= new Joueur();
//        murray.setId(34L);
//        match.setVainqueur(federer);
//        match.setFinaliste(murray);
//        Epreuve epreuve=new Epreuve();
//        epreuve.setId(10l);
//        match.setEpreuve(epreuve);
//        matchService.createMatch(match);
        joueurService.getJoueur(9L);

    }
}
