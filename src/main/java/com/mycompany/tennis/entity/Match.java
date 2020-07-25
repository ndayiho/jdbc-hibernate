package com.mycompany.tennis.entity;

public class Match {
    private Long id;
    private Epreuve epreuve;
    private Score score;
    private Joueur vainqueur;
    private Joueur finaliste;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }
}
