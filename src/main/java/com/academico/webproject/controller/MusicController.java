package com.academico.webproject.controller;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Music;
import com.academico.webproject.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<List<Music>> getAllMusics() {
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

    @PostMapping("/{id}/upload-pdf")
    public ResponseEntity<String> uploadPdf(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            musicService.uploadPdf(id, file);
            return ResponseEntity.ok("PDF sent successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending file");
        }
    }

    @GetMapping("/{id}/download-pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String id) {
        try {
            byte[] pdfData = musicService.getPdf(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=letter.pdf")
                    .body(pdfData);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<Music>> getUnassignedMusics() {
        List<Music> unassignedMusics = musicService.getUnassignedMusics();
        return ResponseEntity.ok(unassignedMusics);
    }
}
