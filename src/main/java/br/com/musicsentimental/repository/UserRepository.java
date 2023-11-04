package br.com.musicsentimental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.musicsentimental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByUsuario(String usuario);
	
}