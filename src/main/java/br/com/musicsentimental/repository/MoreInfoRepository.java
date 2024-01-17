package br.com.musicsentimental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.musicsentimental.model.MoreInfo;
import br.com.musicsentimental.model.User;

@Repository
public interface MoreInfoRepository extends JpaRepository<MoreInfo, Long> {
    MoreInfo findByUser(User user);
}