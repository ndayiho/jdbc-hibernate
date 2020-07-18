package com.mycompany.tennis;


import com.mycompany.tennis.entity.Tournoi;
import com.mycompany.tennis.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.services.TournoiService;

public class TravauxPratique {
    public static void main(String... args) {
        TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();
        System.out.println("on a "+tournoiRepository.findAll().size()+": ");
        tournoiRepository.findAll().stream().forEach(tournoi -> System.out.println(tournoi.getId()+" "+tournoi.getCode()+" "+tournoi.getCode()));

        final TournoiService tournoiService=new TournoiService();
        System.out.println("le tournoi numero 1 est :"+tournoiService.getTournoi(1L).getNom());

        Tournoi tournoi=new Tournoi();
        tournoi.setNom("Championat rennes Fi");
        tournoi.setCode("FH");
        tournoiService.createTournoi(tournoi);

    }
}

