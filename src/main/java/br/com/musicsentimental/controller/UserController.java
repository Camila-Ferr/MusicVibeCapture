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

import br.com.musicsentimental.model.LoginRequest;
import br.com.musicsentimental.model.User;
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
    		User savedUser = repository.save(user);
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
    public ResponseEntity<Object> sendEmail() {
    	emailService.sendEmail("Teste", "Fooooiiiiii! Oiii Thi! Bjs");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @GetMapping("/forgotPassword")
    public ResponseEntity<Object> generatePassword(User user) throws MessagingException {
        
    	String password =  RandomStringUtils.random(12,true,true);
        user.setSenha(password);
        emailService.recuperaSenha(user);
        repository.save(user);
        
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
}