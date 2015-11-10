package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.List;

import com.google.common.collect.Lists;

import utils.PiezasEnum;

public class Rey extends Pieza {

	private int[] filas = new int[] { 1, 1, 0, -1, -1, -1, 0, 1 };

	private int[] colum = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	public Rey(Posicion posicion) {
		super(PiezasEnum.REY.nomenclatura, posicion);
	}

	@Override
	public List<Posicion> getProximosMovimientos() {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(6);
		for (int i = 0; i < filas.length; ++i)
			agregarSiNoEsNulo(posiciones, sumar(posicionActual, filas[i], colum[i]));
		return posiciones;
	}

}
