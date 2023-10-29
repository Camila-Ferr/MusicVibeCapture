package br.com.musicsentimental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import br.com.musicsentimental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}