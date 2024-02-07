package br.com.musicsentimental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.RatingRepository;

@RestController
@RequestMapping("/music")
public class MusicController {
    
    @Autowired
    private RatingRepository ratingRepository;
    
    @GetMapping("/musicsUsers")
    public ResponseEntity<List<Music>> avaliacoesPorUsuario() { 
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
    	
        List<Music> musics = ratingRepository.findUnratedMusicByUser(user);
        
        if (!musics.isEmpty()) {
            return new ResponseEntity<>(musics, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}