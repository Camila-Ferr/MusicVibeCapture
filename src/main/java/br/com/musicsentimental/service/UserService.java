package br.com.musicsentimental.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public static final String CURIOSIDADE = "Conte como começou sua história com a música, se você faz faculdade de música,se frequenta roda de samba, se gosta de cantar no chuveiro... Sinta-se livre"; 
    
    public boolean verificacao(User user) {

        User verificacaoEmail = userRepository.findByEmail(user.getEmail());
        User verificacaoUsuario = userRepository.findByUsuario(user.getUsuario());
        
        if (verificacaoEmail != null || verificacaoUsuario != null ) {
            return false;
        }
        return true;
    }
    
    public boolean validaInfo (User user) {
    	if (user.getEmail().isEmpty() || user.getNome().isEmpty() || user.getUsuario().isEmpty() || user.getPassword().isEmpty()) {
    		return false;
    	}
    	return true;
    }
    
    public String validaCuriosidade (String curiosidade) {
    	String curiosidadeReturn = curiosidade.equals(CURIOSIDADE)? "": curiosidade;
    	return curiosidadeReturn;
    }
    
}
