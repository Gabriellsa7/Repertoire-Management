package com.academico.webproject.repository;

import com.academico.webproject.model.RepertoireMusic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepertoireMusicRepository extends JpaRepository<RepertoireMusic, String>{
    List<RepertoireMusic> findByRepertoireIdAndIsActiveTrue(String repertoireId);

    List<RepertoireMusic> findAllByRepertoireId(String repertoireId);

    @Transactional
    @Modifying
    @Query("DELETE FROM RepertoireMusic rm WHERE rm.repertoire.id = :repertoireId")
    void deleteByRepertoireId(@Param("repertoireId") String repertoireId);

    @Transactional
    @Modifying
    @Query("DELETE FROM RepertoireMusic rm WHERE rm.music.id = :musicId")
    void deleteByMusicId(@Param("musicId") String musicId);

    Optional<RepertoireMusic> findByRepertoireIdAndMusicId(String repertoireId, String musicId);
}
