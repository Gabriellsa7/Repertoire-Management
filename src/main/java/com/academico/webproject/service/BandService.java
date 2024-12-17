package com.academico.webproject.service;


import com.academico.webproject.model.Band;
import com.academico.webproject.model.User;
import com.academico.webproject.repository.BandRepository;
import com.academico.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BandService {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private UserRepository userRepository;

    public Band createBand(Band band) {
        return bandRepository.save(band);
    }

    public Optional<Band> getBandById(String bandId) {
        return bandRepository.findById(bandId);
    }

    public List<Band> getAllBands() {
        return bandRepository.findAll();
    }

    public Band updateBand(String id, Band updatedBand) {
        return bandRepository.findById(id).map(band -> {
            band.setName(updatedBand.getName());
            band.setLeader(updatedBand.getLeader());
            return bandRepository.save(band);
        }).orElseThrow(() -> new RuntimeException("Band not found"));
    }

    public void deleteBand(String id) {
        if (bandRepository.existsById(id)) {
            bandRepository.deleteById(id);
        } else {
            throw new RuntimeException("Band not found with ID: " + id);
        }
    }
}

