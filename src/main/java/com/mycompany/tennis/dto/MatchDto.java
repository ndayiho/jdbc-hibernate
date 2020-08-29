package com.mycompany.tennis.dto;

import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Score;

public class MatchDto {
    private Long id;
    private Epreuve epreuve;
    private Score score;
    private JoueurDto vainqueur;
    private JoueurDto finaliste;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoueurDto getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDto vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDto getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDto finaliste) {
        this.finaliste = finaliste;
    }
}
