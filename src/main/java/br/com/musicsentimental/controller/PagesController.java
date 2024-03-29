package br.com.musicsentimental.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<String> login() throws IOException {
    	return getResource("static/login.html");
    }

    @GetMapping("/cadastro")
    public ResponseEntity<String> cadastro() throws IOException {
        return getResource("static/cadastro.html");
    }
    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard() throws IOException {
         	return getResource("static/dashboard.html");
    }
    @GetMapping("/ranking")
    public ResponseEntity<String> ranking() throws IOException {
         	return getResource("static/ranking.html");
    }
    @GetMapping("/profile")
    public ResponseEntity<String> profile() throws IOException {
         	return getResource("static/profile.html");
    }
    @GetMapping("/sobre")
    public ResponseEntity<String> sobre() throws IOException {
         	return getResource("static/sobre.html");
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
