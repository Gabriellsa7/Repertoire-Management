package com.academico.webproject.service;

import com.academico.webproject.model.Music;
import com.academico.webproject.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public Music createMusic(Music music) {
        return  musicRepository.save(music);
    }

    public Optional<Music> getMusicById(String MusicId) {
        return musicRepository.findById(MusicId);
    }

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Music updateMusic(String id, Music updateMusic) {
        //The repository's findById(id) method returns an Optional<Music>
        //Present: If the song with the given ID is found. Empty: If no song with that ID exists.
        return musicRepository.findById(id).map(music -> {
            music.setTitle(updateMusic.getTitle());
            //Search about how to send PDF to site
            music.setRepertoireLinks(updateMusic.getRepertoireLinks());
            return musicRepository.save(music);
        }).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public void deleteMusic(String id) {
        if (musicRepository.existsById(id)) {
            musicRepository.deleteById(id);
        } else {
            throw new RuntimeException("Music not found with ID: " + id);
        }
    }

    public Music uploadPdf(String musicId, MultipartFile file) throws IOException {
        return musicRepository.findById(musicId).map(music -> {
            try {
                music.setPdfFile(file.getBytes());
                return musicRepository.save(music);
            } catch (IOException e) {
                throw new RuntimeException("Error Save PDF", e);
            }
        }).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public byte[] getPdf(String musicId) {
        return musicRepository.findById(musicId)
                .map(Music::getPdfFile)
                .orElseThrow(() -> new RuntimeException("PDF not found"));
    }
}

