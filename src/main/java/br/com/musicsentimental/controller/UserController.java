package br.com.musicsentimental.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.Avatar;
import br.com.musicsentimental.model.MoreInfo;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.model.UserDTO;
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
    
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> consultaPorId(@PathVariable Long id){
    	User user = repository.findById(id).get();
    	UserDTO dto = new UserDTO(user.getEmail(),user.getUsername());
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody User user) {
    	
    	if (userService.verificacao(user)) {
    		
    		MoreInfo moreInfo = new MoreInfo();
    		user.setMoreInfo(moreInfo);
    		moreRepository.save(moreInfo);
    		
    		repository.save(user);
    		
    		 return ResponseEntity.ok("Usuário cadastrado com sucesso");
    	}
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/returnInfo")
    public ResponseEntity<Object> returnInfo() throws MessagingException {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
    	UserDTO userReturn = new UserDTO(user.getEmail(), user.getUsuario(), user.getNome(), user.getNascimento(), user.getMusicExp(), user.getMoreInfo());
        return ResponseEntity.ok(userReturn);
    	
    }
    
    @PostMapping("/saveInfo")
    public ResponseEntity<Object> saveInfo(@RequestBody MoreInfo moreInfo) throws MessagingException {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Avatar avatar =  Avatar.getByCodigo(Integer.parseInt(moreInfo.getAvatar()));
    	User user = (User) authentication.getPrincipal();
    	
    	MoreInfo info = user.getMoreInfo();
    	info.setMoreInfo(moreInfo.getGenero(), moreInfo.getRegiao(), avatar.getRotulo(), moreInfo.getEstiloMusical(), moreInfo.getArtistasFavorito1(), moreInfo.getArtistasFavorito2(), moreInfo.getArtistasFavorito3(), moreInfo.getInstrumentos1(), moreInfo.getInstrumentos2(), moreInfo.getInstrumentos3(), moreInfo.getCuriosidade());
    	moreRepository.save(info);
    	return ResponseEntity.ok(info);
    }
}
   
    
  