package br.com.musicsentimental.servicer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicsentimental.model.User;
import br.com.musicsentimental.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);
        if (user != null && user.getSenha().equals(password)) {
            return user;
        }
        return null;
    }
}
