package com.academico.webproject.repository;

import com.academico.webproject.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, String> {
    @Query("SELECT m FROM Music m LEFT JOIN m.repertoireLinks r WHERE r.id IS NULL")
    List<Music> findAllUnassignedMusics();
}
