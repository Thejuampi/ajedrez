package ajedrez;

import static utils.PosicionMathUtils.sumar;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CaballoExplorer extends Explorer {

	private static final int[] filas = new int[] { 2, 2, 1, 1, -2, -2, -1, -1 };

	private static final int[] colum = new int[] { -1, 1, -2, 2, -1, 1, -2, 2 };

	@Override
	public List<Posicion> explorar(Collection<Pieza> piezas) {
		List<Posicion> posiciones = Lists.newArrayList();
		Map<Posicion, Pieza> mapa = Maps.uniqueIndex(piezas, new Function<Pieza, Posicion>() {
			public Posicion apply(Pieza input) {
				return input.posicionActual;
			}
		});
		for (int i = 0; i < filas.length; ++i) {
			Posicion pos = sumar(pieza.posicionActual, getFila(i), getColumna(i));
			if(pos != null ) {
				Pieza piezaAtPos = mapa.get(pos);
				if(piezaAtPos == null || piezaAtPos.blanca == this.pieza.blanca) { // si esta desocupado
					posiciones.add(pos);
				} 
			}
		}
		return posiciones;
	}

	public CaballoExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return colum[i];
	}

	@Override
	protected int getFila(int i) {
		return filas[i];
	}

}
