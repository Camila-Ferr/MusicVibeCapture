package br.com.musicsentimental.controller;

import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicsentimental.model.User;
import br.com.musicsentimental.model.dto.EmailDTO;
import br.com.musicsentimental.model.dto.NovaSenhaDto;
import br.com.musicsentimental.repository.UserRepository;
import br.com.musicsentimental.service.EmailService;


@RestController
@RequestMapping(value = "/mail")
public class MailController {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private HttpSession session;
    
    
    @PostMapping("/sendEmail")
    public ResponseEntity<Object> sendEmail(@RequestBody EmailDTO requestEmail) {
    	if (emailService.verificaEnvio(session.getAttribute("ultima_sugestao"))) {
	    	
    		if (requestEmail.corpo()!= "") {
	    		LocalDateTime time = emailService.sendEmail(requestEmail.assunto(),requestEmail.corpo());
	    		session.setAttribute("ultima_sugestao", time);
	    	}
	    	else {
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    	}
	        return ResponseEntity.status(HttpStatus.OK).build();
    	}
    	return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    
    @PostMapping("/forgetPassword")
    public ResponseEntity<Object> generatePassword(@RequestBody NovaSenhaDto requestEmail) throws MessagingException {
    	
    	if (session.getAttribute("ultimo_envio_count") == null) {
    	    session.setAttribute("ultimo_envio_count", 0);
    	}

    	
    	if (emailService.verificaEnvio(session.getAttribute("ultimo_envio")) || (emailService.verificaVezesEnvio((Integer) session.getAttribute("ultimo_envio_count")))) {

	    	User user = repository.findByEmail(requestEmail.email());
	    	if (user!= null) {
				String codigo = RandomStringUtils.random(12, true, true);
				session.setAttribute("codigo", codigo);
				
				LocalDateTime time = emailService.recuperaSenha(requestEmail.email(), codigo);
				session.setAttribute("ultimo_envio", time);
				session.setAttribute("ultimo_envio_count", (Integer) session.getAttribute("ultimo_envio_count")+1 );
				
				session.setAttribute("email", requestEmail.email());
				return ResponseEntity.status(HttpStatus.OK).build();
	    	}
	    	else {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	}
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
}
   
    
  