package utils;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum PiezasEnum {
	
	REY(0,"R"),
	DAMA(1, "D"),
	TORRE(2,"T"),
	ALFIL(3, "A"), 
	CABALLO(4, "C"),
	PEON(5, "");
	
	private static Map<String, PiezasEnum> mapaValoresByNombre = buildMapaByNombre();
	
	PiezasEnum(int id, String nomenclatura){
		this.id = id;
		this.nomenclatura = nomenclatura;
	}
	
	private static Map<String, PiezasEnum> buildMapaByNombre() {
		Builder<String, PiezasEnum> builder = ImmutableMap.builder();
		for(PiezasEnum value : values()) {
			builder.put(value.nomenclatura, value);
		}
		return builder.build();
	}

	public final int id;
	public final String nomenclatura;
	
	public static PiezasEnum getByNomenclatura(String nom) {
		return mapaValoresByNombre.get(nom);
	}

}
