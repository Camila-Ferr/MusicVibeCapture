package br.com.musicsentimental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.MusicRepository;
import br.com.musicsentimental.repository.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
    private MusicRepository musicRepository;
	
	@Autowired
    private RatingRepository ratingRepository;
	
    public Music adicionaAvaliacao(Music music) {
        music.setAvaliacoes(music.getAvaliacoes()+1);
        musicRepository.save(music);
        return music;

    }
    
    public boolean validaAvaliacao(Music music, User user) {
    	
    	if (ratingRepository.existsByUserAndMusic(user, music)) {
    		return false;
    	}
    	return true;
    }
    

    
	
	
	

}
