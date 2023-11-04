package br.com.musicsentimental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.repository.MusicRepository;

@Service
public class RatingService {
	
	@Autowired
    private MusicRepository musicRepository;
	
    public Music adicionaAvaliacao(String link) {
        Music music = musicRepository.findByLink(link);
        music.setAvaliacoes(music.getAvaliacoes()+1);
        musicRepository.save(music);
        return music;

    }
    
	
	
	

}
