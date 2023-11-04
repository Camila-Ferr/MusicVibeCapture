package br.com.musicsentimental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.repository.MusicRepository;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/next")
    public ResponseEntity<Music> getMusicWithLowestRating() {
        Music music = musicRepository.findFirstByOrderByAvaliacoesAsc();
        if (music != null) {
            return new ResponseEntity<>(music, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/findById")
    public ResponseEntity<Music> getById() {
        Music music = musicRepository.findFirstByOrderByAvaliacoesAsc();
        if (music != null) {
            return new ResponseEntity<>(music, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}