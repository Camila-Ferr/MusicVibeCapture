package br.com.musicsentimental.model.dto;

import java.sql.Date;

import br.com.musicsentimental.model.MoreInfo;

public class UserDTO {
	private String email;
	private String usuario;
	private String nome;
	private Date nascimento;
	private String musicExp;
	private MoreInfo moreInfo;
	
	public UserDTO(String email, String usuario, String nome, Date nascimento, String musicExp, MoreInfo moreInfo) {
		this.email = email;
		this.usuario = usuario;
		this.nome = nome;
		this.nascimento = nascimento;
		this.musicExp = musicExp;
		this.moreInfo = moreInfo;
	}
	public UserDTO(String email, String usuario) {
		this.email = email;
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getMusicExp() {
		return musicExp;
	}

	public void setMusicExp(String musicExp) {
		this.musicExp = musicExp;
	}

	public MoreInfo getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(MoreInfo moreInfo) {
		this.moreInfo = moreInfo;
	}

}
