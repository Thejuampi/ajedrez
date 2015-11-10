package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.List;

import com.google.common.collect.Lists;

import utils.PiezasEnum;

public class Peon extends Pieza {

	private int blancas_filas[] = new int[] { 1, 1, 1, 2 };

	private int blancas_colum[] = new int[] { 0, 1, -1, 0 };

	private int negras_filas[] = new int[] { -1, -1, -1, -2 };

	private int negras_colum[] = new int[] { 0, 1, -1, 0 };
	
	private boolean posicionInicial = true;
	
	public Peon(Posicion posicion) {
		super(PiezasEnum.PEON.nomenclatura, posicion);
		if (isBlanca()) {
			if (posicion.getFila().equals("2")) {
				posicionInicial = true;
			}
		} else { // si es negra
			if (posicion.getFila().equals("6")) {
				posicionInicial = true;
			}
		}

	}

	@Override
	public List<Posicion> getProximosMovimientos() {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(3);
		if (posicionInicial) { // el peon puede adelantar 2 posiciones si esta en posicion inicial
			if (isBlanca()) {
				for(int i = 0; i < 4; ++i) agregarSiNoEsNulo(posiciones, sumar(posicionActual, blancas_filas[i], blancas_colum[i]));
			} else { // si es negra
				for(int i = 0; i < 4; ++i) agregarSiNoEsNulo(posiciones, sumar(posicionActual, negras_filas[i], negras_colum[i]));
			}
		} else { // no es posicion inicial
			if(isBlanca()) {
				for(int i = 0; i < 3; ++i) agregarSiNoEsNulo(posiciones, sumar(posicionActual, blancas_filas[i], blancas_colum[i]));
			} else { // negra
				for(int i = 0; i < 3; ++i) agregarSiNoEsNulo(posiciones, sumar(posicionActual, negras_filas[i], negras_colum[i]));
			}
		}
		return posiciones;
	}

}
