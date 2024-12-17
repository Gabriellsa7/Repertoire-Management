package com.academico.webproject.service;


import com.academico.webproject.model.Band;
import com.academico.webproject.model.BandMember;
import com.academico.webproject.model.User;
import com.academico.webproject.repository.BandMemberRepository;
import com.academico.webproject.repository.BandRepository;
import com.academico.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandMemberService {

    @Autowired
    private BandMemberRepository bandMemberRepository;

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private UserRepository userRepository;

    public BandMember addMemberToBand(String bandId, String userId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        BandMember bandMember = new BandMember(band, user);
        return bandMemberRepository.save(bandMember);
    }

    public void removeMemberFromBand(String bandId, String userId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found with ID: " + bandId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        BandMember bandMember = bandMemberRepository.findByBandIdAndUserId(bandId, userId)
                .orElseThrow(() -> new RuntimeException("Band member not found"));

        bandMemberRepository.delete(bandMember);
    }

    public List<BandMember> getMembersByBandId(String bandId) {
        return bandMemberRepository.findByBandId(bandId);
    }

    public List<BandMember> getMembersByUserId(String userId) {
        return bandMemberRepository.findByUserId(userId);
    }
}
