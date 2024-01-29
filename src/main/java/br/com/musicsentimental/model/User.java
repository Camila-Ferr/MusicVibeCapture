package br.com.musicsentimental.model;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "usuario")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Email
	private String email;
	private String usuario;
	private String nome;
	private String senha;
	@Column(name = "nascimento", nullable = true)
	private Date nascimento;
	private String musicExp;
	@OneToOne
    @JoinColumn(name = "moreInfo_id")
	private MoreInfo moreInfo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		this.usuario= usuario.replaceAll("[^a-zA-Z0-9\\s@]", "");
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = codificarSenha(senha);
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public MoreInfo getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(MoreInfo moreInfo) {
		this.moreInfo = moreInfo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
    public String codificarSenha(String senha) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	return encoder.encode(senha);
    	
    }

}
