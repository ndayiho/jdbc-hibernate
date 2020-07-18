package com.mycompany.tennis.services;

import com.mycompany.tennis.entity.Tournoi;
import com.mycompany.tennis.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;
    public TournoiService(){
        this.tournoiRepository=new TournoiRepositoryImpl();
    }

    public Tournoi getTournoi(Long id){
       return tournoiRepository.findById(id);
    }

    public void createTournoi(Tournoi tournoi){
         tournoiRepository.create(tournoi);
    }


}
