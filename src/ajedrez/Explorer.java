package ajedrez;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import utils.PosicionMathUtils;

public abstract class Explorer {
	
	protected Pieza pieza = null;

	public Explorer(Pieza pieza) {
		this.pieza = pieza;
	}

	public List<Posicion> explorar(Collection<Pieza> piezas) {
		List<Posicion> posiciones = Lists.newArrayListWithCapacity(8);
		int i = 0;
		boolean terminar = false;
//		boolean agregar = true;
		
		Map<Posicion, Pieza> mapa = Maps.uniqueIndex(piezas, new Function<Pieza, Posicion>() {
			public Posicion apply(Pieza input) {
				return input.posicionActual;
			}
		});
		
		while (i < getLoopLimit() && !terminar) {
			/**
			 * En teoria, esta posicion no deberia ser nunca igual a la posicion de la pieza actual
			 * Si no es asi, FIXME: Si algo falla, revisar esto primero
			 */
			Posicion nuevaPosicion = PosicionMathUtils.sumar(pieza.posicionActual, getFila(i),getColumna(i));
			if(nuevaPosicion != null) {
				Pieza piezaAtPos = mapa.get(nuevaPosicion);
				if(piezaAtPos == null) {
					posiciones.add(nuevaPosicion); // si esta vacio, agrego y continuo
				} else { // sino, verifico que la pieza sea del mismo palo para agregar y pongo bandera para terminar
					if( piezaAtPos.blanca != this.pieza.blanca  ) {
					posiciones.add(nuevaPosicion);
					}
					terminar = true;
				}
//				
//				
//				for(Pieza pieza : piezas)  {
//					if( !this.pieza.equals(pieza) && nuevaPosicion.equals(pieza.getPosicionActual())) {
//						if(this.pieza.isBlanca() != pieza.isBlanca()){
//							agregar = true;
//						} else {
//							agregar = false; 
//						}
//						terminar = true;
//						break;
//					}
//				}
//				if(agregar) {
//					posiciones.add(nuevaPosicion);
//				}
			}
			++i;
		}
		return posiciones;
	}

	private Integer getLoopLimit() {
		return 8;
	}

	protected abstract int getColumna(int i);

	protected abstract int getFila(int i);

}
