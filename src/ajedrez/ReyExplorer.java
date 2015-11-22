package ajedrez;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

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
		for(int posIndex = 0 ; posIndex < filas.length; ++posIndex) {
			PosicionMathUtils.agregarSiNoEsNulo(posiciones, PosicionMathUtils.sumar(pieza.posicionActual, getFila(posIndex), getColumna(posIndex)));
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
