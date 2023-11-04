package br.com.musicsentimental.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import br.com.musicsentimental.model.Label;
import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.Rating;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.RatingRepository;
import br.com.musicsentimental.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingRepository repository;
    
    @Autowired
    private RatingService ratingService;    
   


    @PostMapping("/saveAvaliacao")
    public ResponseEntity<Rating> cadastrarAvaliacao(@RequestBody Map<String, String> requestBody, @SessionAttribute("user") User user) {
    	Music music = ratingService.adicionaAvaliacao(requestBody.get("music"));
        Label label = Label.getByCodigo(Integer.parseInt(requestBody.get("label")));
        
        Rating rating = new Rating(user, music, label, "");
        Rating savedRating = repository.save(rating);
        
        return ResponseEntity.ok(savedRating);
    }
}