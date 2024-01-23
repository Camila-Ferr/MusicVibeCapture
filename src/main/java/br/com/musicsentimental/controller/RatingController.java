package br.com.musicsentimental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.Label;
import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.Rating;
import br.com.musicsentimental.model.RatingUserDTO;
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
    public ResponseEntity<Rating> cadastrarAvaliacao(@RequestBody Map<String, String> requestBody) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
    	
    	Music music = ratingService.adicionaAvaliacao(requestBody.get("music"));
        Label label = Label.getByCodigo(Integer.parseInt(requestBody.get("label")));
        String adicional = requestBody.get("adicional");
        
        Rating rating = new Rating(user, music, label, adicional);
        Rating savedRating = repository.save(rating);
        
        return ResponseEntity.ok(savedRating);
    }
    
    @GetMapping("/rankingUsers")
    public ResponseEntity<ArrayList<RatingUserDTO>> rankingUsers() {
    	List<Object[]> results = repository.findTopUsers();
    	
    	ArrayList<RatingUserDTO> ranking = new ArrayList<>();
    	
    	for (Object[] result : results) {
    		RatingUserDTO usuario = new RatingUserDTO((User) result[0],(Long) result[1] );
    		ranking.add(usuario);
    		
        }
        return ResponseEntity.ok(ranking); 
    }
    
}