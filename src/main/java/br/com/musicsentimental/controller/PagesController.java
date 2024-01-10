package br.com.musicsentimental.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.User;


@RestController
public class PagesController {
    @GetMapping("/**/*.html")
    public ResponseEntity<Object> redirectHtmlPages() {
        return ResponseEntity.notFound().build();
    }
	
	@GetMapping("/")
    public ResponseEntity<String> index() throws IOException {
		return getResource("static/index.html");
    }
	
    @GetMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute("user") User user) throws IOException {
    	if (user == null) {
    		return getResource("static/login.html");
    	}else {
    		return getResource("static/dashboard.html");
    	}
    }
    @GetMapping("/cadastro")
    public ResponseEntity<String> cadastro() throws IOException {
        return getResource("static/cadastro.html");
    }
    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(@ModelAttribute("user") User user) throws IOException {
    	 if (user != null) {
             return getResource("static/dashboard.html");
         } else {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
         }
    }
    
    
	
	private ResponseEntity<String> getResource(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        if (resource.exists()) {
            byte[] content = Files.readAllBytes(Path.of(resource.getURI()));
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(new String(content));
        } else {
            return ResponseEntity.notFound().build();
        }
	}

}
