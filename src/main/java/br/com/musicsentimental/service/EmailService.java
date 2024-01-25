package br.com.musicsentimental.service;

import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    @Value("${spring.mail.to}")
    private String[] toEmails;

    @Value("${spring.assigned}")
    private String assigned;

    @Autowired
    private JavaMailSender javaMailSender;
    
    
    public LocalDateTime sendEmail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmails);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
        return (LocalDateTime.now());
    }
    
    public LocalDateTime recuperaSenha(String email, String codigo) throws MessagingException {
    	String htmlMessage = "Caro(a), <br/>"+
    	        "<br/> Como solicitado, foi gerado um código para possibilitar o reset de senha para o seu login.<br/>" +
    	        "Código gerado : <strong>" + codigo + "</strong> <br/>"+
    			"<br/><strong>" +assigned +"</strong> ";

    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

    	try {
    	    messageHelper.setTo(email); 
    	    messageHelper.setSubject("Recuperação de senha");
    	    messageHelper.setFrom("tcc.dataset@application.com");
    	    messageHelper.setText(htmlMessage, true); 
            javaMailSender.send(mimeMessage);
            
            
    	} catch (MessagingException e) {
    	    e.printStackTrace(); 
    	}
        return (LocalDateTime.now());
    }
    
    public boolean verificaEnvio(Object object) {
    	LocalDateTime ultimoEnvio = (LocalDateTime) object;
    	
        LocalDateTime agora = LocalDateTime.now();
        if (ultimoEnvio != null) {
        	LocalDateTime limite = ultimoEnvio.plusMinutes(30);
        	return agora.isAfter(limite);
        }
        return true;
    }

}
