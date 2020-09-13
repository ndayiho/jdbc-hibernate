package com.mycompany.tennis.services;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.EpreuveFullDto;
import com.mycompany.tennis.dto.EpreuveLightDto;
import com.mycompany.tennis.dto.JoueurDto;
import com.mycompany.tennis.dto.TournoiDto;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuveRepository;
    private List<EpreuveFullDto> epreuveFullDtos = new ArrayList<>();

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getEpreuveAvecTounoi(Long id) {
        Epreuve epreuve = null;
        Transaction transaction = null;
        EpreuveFullDto epreuveDto = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            epreuve = epreuveRepository.findById(id);
//            Hibernate.initialize(epreuve.getTournoi());
            epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(epreuve.getId());
            epreuveDto.setType(epreuve.getType());
            epreuveDto.setYear(epreuve.getYear());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);
            epreuveDto.setParticipants(new HashSet<>());
            for (Joueur jouer : epreuve.getParticipants()) {
                JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(jouer.getId());
                joueurDto.setSexe(jouer.getSexe());
                joueurDto.setNom(jouer.getNom());
                joueurDto.setPrenom(jouer.getPrenom());
                epreuveDto.getParticipants().add(joueurDto);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }

        }
        return epreuveDto;
    }

    public EpreuveLightDto getEpreuveSansTounoi(Long id) {
        Epreuve epreuve = null;
        Transaction transaction = null;
        EpreuveLightDto epreuveLightDto = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            epreuve = epreuveRepository.findById(id);
            epreuveLightDto = new EpreuveLightDto();
            epreuveLightDto.setId(epreuve.getId());
            epreuveLightDto.setType(epreuve.getType());
            epreuveLightDto.setYear(epreuve.getYear());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }

        }
        return epreuveLightDto;
    }

    public void createNewEpreuve(Epreuve epreuve) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            epreuveRepository.create(epreuve);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR --> rollback!!!!!!");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<EpreuveFullDto> getEpreuve(String codeTournoi) {
        List<Epreuve> epreuves = epreuveRepository.findAll(codeTournoi);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            for (Epreuve epreuve : epreuves) {
                EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
                epreuveFullDto.setId(epreuve.getId());
                epreuveFullDto.setType(epreuve.getType());
                TournoiDto tournoiDto = new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                epreuveFullDto.setTournoi(tournoiDto);
                epreuveFullDto.setParticipants(new HashSet<>());
                epreuveFullDtos.add(epreuveFullDto);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return epreuveFullDtos;
    }
}
