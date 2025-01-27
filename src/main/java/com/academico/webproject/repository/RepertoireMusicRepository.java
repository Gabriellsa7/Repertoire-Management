package com.academico.webproject.repository;

import com.academico.webproject.model.RepertoireMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepertoireMusicRepository extends JpaRepository<RepertoireMusic, String>{
    List<RepertoireMusic> findByRepertoireIdAndIsActiveTrue(String repertoireId);

    List<RepertoireMusic> findAllByRepertoireId(String repertoireId);

    Optional<RepertoireMusic> findByRepertoireIdAndMusicId(String repertoireId, String musicId);
}
