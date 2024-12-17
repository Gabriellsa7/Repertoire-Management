package com.academico.webproject.controller;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.User;
import com.academico.webproject.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @PostMapping
    public ResponseEntity<Band> createBand(@RequestBody Band band) {
        Band createdBand = bandService.createBand(band);
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

    @PostMapping("/{bandId}/add-member/{userId}")
    public ResponseEntity<Band> addMemberToBand(@PathVariable String bandId, @PathVariable String userId) {
        Band updatedBand = bandService.addMemberToBand(bandId, userId);
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
}
