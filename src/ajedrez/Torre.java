package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.List;

import com.google.common.collect.Lists;

import utils.PiezasEnum;

public class Torre extends Pieza {
	
	private HorizontalesGenerator horizontalesGenerator = HorizontalesGenerator.INSTANCE;
	
	private VerticalesGenerator verticalesGenerator = VerticalesGenerator.INSTANCE;
	
	private static final int[] filas = new int[32];
	
	private static final int[] colum = new int[32];

	private int ultimaPosicion = 0;
	
	public Torre(Posicion posicion) {
		super(PiezasEnum.TORRE.nomenclatura, posicion);
		horizontalesGenerator.generarHorizontales(ultimaPosicion, filas, colum);
		verticalesGenerator.generarVerticales(ultimaPosicion, filas, colum);
	}

	@Override
	public List<Posicion> getProximosMovimientos() {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(8);
		for (int i = 0; i < filas.length; ++i)
			agregarSiNoEsNulo(posiciones, sumar(posicionActual, filas[i], colum[i]));
		return posiciones;
	}

}
