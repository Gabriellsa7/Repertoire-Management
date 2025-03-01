package com.academico.webproject.controller;

import com.academico.webproject.dto.BandRequest;
import com.academico.webproject.model.Band;
import com.academico.webproject.model.User;
import com.academico.webproject.repository.RepertoireRepository;
import com.academico.webproject.repository.UserRepository;
import com.academico.webproject.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepertoireRepository repertoireRepository;

    @PostMapping
    public ResponseEntity<Band> createBand(@RequestBody BandRequest bandRequest) {
        User leader = userRepository.findById(bandRequest.getLeaderId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Band newBand = new Band();
        newBand.setName(bandRequest.getName());
        newBand.setImageUrl(bandRequest.getImageUrl());
        newBand.setLeader(leader);

        Band createdBand = bandService.createBand(newBand);
        return ResponseEntity.ok(createdBand);
    }

    @GetMapping
    public ResponseEntity<List<Band>> getAllBands() {
        List<Band> bands = bandService.getAllBands();
        return ResponseEntity.ok(bands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> getBandById(@PathVariable String id) {
        return bandService.getBandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Band> updateBand(@PathVariable String id, @RequestBody Band updatedBand) {
        Band band = bandService.updateBand(id, updatedBand);
        return ResponseEntity.ok(band);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBand(@PathVariable String id) {
        bandService.deleteBand(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/{bandId}/add-repertoire/{repertoireId}")
//    public ResponseEntity<Band> addRepertoireToBand(@PathVariable String bandId, @PathVariable String repertoireId) {
//        Band updateBand = bandService.addRepertoireToBand(bandId, repertoireId);
//        return ResponseEntity.ok(updateBand);
//    }

    @PostMapping("/{bandId}/add-member/{userId}")
    public ResponseEntity<Band> addMemberToBand(@PathVariable String bandId, @PathVariable String userId, @RequestHeader("Requester-Id") String requesterId) {
        Band updatedBand = bandService.addMemberToBand(bandId, userId, requesterId);
        return ResponseEntity.ok(updatedBand);
    }

    @DeleteMapping("/{bandId}/remove-member/{userId}")
    public ResponseEntity<Band> removeMemberFromBand(@PathVariable String bandId, @PathVariable String userId) {
        Band updatedBand = bandService.removeMemberFromBand(bandId, userId);
        return ResponseEntity.ok(updatedBand);
    }

    @GetMapping("/{bandId}/members")
    public ResponseEntity<List<User>> getMembersByBandId(@PathVariable String bandId) {
        List<User> members = bandService.getMemberByBandId(bandId);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/leader/{leaderId}")
    public ResponseEntity<List<Band>> getBandsByLeader(@PathVariable String leaderId) {
        List<Band> bands = bandService.getBandsByLeader(leaderId);
        return ResponseEntity.ok(bands);
    }

    @GetMapping("/member/{userId}")
    public ResponseEntity<List<Band>> getBandsByMember(@PathVariable String userId) {
        List<Band> bands = bandService.getBandsByMember(userId);
        return ResponseEntity.ok(bands);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

}
