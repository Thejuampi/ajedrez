package ajedrez;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import utils.PiezasEnum;

public class PiezasManager {

	private Map<Posicion, Pieza> mapaPiezas = Maps.newHashMap();
	
	public static PiezasManager INSTANCE = new PiezasManager();
	
	private final Set<String> nomenclaturaPiezas = buildNomenclaturasSet();
	
	private PiezasManager(){}
	
	private Set<String> buildNomenclaturasSet() {
		Builder<String> builder = ImmutableSet.builder();
		for(PiezasEnum pieza : PiezasEnum.values()) {
			builder.add(pieza.nomenclaruta);
		}
		return builder.build();
	}

	public Map<Posicion, Pieza> getMapaPiezas() {
		return mapaPiezas;
	}
	
	public boolean addPieza(Posicion pos, Pieza pieza) {
		if(this.mapaPiezas.get(pos) == null){
			this.mapaPiezas.put(pos, pieza);
			return true;
		} else {
			return false;
		}
	}
	
	public Pieza getPieza(Posicion pos) {
		return this.mapaPiezas.get(pos);
	}
	
	public Pieza parse(String str) {
		Pieza result = null;
		if(str.length() > 0 && str.length() <= 3) {
			String primerLetra = str.substring(0, 1);
			String segundaLetra = str.substring(1,2);
			if(str.length() == 3){ // Caso general
				if(this.nomenclaturaPiezas.contains(primerLetra)){
					char letra = segundaLetra.charAt(0);
					if(letra >= 'a' && letra <= 'h') {
						char num = str.charAt(2);
						if(num >= '1' && num <='8'){
							Posicion pos = new Posicion(String.valueOf(num), segundaLetra);
							if(mapaPiezas.get(pos) != null) {
								
							}
						}
					}
				}
			} else if(str.length() == 2){ //posible peon
				
			}
		} 
		return result;
	}
	
}
