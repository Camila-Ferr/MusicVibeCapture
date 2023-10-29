package br.com.musicsentimental.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.LoginRequest;
import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.UserRepository;
import br.com.musicsentimental.servicer.AuthService;

@RestController
@RequestMapping(value = "/usuarios")
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private HttpSession session;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> consultaPorId(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }
    
    @PostMapping("/cadastrar")
    public User cadastrarUsuario(@RequestBody User user) {
        User savedUser = repository.save(user);
        return savedUser;
    }
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
        	session.setAttribute("user", user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/secure-page")
    public ResponseEntity<String> securePage(@RequestBody HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok("Esta é uma página segura para " + user.getUsuario());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
        }
    }
}