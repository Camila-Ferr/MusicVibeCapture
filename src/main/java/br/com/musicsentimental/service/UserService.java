package br.com.musicsentimental.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);
        if (user != null && user.getSenha().equals(password)) {
            return user;
        }
        return null;
    }
    
    public boolean verificacao(User user) {

        User verificacaoEmail = userRepository.findByEmail(user.getEmail());
        User verificacaoUsuario = userRepository.findByUsuario(user.getUsuario());
        
        if (verificacaoEmail != null || verificacaoUsuario != null ) {
            return false;
        }
        return true;
    }
    
}
