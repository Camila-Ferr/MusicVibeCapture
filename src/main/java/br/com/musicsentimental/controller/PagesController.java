package br.com.musicsentimental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {
	
	@GetMapping("/")
    public String index() {
        return "index.html";
    }
	
	@GetMapping("/login")
    public String login() {
        return "login.html";
    }

}
