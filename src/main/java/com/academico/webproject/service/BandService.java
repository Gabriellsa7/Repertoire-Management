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

    public Band updateBand(String bandId, Band updatedBand) {
        Optional<Band> existingBand = bandRepository.findById(bandId);

        if (existingBand.isPresent()) {
            Band band = existingBand.get();
            band.setName(updatedBand.getName());
            return bandRepository.save(band);
        } else {
            throw new RuntimeException("Band not found with ID: " + bandId);
        }
    }

    public void deleteBand(String bandId) {
        if (bandRepository.existsById(bandId)) {
            bandRepository.deleteById(bandId);
        } else {
            throw new RuntimeException("Band not found with ID: " + bandId);
        }
    }

    public Band addMusicianToBand(String bandId, String userId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        band.getMembers().add(user);
        return bandRepository.save(band);
    }

    public Band removeMusicianFromBand(String bandId, String userId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        band.getMembers().remove(user);
        return bandRepository.save(band);
    }

    public List<User> getMembersByBandId(String bandId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));

        // Convert Set<User> to List<User>
        return new ArrayList<>(band.getMembers());
    }
}
