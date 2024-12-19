package com.academico.webproject.controller;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Music;
import com.academico.webproject.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {

    // do the get all here

    @Autowired
    private MusicService musicService;

    @PostMapping
    public ResponseEntity<Music> createMusic(@RequestBody Music music) {
        Music createMusic = musicService.createMusic(music);
        return ResponseEntity.ok(createMusic);
    }

    @GetMapping
    public ResponseEntity<List<Music>> getAllBands() {
        List<Music> musics = musicService.getAllMusics();
        return ResponseEntity.ok(musics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable String id) {
        return musicService.getMusicById(id)
                .map(music -> ResponseEntity.ok().body(music))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable String id) {
        musicService.deleteMusic(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Music> updateMusic(@PathVariable String id, @RequestBody Music musicDetails){
        try {
            Music updateMusic = musicService.updateMusic(id, musicDetails);
            return ResponseEntity.ok(updateMusic);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
