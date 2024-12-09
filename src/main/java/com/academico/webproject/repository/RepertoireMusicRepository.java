package com.academico.webproject.repository;

import com.academico.webproject.model.RepertoireMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepertoireMusicRepository extends JpaRepository<RepertoireMusic, String>{
    Optional<RepertoireMusic> findByRepertoireIdAndMusicId(String repertoireId, String musicId);
}
