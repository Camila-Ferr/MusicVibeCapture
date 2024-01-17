package br.com.musicsentimental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "musica")
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String link;
	private String nome;
	private int avaliacoes;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(int avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
