package com.academico.webproject.repository;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, String> {
    List<Repertoire> findByBand(Band band);
    @Query("SELECT r FROM Repertoire r ORDER BY r.createdAt DESC LIMIT 1")
    Optional<Repertoire> findLatestRepertoire();
}
