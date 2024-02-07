package br.com.musicsentimental.model.enums;

public enum Label {
	 FELICIDADE(1, "happy"),
	 TRISTEZA(2, "sad"),
	 ROMANTICA(3, "romantic"),
	 AGRESSIVA(4, "agressive"),
	 DRAMATICA(5, "dramatic"),
	 CALMA(6, "calm");
	
	private final int codigo;
	private final String rotulo;
	
	Label(int codigo, String rotulo) {
		this.codigo = codigo;
	    this.rotulo = rotulo;
	 }
	
	public int getCodigo() {
		return codigo;
	 }
	
	public String getRotulo() {
		return rotulo;
	}
	
	public static Label getByCodigo(int codigo) {
		
		for (Label sentimento : values()) {
			if (sentimento.getCodigo() == codigo) {
				return sentimento;
	        }
	    }
	   return null; 
	}

}
