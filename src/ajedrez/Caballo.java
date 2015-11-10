package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.List;

import com.google.common.collect.Lists;

import utils.PiezasEnum;

public class Caballo extends Pieza {

	private static final int[] filas = new int[] { 2, 2, 1, 1, -2, -2, -1, -1 };

	private static final int[] colum = new int[] { -1, 1, -2, 2, -1, 1, -2, 2 };

	public Caballo(Posicion posicion) {
		super(PiezasEnum.CABALLO.nomenclatura, posicion);
	}

	@Override
	public List<Posicion> getProximosMovimientos() {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(8);
		for (int i = 0; i < filas.length; ++i)
			agregarSiNoEsNulo(posiciones, sumar(posicionActual, filas[i], colum[i]));
		return posiciones;
	}

}
