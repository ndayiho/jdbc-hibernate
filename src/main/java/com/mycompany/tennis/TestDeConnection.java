package com.mycompany.tennis;


import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Tournoi;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.services.JoueurService;

public class TestDeConnection {
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

    }
}

