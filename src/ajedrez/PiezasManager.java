package ajedrez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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
	public Collection<Pieza> buscarSolucion() {
		
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
		
		List<GameState> gameStates = Lists.newArrayList();
		for(Pieza blanca : blancas) {
			Posicion posicionOriginal = blanca.getPosicionActual();
			for(Posicion posicion : blanca.getProximosMovimientos(piezas)){
				blanca.setPosicionActual(posicion);
				Pieza copiaBlanca = new Pieza(blanca);
				GameState state = new GameState(blanca, blancas, negras);
				if(!gameStates.contains(state)) { //computo el hash, porque consume menos memoria que guardar el estado completo
					gameStates.add(state);
					Collection<Posicion> lugaresDeAtaque = blanca.getProximosMovimientos(piezas);
					if(! lugaresDeAtaque.contains(reyNegro.posicionActual) //sin jaque
							&& mapaPiezas.get(posicion) == null ) { // Ni captura inicial. 
						//FASE 2: Mueve el negro
						for(Pieza negra : negras) {
							Posicion posicionNegraOrigina = negra.getPosicionActual();
							for(Posicion posicionNegra : negra.getProximosMovimientos(piezas)) {
								negra.setPosicionActual(posicionNegra);
								GameState state2 = new GameState(negra, blancas, negras);
								if(!gameStates.contains(state2)) { // si el movimiento no fue jugado antes
									gameStates.add(state2);
									Map<Posicion, Pieza> mapa_blancas = Maps.uniqueIndex(blancas, (Function<Pieza, Posicion>) input -> input.posicionActual);
									if(mapa_blancas.get(posicionNegra) != null) {
										continue; // se descarta la captura por parte del negro...
									}
									//TODO: Capturar pieza blanca...
									for(Pieza blanca2 : blancas) {
										Posicion posicionOriginalBlanca2 = blanca2.posicionActual;
										for(Posicion posicionBlanca2 : blanca2.getProximosMovimientos(piezas)){
											blanca2.setPosicionActual(posicionBlanca2);
											GameState state3 = new GameState(blanca2, blancas, negras);
											if(!gameStates.contains(state3)) {
												gameStates.add(state3);
												//Tiene un problema: usa el rey para dar jaque mate... ja
												if(isJaqueMate(blanca2, posicionBlanca2, blancas, negras)) {
													return Arrays.asList(copiaBlanca, negra, blanca2);
												}	
											} 
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
			}
			//Devuelvo la blanca a su posicion original y sigo probando con otra pieza
			blanca.setPosicionActual(posicionOriginal);
		}
		return Arrays.asList();
	}

	public boolean isJaqueMate(Pieza pieza, Posicion posicion, Collection<Pieza> piezasAliadas, Collection<Pieza> piezasEnemigas) {
		Rey reyEnemigo = (Rey) Iterables.find(piezasEnemigas, input -> input instanceof Rey);
		List<Posicion> posicionesRey = reyEnemigo.getProximosMovimientos( mapaPiezas.values() );
		Set<Posicion> posicionesAliadas = Sets.newHashSet();
		Map<Posicion, Pieza> mapaAliadas = Maps.uniqueIndex(piezasAliadas, (Function<Pieza, Posicion>) input -> input.posicionActual);
		
		for(Pieza aliada : piezasAliadas) {
			posicionesAliadas.addAll(aliada.getProximosMovimientos(mapaPiezas.values()));
		}

		for(Posicion pos : new ArrayList<Posicion>(posicionesRey)) {
			Pieza ppp = mapaAliadas.get(pos);
			if( ppp != null ) {
				Collection<Pieza> filtradas = Collections2.filter(mapaPiezas.values(), input -> !input.equals(ppp));
				Collection<Pieza> aliadasFiltradas = Collections2.filter(filtradas, input -> input.blanca == pieza.blanca);
				
				Set<Posicion> posAliadas = Sets.newHashSet();
				for(Pieza piezaFiltrada : aliadasFiltradas) {
					posAliadas.addAll(piezaFiltrada.getProximosMovimientos(filtradas));
				}
				// Esto significa que si el rey toma esta pieza, pero la misma es defendida por otra, esta en jaque.
				if(posAliadas.contains(pos)) {
					posicionesRey.remove(pos);
				}
			}
		}
		
		if(	posicionesRey.isEmpty() // si no tiene mas movimientos
			&& posicionesAliadas.contains(reyEnemigo.posicionActual) ){ // y las piezas aliadas atacan al rey enemigo {
			return true;
		}
		
		return false;
	}
	
}
