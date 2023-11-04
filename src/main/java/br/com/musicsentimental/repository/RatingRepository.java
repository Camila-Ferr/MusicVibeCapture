package br.com.musicsentimental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.musicsentimental.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
