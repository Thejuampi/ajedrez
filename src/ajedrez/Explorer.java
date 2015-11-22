package ajedrez;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

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
		while (i < getLoopLimit() || terminar) {
			Posicion p = PosicionMathUtils.sumar(pieza.posicionActual, getFila(i),getColumna(i));
			if(p != null) {
				for(Pieza pieza : piezas) {
					if(p.equals(pieza.getPosicionActual())) {
						terminar = true;
						break;
					}
				}
				if(!terminar) {
					posiciones.add(p);
				}
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
