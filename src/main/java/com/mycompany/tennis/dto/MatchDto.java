package com.mycompany.tennis.dto;

import com.mycompany.tennis.entity.Score;

public class MatchDto {
    private Long id;
    private EpreuveFullDto epreuveFullDto;
    private ScoreFullDto scoreFullDto;
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

    public EpreuveFullDto getEpreuve() {
        return epreuveFullDto;
    }

    public void setEpreuve(EpreuveFullDto epreuve) {
        this.epreuveFullDto = epreuve;
    }

    public EpreuveFullDto getEpreuveFullDto() {
        return epreuveFullDto;
    }

    public void setEpreuveFullDto(EpreuveFullDto epreuveFullDto) {
        this.epreuveFullDto = epreuveFullDto;
    }

    public ScoreFullDto getScoreFullDto() {
        return scoreFullDto;
    }

    public void setScoreFullDto(ScoreFullDto scoreFullDto) {
        this.scoreFullDto = scoreFullDto;
    }
}
