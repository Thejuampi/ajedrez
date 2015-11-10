package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.List;

import com.google.common.collect.Lists;

import utils.PiezasEnum;

public class Alfil extends Pieza {

	private int[] filas = new int[32];
	
	private int[] colum = new int[32];
	
	private int ultimaPosicion = 0;
	
	private DiagonalesGenerator diagonalesGenerator = DiagonalesGenerator.INSTANCE;
	
	public Alfil(Posicion posicion) {
		super(PiezasEnum.ALFIL.nomenclatura, posicion);
		diagonalesGenerator.generarDiagonales(ultimaPosicion, filas, colum);
	}

	@Override
	public List<Posicion> getProximosMovimientos() {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(32);
		for (int i = 0; i < filas.length; ++i)
			agregarSiNoEsNulo(posiciones, sumar(posicionActual, filas[i], colum[i]));
		return posiciones;
	}

}
