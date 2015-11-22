package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

public class CaballoExplorer extends Explorer {

	private static final int[] filas = new int[] { 2, 2, 1, 1, -2, -2, -1, -1 };

	private static final int[] colum = new int[] { -1, 1, -2, 2, -1, 1, -2, 2 };

	@Override
	public List<Posicion> explorar(Collection<Pieza> piezas) {
		List<Posicion> posiciones = Lists.newArrayList();
		for (int i = 0; i < filas.length; ++i)
			agregarSiNoEsNulo(posiciones, sumar(pieza.posicionActual, getFila(i), getColumna(i)));
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
