package br.com.musicsentimental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.musicsentimental.model.enums.Label;

@Entity
@Table(name = "avaliacao")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
    @JoinColumn(name = "music_id")
	private Music music;
	
	@Column(name = "label_rotulo")
	private String sentimento;
	@Column(name = "adicional", nullable = true)
	private String adicional;
	
	public Rating (User user, Music music, Label sentimento, String adicional) {
		this.user = user;
		this.music = music;
		this.sentimento = sentimento.getRotulo();
		this.adicional = adicional;
	}
	
	public String getSentimento() {
		return sentimento;
	}
	public void setSentimento(String sentimento) {
		this.sentimento = sentimento;
	}
	public String getAdicional() {
		return adicional;
	}
	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music musica) {
		this.music = musica;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}

}
