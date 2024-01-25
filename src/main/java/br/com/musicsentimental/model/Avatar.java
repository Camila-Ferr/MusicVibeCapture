package br.com.musicsentimental.model;

public enum Avatar {
	 AVATAR1(1,"avatar-1.png"),
	 AVATAR2(2,"avatar-2.png"),
	 AVATAR3(3,"avatar-3.png"),
	 AVATAR4(4,"avatar-4.png"),
	 AVATAR5(5,"avatar-5.png"),
	 AVATAR6(6,"avatar-6.png"),
	 AVATAR7(7,"avatar-7.png"),
	 AVATAR8(8,"avatar-8.png"),
	 AVATAR9(9,"avatar-9.png");
	
	private final int codigo;
	private final String rotulo;
	
	Avatar (int codigo, String rotulo) {
		this.codigo = codigo;
	    this.rotulo = rotulo;
	 }
	
	public int getCodigo() {
		return codigo;
	 }
	
	public String getRotulo() {
		return rotulo;
	}
	
	public static Avatar getByCodigo(int codigo) {
		
		for (Avatar avatar : values()) {
			if (avatar.getCodigo() == codigo) {
				return avatar;
	        }
	    }
	   return null; 
	}

}
