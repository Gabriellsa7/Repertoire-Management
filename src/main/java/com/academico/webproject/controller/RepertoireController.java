package com.academico.webproject.controller;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Repertoire;
import com.academico.webproject.service.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repertoire")
public class RepertoireController {

    @Autowired
    private RepertoireService repertoireService;

    @PostMapping
    public ResponseEntity<Repertoire> createRepertoire(@RequestBody Repertoire repertoire) {
        Repertoire createRepertoire = repertoireService.createRepertoire(repertoire);
        return ResponseEntity.ok(createRepertoire);
    }

    @GetMapping
    public ResponseEntity<List<Repertoire>> getAllMusics() {
        List<Repertoire> repertoires = repertoireService.getAllRepertoire();
        return ResponseEntity.ok(repertoires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repertoire> getRepertoireById(@PathVariable String id) {
        return repertoireService.getRepertoireById(id)
                .map(repertoire -> ResponseEntity.ok().body(repertoire))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepertoire(@PathVariable String id) {
        repertoireService.deleteRepertoire(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repertoire> updateRepertoire(@PathVariable String id, @RequestBody Repertoire repertoireDetails) {
        try {
            Repertoire updateRepertoire = repertoireService.updateRepertoire(id, repertoireDetails);
            return ResponseEntity.ok(updateRepertoire);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/band/{bandId}")
    public ResponseEntity<List<Repertoire>> getRepertoireByBandId(@PathVariable String bandId) {
        try {
            List<Repertoire> repertoires = repertoireService.getRepertoireByBandId(bandId);
            return ResponseEntity.ok(repertoires);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
