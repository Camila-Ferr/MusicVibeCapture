package br.com.musicsentimental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.UserRepository;

@Component
public class UserDetailsServ implements UserDetailsService{
	
    @Autowired
    private UserRepository userRepository;
    
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("Usuário não encontrado");
	        }
	        return user;
	    }

}
