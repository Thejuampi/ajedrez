package utils;

public enum PiezasEnum {
	
	REY(1,"R"),
	DAMA(2, "D"),
	TORRE(3,"T"),
	ALFIL(4, "A"), 
	CABALLO(5, "C"),
	PEON(6, "");
	
	PiezasEnum(int id, String nomenclatura){
		this.id = id;
		this.nomenclaruta = nomenclatura;
	}
	
	public final int id;
	public final String nomenclaruta;

}
