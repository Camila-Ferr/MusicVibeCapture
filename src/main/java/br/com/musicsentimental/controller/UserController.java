package br.com.musicsentimental.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.MoreInfo;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.MoreInfoRepository;
import br.com.musicsentimental.repository.UserRepository;
import br.com.musicsentimental.service.UserService;


@RestController
@RequestMapping(value = "/usuarios")
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private MoreInfoRepository moreRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private HttpSession session;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> consultaPorId(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }
    
    @PostMapping("/cadastrar")
    public User cadastrarUsuario(@RequestBody User user) {
    	
    	if (userService.verificacao(user)) {
    		
    		MoreInfo moreInfo = new MoreInfo();
    		user.setMoreInfo(moreInfo);
    		moreRepository.save(moreInfo);
    		
    		User savedUser = repository.save(user);
    		session.setAttribute("user", user);
    		
    		return savedUser;
    	}
    	return null;
    }
    
    @GetMapping("/returnInfo")
    public ResponseEntity<Object> returnInfo() throws MessagingException {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    	
    }
    
    @PostMapping("/saveInfo")
    public ResponseEntity<Object> saveInfo(@RequestBody MoreInfo moreInfo) throws MessagingException {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
    	
    	MoreInfo info = user.getMoreInfo();
    	info.setMoreInfo(moreInfo.getGenero(), moreInfo.getRegiao(), moreInfo.getAvatar(), moreInfo.getEstiloMusical(), moreInfo.getArtistasFavorito1(), moreInfo.getArtistasFavorito2(), moreInfo.getArtistasFavorito3(), moreInfo.getInstrumentos1(), moreInfo.getInstrumentos2(), moreInfo.getInstrumentos3(), moreInfo.getCuriosidade());
    	moreRepository.save(info);
    	return ResponseEntity.ok(info);
    }
}
   
    
  