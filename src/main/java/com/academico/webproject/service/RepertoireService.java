package com.academico.webproject.service;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Repertoire;
import com.academico.webproject.repository.BandRepository;
import com.academico.webproject.repository.RepertoireMusicRepository;
import com.academico.webproject.repository.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepertoireService {
    //Methods: createRepertoire, updateRepertoire, deleteRepertoire
    //getRepertoireById, syncRepertoire

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private RepertoireMusicRepository repertoireMusicRepository;

    public Repertoire createRepertoire(Repertoire repertoire) {
        return repertoireRepository.save(repertoire);
    }

    public Repertoire updateRepertoire(String id, Repertoire repertoireDetails) {
        return  repertoireRepository.findById(id).map(repertoire -> {
            repertoire.setName(repertoire.getName());
            repertoire.setDescription(repertoire.getDescription());
            repertoire.setBand(repertoire.getBand());
            repertoire.setImageUrl(repertoire.getImageUrl());
            //think of a way to put the musicLinks here
            return repertoireRepository.save(repertoire);
        }).orElseThrow(() -> new RuntimeException("Repertoire not found"));
    }

    public Repertoire assignBandToRepertoire(String repertoireId, String bandId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));

        Repertoire repertoire = repertoireRepository.findById(repertoireId)
                .orElseThrow(() -> new RuntimeException("Repertoire not found with ID: " + repertoireId));

        repertoire.setBand(band);
        return repertoireRepository.save(repertoire);
    }

    public void deleteRepertoire(String id) {
        repertoireMusicRepository.deleteByRepertoireId(id);
        repertoireRepository.deleteById(id);
    }

    public Optional<Repertoire> getRepertoireById(String id) {
        return repertoireRepository.findById(id);
    }

    public List<Repertoire> getAllRepertoire() {
        return repertoireRepository.findAll();
    }

    public List<Repertoire> getRepertoireByBandId(String bandId) {
        Optional<Band> band = bandRepository.findById(bandId);
        if (band.isPresent()) {
            return repertoireRepository.findByBand(band.get());
        } else {
            throw new RuntimeException("Band not found");
        }
    }

    public Optional<Repertoire> getLatestRepertoire() {
        return repertoireRepository.findLatestRepertoire();
    }

    //think of a way to create the syncRepertoire
}
