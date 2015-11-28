package ajedrez;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
			builder.add(pieza.nomenclatura);
		}
		return builder.build();
	}

	public boolean parsearPiezas(String linea, boolean isBlancas) {
		String[] piezas = linea.split(",");
		for(String piezaStr : piezas){
			piezaStr = piezaStr.trim();
			Pieza pieza = parse(piezaStr, isBlancas);
			if(pieza == null) {
				return false;
			} else {
				if(!addPieza(pieza.getPosicionActual(), pieza)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean validarCondicionInicialDeTodasLasPiezas() {
		Collection<Pieza> blancas = Collections2.filter(mapaPiezas.values(), new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input.isBlanca();
			}
		});
		
		Collection<Pieza> negras = Collections2.filter(mapaPiezas.values(), new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return !input.isBlanca();
			}
		});
		
		Predicate<Pieza> predicadoReyes = new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input instanceof Rey;
			}
		};
		Collection<Pieza> reyesBlanco = Collections2.filter(blancas, predicadoReyes);
		if(reyesBlanco.size() != 1){
			return false;
		}
		
		Collection<Pieza> reyesNegro = Collections2.filter(negras, predicadoReyes);
		if(reyesNegro.size() != 1){
			return false;
		}
		
		Predicate<Pieza> predicadoDamas = new Predicate<Pieza>(){
			public boolean apply(Pieza input) {
				return input instanceof Dama;
			}
		};
		
		Collection<Pieza> damasBlancas = Collections2.filter(blancas, predicadoDamas);
		if(damasBlancas.size() > 1) {
			return false;
		}
		
		Collection<Pieza> damasNegras = Collections2.filter(negras, predicadoDamas);
		if(damasNegras.size() > 1) {
			return false;
		}
		
		Predicate<Pieza> predicadoCaballos = new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input instanceof Caballo;
			}
		};
		Collection<Pieza> caballosBlancos = Collections2.filter(blancas, predicadoCaballos);
		if(caballosBlancos.size() > 2) {
			return false;
		}
		
		Collection<Pieza> CaballosNegros = Collections2.filter(negras, predicadoCaballos);
		if(CaballosNegros.size() > 2) {
			return false;
		}
		
		Predicate<Pieza> predicadoAlfil = new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input instanceof Alfil;
			}
		};
		
		Collection<Pieza> alfilesBlancos = Collections2.filter(blancas, predicadoAlfil);
		if(alfilesBlancos.size() > 2) {
			return false;
		}
		
		Collection<Pieza> alfilesnegros = Collections2.filter(negras, predicadoAlfil);
		if(alfilesnegros.size() > 2) {
			return false;
		}
		
		Predicate<Pieza> predicadoTorres = new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input instanceof Torre;
			}
		};
		
		Collection<Pieza> torresBlancos = Collections2.filter(blancas, predicadoTorres);
		if(torresBlancos.size() > 2) {
			return false;
		}
		
		Collection<Pieza> torressnegros = Collections2.filter(negras, predicadoTorres);
		if(torressnegros.size() > 2) {
			return false;
		}
		
		
		Predicate<Pieza> predicadoPeones = new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input instanceof Torre;
			}
		};
		
		Collection<Pieza> peonesBlancos = Collections2.filter(blancas, predicadoPeones);
		if(peonesBlancos.size() > 8) {
			return false;
		}
		
		Collection<Pieza> peonesNegros = Collections2.filter(negras, predicadoPeones);
		if(peonesNegros.size() > 8) {
			return false;
		}
		
		Rey reyBlanco = (Rey) reyesBlanco.iterator().next();
		
		for(Pieza negra : negras) {
			List<Posicion> movimientos = negra.getProximosMovimientos(blancas);
			if(movimientos.contains(reyBlanco.getPosicionActual())) {
				return false;
			}
		}
		
		Rey reyNegro = (Rey) reyesNegro.iterator().next();
		
		for(Pieza blanca : blancas) {
			List<Posicion> movimientos = blanca.getProximosMovimientos(negras);
			if(movimientos.contains(reyNegro.getPosicionActual())) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean addPieza(Posicion pos, Pieza pieza) {
		if( this.validarPosicion(pos, pieza)) {
			this.mapaPiezas.put(pos, pieza);
			return true;
		} else {
			return false;
		}
	}

	private boolean validarPosicion(Posicion pos, Pieza pieza) {
		if (this.mapaPiezas.get(pos) == null) {// si el lugar esta desocupado...
			return true;
		} else {
			return false;
		}
	}

	public Pieza getPieza(Posicion pos) {
		return this.mapaPiezas.get(pos);
	}

	public Pieza parse(String str, boolean isBlanca) {
		Pieza result = parse(str);
		if(result != null) {
			result.setBlanca(isBlanca);
		}
		return result;
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
							PiezasEnum piezaEnum = PiezasEnum.getByNomenclatura(primerLetra);
							if(piezaEnum != null) {
								switch (piezaEnum) {
								case ALFIL:
									result = new Alfil(pos);
									break;
								case CABALLO:
									result = new Caballo(pos);
									break;
								case DAMA:
									result = new Dama(pos);
									break;
								case PEON: // esto no deberia poder alcanzarse
									result = new Peon(pos);
									break;
								case REY:
									result = new Rey(pos);
									break;
								case TORRE:
									result = new Torre(pos);
									break;
								default:
									break;
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

	public boolean init(String blancas, String negras) {
		return this.parsearPiezas(blancas, true) && this.parsearPiezas(negras, false) && validarCondicionInicialDeTodasLasPiezas();
	}

	/**
	 * 
	 * @return Colección de posiciones que indican las jugadas necesarias para dar jaque mate.
	 */
	public Collection<Posicion> buscarSolucion() {
		
		Collection<Pieza> piezas = mapaPiezas.values();
		Collection<Pieza> blancas = Collections2.filter(piezas, new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input.isBlanca();
			}
		});
		
		Collection<Pieza> negras = Collections2.filter(piezas, new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return !input.isBlanca();
			}
		});
		
		Predicate<Pieza> predicadoRey = new Predicate<Pieza>() {
			public boolean apply(Pieza input) {
				return input instanceof Rey;
			}
		};
		
		Rey reyNegro = (Rey) Iterables.find(negras, predicadoRey);
		Rey reyBlanco = (Rey) Iterables.find(blancas, predicadoRey);
		
		GameState inicial = new GameState(blancas, negras);
		Set<Integer> gameStates = Sets.newHashSet(inicial.hashCode());
		
		for(Pieza blanca : blancas) {
			Posicion posicionOriginal = blanca.getPosicionActual();
			for(Posicion posicion : blanca.getProximosMovimientos(piezas)){
				blanca.setPosicionActual(posicion);
				GameState state = new GameState(blancas, negras);
				
				if(!gameStates.contains(state.hashCode())) { //computo el hash, porque consume menos memoria que guardar el estado completo
					
					if(! reyNegro.getPosicionActual().equals(posicion) && mapaPiezas.get(posicion) == null ) { // sin jaque ni captura inicial
						
						//FASE 2: Mueve el negro
						
						for(Pieza negra : negras) {
							Posicion posicionNegraOrigina = negra.getPosicionActual();
							for(Posicion posicionNegra : negra.getProximosMovimientos(piezas)) {
								negra.setPosicionActual(posicionNegra);
								GameState state2 = new GameState(blancas, negras);
								if(!gameStates.contains(state2.hashCode())) { // si el movimiento no fue jugado antes
									
									for(Pieza blanca2 : blancas) {
										Posicion posicionOriginalBlanca2 = blanca2.posicionActual;
										for(Posicion posicionBlanca2 : blanca2.getProximosMovimientos(piezas)){
											
											//TODO Falta ver como determinar que la posicion representa un jaque mate
											// Probablemente tenga que recorrer todas las piezas negras (de nuevo)
											// y ver si puedo "tapar" o mover el rey
											
											
										}
										
										blanca2.setPosicionActual(posicionOriginalBlanca2);
									}
									
									
								}
							}
							// cuando termine de probar los movimientos con la negra, la devuelvo a su posicion
							// original, y sigo con otra pieza
							negra.setPosicionActual(posicionNegraOrigina); 
						}
						
						
					}
				}
				//Devuelvo la blanca a su posicion original y sigo probando con otra pieza
				blanca.setPosicionActual(posicionOriginal);
			}
			
		}
		
		return null;
	}
	
	
	
}
