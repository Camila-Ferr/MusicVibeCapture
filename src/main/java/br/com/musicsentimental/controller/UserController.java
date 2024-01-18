package br.com.musicsentimental.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.musicsentimental.model.EmailDTO;
import br.com.musicsentimental.model.LoginRequest;
import br.com.musicsentimental.model.MoreInfo;
import br.com.musicsentimental.model.NovaSenhaDto;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.MoreInfoRepository;
import br.com.musicsentimental.repository.UserRepository;
import br.com.musicsentimental.service.EmailService;
import br.com.musicsentimental.service.UserService;

@SessionAttributes("user")
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
    private EmailService emailService;
    
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
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
        	session.setAttribute("user", user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/getSession")
    public HttpSession getSession() {
        return session;
    }
    
    @GetMapping("/destroySession")
    public ResponseEntity<Object> invalidateSession() {
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PostMapping("/sendEmail")
    public ResponseEntity<Object> sendEmail(@RequestBody EmailDTO requestEmail) {
    	if (requestEmail.corpo()!= "") {
    		emailService.sendEmail(requestEmail.assunto(),requestEmail.corpo());
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PostMapping("/forgetPassword")
    public ResponseEntity<Object> generatePassword(@RequestBody NovaSenhaDto requestEmail) throws MessagingException {
    	
    	User user = repository.findByEmail(requestEmail.email());
    	if (user!= null) {
			String codigo = RandomStringUtils.random(12, true, true);
			emailService.recuperaSenha(requestEmail.email(), codigo);
			session.setAttribute("codigo", codigo);
			session.setAttribute("email", requestEmail.email());
			return ResponseEntity.status(HttpStatus.OK).build();
			
    	}
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	
    }
    
    @PostMapping("/confirmCode")
    public ResponseEntity<Object> confirmCode(@RequestBody NovaSenhaDto codigoDigitado) throws MessagingException {
    	String codigo = (String) session.getAttribute("codigo");
    	if (codigoDigitado.codigo().equals(codigo)) {
    		session.removeAttribute("codigo");
    		return ResponseEntity.status(HttpStatus.OK).build();
    	}
	    
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	
    }
    
    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody NovaSenhaDto senha) throws MessagingException {
    	User user = repository.findByEmail((String)session.getAttribute("email"));
    	if (user != null) {
	    	session.removeAttribute("email");
	    	user.setSenha(senha.novaSenha());
	    	repository.save(user);
	    	return ResponseEntity.status(HttpStatus.OK).build();
    	}
	    
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	
    }
    
    @GetMapping("/returnInfo")
    public ResponseEntity<Object> returnInfo() throws MessagingException {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(user);
    	
    }
    
    @PostMapping("/saveInfo")
    public ResponseEntity<Object> saveInfo(@RequestBody MoreInfo moreInfo) throws MessagingException {
    	User user = (User) session.getAttribute("user");
    	
    	MoreInfo info = user.getMoreInfo();
    	info.setMoreInfo(moreInfo.getGenero(), moreInfo.getRegiao(), moreInfo.getAvatar(), moreInfo.getEstiloMusical(), moreInfo.getArtistasFavorito1(), moreInfo.getArtistasFavorito2(), moreInfo.getArtistasFavorito3(), moreInfo.getInstrumentos1(), moreInfo.getInstrumentos2(), moreInfo.getArtistasFavorito3(), moreInfo.getCuriosidade());
    	moreRepository.save(info);
    	return ResponseEntity.ok(info);
    }
}
   
    
  