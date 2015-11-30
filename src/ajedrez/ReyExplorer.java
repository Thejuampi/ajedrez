package ajedrez;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import utils.PosicionMathUtils;

public class ReyExplorer extends Explorer {

	private int[] filas = new int[] {
			1,1,0,-1,-1,-1,0,1
	};

	private int[] columnas = new int[] {
			0,1,1,1,0,-1,-1,-1
	};
	
	@Override
	public List<Posicion> explorar(Collection<Pieza> piezas) {
		List<Posicion> posiciones = Lists.newArrayListWithCapacity(8);
		
		Map<Posicion, Pieza> mapa = Maps.uniqueIndex(piezas, (Function<Pieza, Posicion>) t -> t.posicionActual);
		Collection<Pieza> enemigas = Collections2.filter(piezas, input -> input.blanca != pieza.blanca);
		Set<Posicion> posicionesEnemigas = Sets.newHashSet();
		
		for(Pieza enemiga : enemigas) {
			if(enemiga instanceof Rey) {
				posicionesEnemigas.addAll(enemiga.getProximosMovimientos(Collections2.filter(piezas, input -> !input.equals(enemiga))));
			} else {
				posicionesEnemigas.addAll(enemiga.getProximosMovimientos(piezas));
			}
		}
		
		for(int posIndex = 0; posIndex < filas.length; ++posIndex) {
			Posicion posiblePosicion = PosicionMathUtils.sumar(pieza.posicionActual, getFila(posIndex), getColumna(posIndex));
			if(posiblePosicion != null) {
				Pieza posiblePieza = mapa.get(posiblePosicion);
				if(posiblePieza != null) { // Si el lugar esta ocupado
					if( posiblePieza.blanca != pieza.blanca && !posicionesEnemigas.contains(posiblePosicion)) { // y son de palos diferentes
						posiciones.add(posiblePosicion);
					} 
				} else { // si el lugar no esta ocupado y no este asechado por el enemigo
					if(!posicionesEnemigas.contains(posiblePosicion)) {
						posiciones.add(posiblePosicion);
					}
				}
			}
		}
		
		return posiciones;
	}

	public ReyExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return columnas[i];
	}

	@Override
	protected int getFila(int i) {
		return filas[i];
	}

}
