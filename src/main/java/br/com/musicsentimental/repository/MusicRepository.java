package br.com.musicsentimental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.musicsentimental.model.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    
    Music findByLink(String link);
    

}

