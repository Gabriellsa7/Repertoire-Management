package com.academico.webproject.controller;

import com.academico.webproject.model.RepertoireMusic;
import com.academico.webproject.service.RepertoireMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repertoire-music")
public class RepertoireMusicController {

    @Autowired
    private RepertoireMusicService repertoireMusicService;

    @PostMapping("/{repertoireId}/add-music/{musicId}")//?order=1
    public ResponseEntity<RepertoireMusic> addMusicToRepertoire(
            @PathVariable String repertoireId,
            @PathVariable String musicId,
            @RequestParam Integer order) {
        RepertoireMusic repertoireMusic = repertoireMusicService.addMusicToRepertoire(musicId, repertoireId, order);
        return ResponseEntity.ok(repertoireMusic);
    }

    @DeleteMapping("/{repertoireId}/remove-music/{musicId}")
    public ResponseEntity<Void> removeMusicFromRepertoire(
            @PathVariable String repertoireId,
            @PathVariable String musicId) {
        repertoireMusicService.removeMusicFromRepertoire(repertoireId, musicId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{repertoireId}/update-order/{musicId}")//?newOrder=2
    public ResponseEntity<RepertoireMusic> updateMusicOrder(
            @PathVariable String repertoireId,
            @PathVariable String musicId,
            @RequestParam Integer newOrder) {
        RepertoireMusic updatedRepertoireMusic = repertoireMusicService.updateMusicOrder(repertoireId, musicId, newOrder);
        return ResponseEntity.ok(updatedRepertoireMusic);
    }

    @GetMapping("/{repertoireId}/find-music/{musicId}")
    public ResponseEntity<RepertoireMusic> findMusicInRepertoire(
            @PathVariable String repertoireId,
            @PathVariable String musicId) {
        RepertoireMusic repertoireMusic = repertoireMusicService.findMusicInRepertoire(repertoireId, musicId);
        return ResponseEntity.ok(repertoireMusic);
    }

    @GetMapping
    public ResponseEntity<List<RepertoireMusic>> getAllBands() {
        List<RepertoireMusic> repertoireMusics = repertoireMusicService.getAllRepertoireMusic();
        return ResponseEntity.ok(repertoireMusics);
    }

    @GetMapping("/{repertoireId}/active-music")
    public ResponseEntity<List<RepertoireMusic>> getActiveMusicInRepertoire(@PathVariable String repertoireId) {
        List<RepertoireMusic> activeMusic = repertoireMusicService.getActiveMusicInRepertoire(repertoireId);
        return ResponseEntity.ok(activeMusic);
    }

    @PatchMapping("/{repertoireId}/{musicId}/status")//?isActive= false or true
    public ResponseEntity<RepertoireMusic> toggleMusicStatus(
            @PathVariable String repertoireId,
            @PathVariable String musicId,
            @RequestParam boolean isActive) {
        RepertoireMusic updatedRepertoireMusic = repertoireMusicService.toggleMusicStatus(repertoireId, musicId, isActive);
        return ResponseEntity.ok(updatedRepertoireMusic);
    }
}
