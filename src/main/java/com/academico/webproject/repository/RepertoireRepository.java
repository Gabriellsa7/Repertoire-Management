package com.academico.webproject.repository;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, String> {
    List<Repertoire> findByBand(Band band);
}
