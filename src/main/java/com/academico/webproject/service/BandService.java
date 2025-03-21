package com.academico.webproject.service;


import com.academico.webproject.model.Band;
import com.academico.webproject.model.Repertoire;
import com.academico.webproject.model.User;
import com.academico.webproject.repository.BandRepository;
import com.academico.webproject.repository.RepertoireRepository;
import com.academico.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BandService {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepertoireRepository repertoireRepository;

    public Band createBand(Band band) {
        User leader = userRepository.findById(band.getLeader().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        band.setLeader(leader);

        if (band.getMembers() == null) {
            band.setMembers(new HashSet<>());
        }

        band.getMembers().add(leader);

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
            band.setRepertoires(updatedBand.getRepertoires());
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

//    public Band addRepertoireToBand(String bandId, String repertoireId) {
//        Band band = bandRepository.findById(bandId)
//                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));
//        Repertoire repertoire = repertoireRepository.findById(repertoireId)
//                .orElseThrow(() -> new RuntimeException("Repertoire not found with ID: " + repertoireId));
//        repertoire.setBand(band);
//        band.getRepertoires().add(repertoire);
//        repertoireRepository.save(repertoire);
//        return bandRepository.save(band);
//    }

    public Band addMemberToBand(String bandId, String userId, String requesterId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        if(!band.getLeader().getId().equals(requesterId)) {
            throw new RuntimeException("Only the leader can add members to the band.");
        }

        band.getMembers().add(user);
        return bandRepository.save(band);
    }

    public Band removeMemberFromBand(String bandId, String userId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        band.getMembers().remove(user);
        return bandRepository.save(band);
    }

    public List<User> getMemberByBandId(String bandId) {
        Optional<Band> optionalBand = bandRepository.findByIdWithMembers(bandId);

        if (optionalBand.isPresent()) {
            Band band = optionalBand.get();
            System.out.println("Band found: " + band.getName());
            System.out.println("Members count: " + band.getMembers().size());
            return new ArrayList<>(band.getMembers());
        } else {
            throw new RuntimeException("Band not found with ID: " + bandId);
        }
    }

    public List<Band> getBandsByLeader(String leaderId) {
        return bandRepository.findByLeaderId(leaderId);
    }

    public List<Band> getBandsByMember(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return bandRepository.findByMembersContaining(user);
    }
}

