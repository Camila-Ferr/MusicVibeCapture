package br.com.musicsentimental.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.musicsentimental.model.Music;
import br.com.musicsentimental.model.Rating;
import br.com.musicsentimental.model.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	 @Query("SELECT m FROM Music m WHERE m NOT IN (SELECT r.music FROM Rating r WHERE r.user = :user) ORDER BY m.avaliacoes ASC")
	 List<Music> findUnratedMusicByUser(@Param("user") User user);
	 
	 @Query("SELECT a.user as user, COUNT(a.user) as quantidade FROM Rating a GROUP BY a.user ORDER BY COUNT(a.user) DESC, a.user.id ASC")
	 Page<Object[]> findTopUsers(Pageable pageable);
	 
	 boolean existsByUserAndMusic(User user, Music music);
	 
	 @Query("SELECT COUNT(DISTINCT user) FROM Rating u")
	 long countDistinctUsers();


}
