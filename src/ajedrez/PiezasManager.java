package ajedrez;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Maps;

import utils.PiezasEnum;

public class PiezasManager {

	private Map<Posicion, Pieza> mapaPiezas = Maps.newHashMap();
	
	public static PiezasManager INSTANCE = new PiezasManager();

	private final Set<String> nomenclaturaPiezas = buildNomenclaturasSet();

	private PiezasManager() {
	}

	private Set<String> buildNomenclaturasSet() {
		Builder<String> builder = ImmutableSet.builder();
		for (PiezasEnum pieza : PiezasEnum.values()) {
			builder.add(pieza.nomenclaruta);
		}
		return builder.build();
	}

	public boolean addPieza(Posicion pos, Pieza pieza) {
		if (this.mapaPiezas.get(pos) == null) {
			if( this.validarPosicion(pos, pieza)) {
				this.mapaPiezas.put(pos, pieza);
				return true;
			}
		} 
		return false;
	}

	private boolean validarPosicion(Posicion pos, Pieza pieza) {
		
		
		return false;
	}

	public Pieza getPieza(Posicion pos) {
		return this.mapaPiezas.get(pos);
	}

	public Pieza parse(String str) {
		Pieza result = null;
		if (str.length() > 0 && str.length() <= 3) {
			String primerLetra = str.substring(0, 1);
			String segundaLetra = str.substring(1, 2);
			if (str.length() == 3) { // Caso general
				if (this.nomenclaturaPiezas.contains(primerLetra)) {
					char letra = segundaLetra.charAt(0);
					if (letra >= 'a' && letra <= 'h') {
						char num = str.charAt(2);
						if (num >= '1' && num <= '8') {
							Posicion pos = new Posicion(String.valueOf(num), segundaLetra);
							if (mapaPiezas.get(pos) == null) {
								PiezasEnum piezaEnum = PiezasEnum.getByNomenclatura(primerLetra);
								if(piezaEnum != null) {
									switch (piezaEnum) {
									case ALFIL:
										result = new Alfil(pos);
									case CABALLO:
										result = new Caballo(pos);
									case DAMA:
										result = new Dama(pos);
									case PEON: // esto no deberia poder alcanzarse
										result = new Peon(pos);
									case REY:
										result = new Rey(pos);
									case TORRE:
										result = new Torre(pos);
									default:
										break;
									}
								}
							}
						}
					}
				}
			} else if (str.length() == 2) { // posible peon
				char letra = primerLetra.charAt(0);
				char num = segundaLetra.charAt(0);
				if(letra >= 'a' && letra <= 'h' && num >= '1' && num <= '8'){
					result = new Peon(new Posicion(primerLetra, segundaLetra));
				}
			}
		}
		return result;
	}
}
