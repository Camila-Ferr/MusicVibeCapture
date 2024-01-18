package br.com.musicsentimental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_adicionais")
public class MoreInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String genero;
    private String regiao;
    private String avatar;
    private String estiloMusical;
    private String artistasFavorito1;
    private String artistasFavorito2;
    private String artistasFavorito3;
	private String instrumentos1;
	private String instrumentos2;
	private String instrumentos3;
	private String curiosidade;
	
    public String getInstrumentos1() {
		return instrumentos1;
	}
	public void setInstrumentos1(String instrumentos1) {
		this.instrumentos1 = instrumentos1;
	}
	public String getInstrumentos2() {
		return instrumentos2;
	}
	public void setInstrumentos2(String instrumentos2) {
		this.instrumentos2 = instrumentos2;
	}
	public String getInstrumentos3() {
		return instrumentos3;
	}
	public void setInstrumentos3(String instrumentos3) {
		this.instrumentos3 = instrumentos3;
	}
	public String getCuriosidade() {
		return curiosidade;
	}
	public void setCuriosidade(String curiosidade) {
		this.curiosidade = curiosidade;
	}

    public String getArtistasFavorito1() {
		return artistasFavorito1;
	}
	public void setArtistasFavorito1(String artistasFavorito1) {
		this.artistasFavorito1 = artistasFavorito1;
	}
	public String getArtistasFavorito2() {
		return artistasFavorito2;
	}
	public void setArtistasFavorito2(String artistasFavorito2) {
		this.artistasFavorito2 = artistasFavorito2;
	}
	public String getArtistasFavorito3() {
		return artistasFavorito3;
	}
	public void setArtistasFavorito3(String artistasFavorito3) {
		this.artistasFavorito3 = artistasFavorito3;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEstiloMusical() {
		return estiloMusical;
	}
	public void setEstiloMusical(String estiloMusical) {
		this.estiloMusical = estiloMusical;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	
	public void setMoreInfo( String genero,String regiao,String avatar,String estiloMusical,String artistasFavorito1,String artistasFavorito2,String artistasFavorito3, String instrumentos1,String instrumentos2,String instrumentos3,String curiosidade) {
		this.genero = genero;
		this.regiao = regiao;
		this.avatar = avatar;
		this.estiloMusical = estiloMusical;
		this.artistasFavorito1 = artistasFavorito1;
		this.artistasFavorito2 = artistasFavorito2;
		this.artistasFavorito3 = artistasFavorito3;
		this.instrumentos1 = instrumentos1;
		this.instrumentos2 = instrumentos2;
		this.instrumentos3 = instrumentos3;
		this.curiosidade=curiosidade;
	}
    
	

}
