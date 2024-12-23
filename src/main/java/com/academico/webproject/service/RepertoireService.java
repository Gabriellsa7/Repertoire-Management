package com.academico.webproject.service;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Repertoire;
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

    public Repertoire createRepertoire(Repertoire repertoire) {
        return repertoireRepository.save(repertoire);
    }

    public Repertoire updateRepertoire(String id, Repertoire repertoireDetails) {
        return  repertoireRepository.findById(id).map(repertoire -> {
            repertoire.setName(repertoire.getName());
            repertoire.setDescription(repertoire.getDescription());
            repertoire.setBand(repertoire.getBand());
            //think of a way to put the musicLinks here
            return repertoireRepository.save(repertoire);
        }).orElseThrow(() -> new RuntimeException("Repertoire not found"));
    }

    public void deleteRepertoire(String id) {
        repertoireRepository.deleteById(id);
    }

    public Optional<Repertoire> getRepertoireById(String id) {
        return repertoireRepository.findById(id);
    }

    public List<Repertoire> getAllRepertoire() {
        return repertoireRepository.findAll();
    }

    //think of a way to create the syncRepertoire
}
