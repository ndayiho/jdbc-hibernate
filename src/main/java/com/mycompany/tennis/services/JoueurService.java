package com.mycompany.tennis.services;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;

public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur){
        joueurRepository.create(joueur);
    }

    public Joueur getJoueur(Long id){
        return joueurRepository.findById(id);
    }

}
