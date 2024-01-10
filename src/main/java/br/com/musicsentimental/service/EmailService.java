package br.com.musicsentimental.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.musicsentimental.model.User;

@Service
public class EmailService {
	
    @Value("${spring.mail.to}")
    private String[] toEmails;

    @Value("${spring.assigned}")
    private String[] assigned;

    @Autowired
    private JavaMailSender javaMailSender;
    
    public void sendEmail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmails);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
    
    public void recuperaSenha(User user) throws MessagingException {
    	String htmlMessage = "Caro(a) " + user.getNome() +", <br/> <br/>"+
    	        " Como solicitado, foi gerada uma nova senha para o seu login.<br/>" +
    	        "Por favor, use <strong>" + user.getSenha() + "</strong> para logar novamente.<br/> <br/>"+
    			"<strong>" +assigned +"</strong> ";

    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

    	try {
    	    messageHelper.setTo(user.getEmail()); 
    	    messageHelper.setSubject("Recuperação de senha");
    	    messageHelper.setFrom("tcc.dataset@application.com");
    	    messageHelper.setText(htmlMessage, true); 
            javaMailSender.send(mimeMessage);
            
    	} catch (MessagingException e) {
    	    e.printStackTrace(); 
    	}
    }

}
