package br.com.musicsentimental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.Rating;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.model.dto.RatingUserDTO;
import br.com.musicsentimental.model.enums.Label;
import br.com.musicsentimental.repository.MusicRepository;
import br.com.musicsentimental.repository.RatingRepository;
import br.com.musicsentimental.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingRepository repository;
    
    @Autowired
    private RatingService ratingService;    
    
    @Autowired
    private MusicRepository musicRepository;


    @PostMapping("/saveAvaliacao")
    public ResponseEntity<String> cadastrarAvaliacao(@RequestBody Map<String, String> requestBody) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
    	Music music = musicRepository.findByLink(requestBody.get("music"));
    	
    	if (ratingService.validaAvaliacao(music, user)) {
	    	ratingService.adicionaAvaliacao(music);
	        Label label = Label.getByCodigo(Integer.parseInt(requestBody.get("label")));
	        String adicional = requestBody.get("adicional");
        
	        Rating rating = new Rating(user, music, label, adicional);
	        repository.save(rating);
    	}
        
        return ResponseEntity.ok("Avaliação salva com sucesso");
        
    }
    
    @GetMapping("/rankingUsers")
    public ResponseEntity<ArrayList<RatingUserDTO>> rankingUsers(@RequestParam("page") int pageNumber) {
    	
    	Pageable pageable = PageRequest.of(pageNumber, 10); 
    	List<Object[]> results = repository.findTopUsers(pageable);
    	
    	ArrayList<RatingUserDTO> ranking = new ArrayList<>();
    	User user;
    	
    	for (Object[] result : results) {
    		user = (User) result[0];
    		RatingUserDTO usuario = new RatingUserDTO(user.getUsuario(), user.getMoreInfo().getAvatar(), (Long) result[1] );
    		ranking.add(usuario);
    		
        }
        return ResponseEntity.ok(ranking); 
    }
    
    @GetMapping("/totalRanking")
    public ResponseEntity<Long> total() {
    	
    	long results = repository.countDistinctUsers();
        return ResponseEntity.ok(results); 
    }
    
}