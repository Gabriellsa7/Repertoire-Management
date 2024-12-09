package com.academico.webproject.service;

import com.academico.webproject.model.Music;
import com.academico.webproject.model.Repertoire;
import com.academico.webproject.model.RepertoireMusic;
import com.academico.webproject.repository.MusicRepository;
import com.academico.webproject.repository.RepertoireRepository;
import com.academico.webproject.repository.RepertoireMusicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class RepertoireMusicService {
    //methods: addMusicToRepertoire, removeMusicFromRepertoire, updateMusicOrder, getMusicListByRepertoire,
    //findMusicInRepertoire, reorderMusic
    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Autowired
    private RepertoireMusicRepository repertoireMusicRepository;

    public RepertoireMusic addMusicToRepertoire(String musicId, String repertoireId, Integer order) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId)
                .orElseThrow(() -> new EntityNotFoundException("Repertoire not found!"));

        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found!"));

        // Creates a new association between repertoire and music
        RepertoireMusic repertoireMusic = new RepertoireMusic();
        repertoireMusic.setRepertoire(repertoire);
        repertoireMusic.setMusic(music);
        repertoireMusic.setOrderInRepertoire(order);
        return repertoireMusicRepository.save(repertoireMusic);
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
                .orElseThrow(() -> new EntityNotFoundException("Música não encontrada no repertório!"));

        repertoireMusic.setOrderInRepertoire(newOrder);
        return repertoireMusicRepository.save(repertoireMusic);
    }

    public RepertoireMusic findMusicInRepertoire(String  repertoireId, String musicId) {
        return repertoireMusicRepository.findByRepertoireIdAndMusicId(repertoireId, musicId)
                .orElseThrow(() -> new EntityNotFoundException("Music not found in the specified repertoire."));
    }
}
