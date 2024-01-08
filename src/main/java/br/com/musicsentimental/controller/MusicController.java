package br.com.musicsentimental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.MusicRepository;
import br.com.musicsentimental.repository.RatingRepository;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;
    
    @Autowired
    private RatingRepository ratingRepository;
    
    @GetMapping("/musicsUsers")
    public ResponseEntity<List<Music>> avaliacoesPorUsuario(@SessionAttribute("user") User user) { 
        List<Music> musics = ratingRepository.findUnratedMusicByUser(user);
        
        if (!musics.isEmpty()) {
            return new ResponseEntity<>(musics, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}