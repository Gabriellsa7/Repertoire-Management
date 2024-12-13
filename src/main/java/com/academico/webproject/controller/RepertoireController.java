package com.academico.webproject.controller;

import com.academico.webproject.model.Repertoire;
import com.academico.webproject.service.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repertoire")
public class RepertoireController {

    @Autowired
    private RepertoireService repertoireService;

    @PostMapping
    public Repertoire createRepertoire(@RequestBody Repertoire repertoire) {
        return repertoireService.createRepertoire(repertoire);
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
}
