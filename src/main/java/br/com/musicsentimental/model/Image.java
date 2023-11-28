package br.com.musicsentimental.model;

public enum Image {
	 FELICIDADE(1, "happy"),
	 TRISTEZA(2, "sad"),
	 ROMANTICA(3, "romantic"),
	 AGRESSIVA(4, "agressive"),
	 DRAMATICA(5, "dramatic");
	
	private final long id;
	private final String caminho;
	
	Image(long id, String caminho) {
		this.id = id;
	    this.caminho = caminho;
	 }
	
	public static Image getById(long id) {
		
		for (Image image : values()) {
			if (image.getId() == id) {
				return image;
	        }
	    }
	   return null; 
	}



	public long getId() {
		return id;
	}
	public String getCaminho() {
		return caminho;
	}

}