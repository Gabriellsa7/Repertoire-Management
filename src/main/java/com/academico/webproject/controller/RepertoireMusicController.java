package com.academico.webproject.controller;

import com.academico.webproject.model.Music;
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
            @RequestParam Integer order,
            @RequestHeader("Requester-Id") String requesterId) {

        RepertoireMusic newRepertoireMusic = repertoireMusicService.addMusicToRepertoire(musicId, repertoireId, order, requesterId);
        return ResponseEntity.ok(newRepertoireMusic);
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

    @GetMapping("/{repertoireId}/musics")
    public ResponseEntity<List<Music>> getAllMusicsInRepertoire(
            @PathVariable String repertoireId) {
        List<Music> musics = repertoireMusicService.findAllMusicsByRepertoire(repertoireId);
        return ResponseEntity.ok(musics);
    }

    @GetMapping("/{repertoireId}/find-music/{musicId}")
    public ResponseEntity<RepertoireMusic> findMusicInRepertoire(
            @PathVariable String repertoireId,
            @PathVariable String musicId) {
        RepertoireMusic repertoireMusic = repertoireMusicService.findMusicInRepertoire(repertoireId, musicId);
        return ResponseEntity.ok(repertoireMusic);
    }

    @GetMapping
    public ResponseEntity<List<RepertoireMusic>> getAllRepertoireMusics() {
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
