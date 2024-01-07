package br.com.musicsentimental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.User;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Music findFirstByOrderByAvaliacoesAsc();
    
    Music findByLink(String link);


    

}

