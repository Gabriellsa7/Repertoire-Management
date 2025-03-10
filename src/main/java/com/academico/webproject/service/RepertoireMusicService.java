package com.academico.webproject.service;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.Music;
import com.academico.webproject.model.Repertoire;
import com.academico.webproject.model.RepertoireMusic;
import com.academico.webproject.repository.MusicRepository;
import com.academico.webproject.repository.RepertoireRepository;
import com.academico.webproject.repository.RepertoireMusicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepertoireMusicService {
    //methods: addMusicToRepertoire, removeMusicFromRepertoire, updateMusicOrder, getMusicListByRepertoire,
    //findMusicInRepertoire, reorderMusic
    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Autowired
    private RepertoireMusicRepository repertoireMusicRepository;

    public RepertoireMusic addMusicToRepertoire(String musicId, String repertoireId, Integer order, String requesterId) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId)
                .orElseThrow(() -> new EntityNotFoundException("Repertoire not found!"));

        Band band = repertoire.getBand();
        if (band == null) {
            throw new RuntimeException("This repertoire is not assigned to any band.");
        }

        if (!band.getLeader().getId().equals(requesterId)) {
            throw new RuntimeException("Only the leader can add music to the repertoire.");
        }

        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found!"));

        RepertoireMusic repertoireMusic = new RepertoireMusic();
        repertoireMusic.setRepertoire(repertoire);
        repertoireMusic.setMusic(music);
        repertoireMusic.setOrderInRepertoire(order);

        return repertoireMusicRepository.save(repertoireMusic);
    }


    public List<Music> findAllMusicsByRepertoire(String repertoireId) {
        // Aqui, você chama o repositório para buscar as músicas relacionadas ao repertório
        return repertoireMusicRepository.findAllByRepertoireId(repertoireId)
                .stream()
                .map(RepertoireMusic::getMusic) // Extrair a entidade Music
                .collect(Collectors.toList());
    }


    public List<RepertoireMusic> getAllRepertoireMusic() {
        return repertoireMusicRepository.findAll();
    }

    public void removeMusicFromRepertoire(String repertoireId, String musicId) {
        // Seeks the Repertoire-Music association through Repertoire and Music
        RepertoireMusic repertoireMusic = repertoireMusicRepository
                .findByRepertoireIdAndMusicId(repertoireId, musicId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found in repertoire!"));

        // Remove association
        repertoireMusicRepository.delete(repertoireMusic);
    }

    public RepertoireMusic updateMusicOrder(String repertoireId, String musicId, Integer newOrder) {
        // Checks whether the association between repertoire and music exists
        RepertoireMusic repertoireMusic = repertoireMusicRepository
                .findByRepertoireIdAndMusicId(repertoireId, musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found in repertoire.!"));

        repertoireMusic.setOrderInRepertoire(newOrder);
        return repertoireMusicRepository.save(repertoireMusic);
    }

    public RepertoireMusic findMusicInRepertoire(String  repertoireId, String musicId) {
        return repertoireMusicRepository.findByRepertoireIdAndMusicId(repertoireId, musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found in the specified repertoire."));
    }

    public List<RepertoireMusic> getActiveMusicInRepertoire(String repertoireId) {
        return repertoireMusicRepository.findByRepertoireIdAndIsActiveTrue(repertoireId);
    }

    public RepertoireMusic toggleMusicStatus(String repertoireId, String musicId, boolean isActive) {
        RepertoireMusic repertoireMusic = repertoireMusicRepository
                .findByRepertoireIdAndMusicId(repertoireId, musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found in the specified repertoire."));

        repertoireMusic.setActive(isActive);
        return repertoireMusicRepository.save(repertoireMusic);
    }
}
