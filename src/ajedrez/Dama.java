package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.List;

import com.google.common.collect.Lists;

import utils.PiezasEnum;

public class Dama extends Pieza {

	private static final int[] filas = new int[256];

	private static final int[] colum = new int[256];

	int ultimaPosicion = 0;
	
	VerticalesGenerator verticalesGenerator = VerticalesGenerator.INSTANCE;
	HorizontalesGenerator horizontalesGenerator = HorizontalesGenerator.INSTANCE;
	DiagonalesGenerator diagonalesGenerator = DiagonalesGenerator.INSTANCE;
	
	public Dama(Posicion posicion) {
		super(PiezasEnum.DAMA.nomenclatura, posicion);
		verticalesGenerator.generarVerticales(ultimaPosicion, filas, colum);
		horizontalesGenerator.generarHorizontales(ultimaPosicion, filas, colum);
		diagonalesGenerator.generarDiagonales(ultimaPosicion, filas, colum);
	}

	@Override
	public List<Posicion> getProximosMovimientos() {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(8);
		for (int i = 0; i < filas.length; ++i)
			agregarSiNoEsNulo(posiciones, sumar(posicionActual, filas[i], colum[i]));
		return posiciones;
	}

}
