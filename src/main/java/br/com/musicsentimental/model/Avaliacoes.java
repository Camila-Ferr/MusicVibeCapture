package br.com.musicsentimental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacoes")
public class Avaliacoes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
    @JoinColumn(name = "music_id")
	private Music musica;
	private Label sentimento;
	private String adicional;
	public Label getSentimento() {
		return sentimento;
	}
	public void setSentimento(Label sentimento) {
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
	public Music getMusica() {
		return musica;
	}
	public void setMusica(Music musica) {
		this.musica = musica;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}

}
