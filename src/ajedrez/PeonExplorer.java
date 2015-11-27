package ajedrez;

import static utils.PosicionMathUtils.agregarSiNoEsNulo;
import static utils.PosicionMathUtils.sumar;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

public class PeonExplorer extends Explorer {

	private int blancas_filas[] = new int[] { 1, 1, 1, 2 };

	private int blancas_colum[] = new int[] { 0, 1, -1, 0 };

	private int negras_filas[] = new int[] { -1, -1, -1, -2 };

	private int negras_colum[] = new int[] { 0, 1, -1, 0 };

	
	public PeonExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	public List<Posicion> explorar(Collection<Pieza> piezas) {
		Peon peon = (Peon) pieza;
		// FIXME! Considerar la posibilidad de que las piezas puedan ser del mismo color.
		// En dicho caso, no se debe poder agregar el movimiento a la lista de movimientos (posiciones)
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(4);
		if (peon.posicionInicial) { // el peon puede adelantar 2 posiciones si esta en posicion inicial
			if (peon.isBlanca()) {
				for(int i = 0; i < 4; ++i) agregarSiNoEsNulo(posiciones, sumar(peon.posicionActual, blancas_filas[i], blancas_colum[i]));
			} else { // si es negra
				for(int i = 0; i < 4; ++i) agregarSiNoEsNulo(posiciones, sumar(peon.posicionActual, negras_filas[i], negras_colum[i]));
			}
		} else { // no es posicion inicial
			if(peon.isBlanca()) {
				for(int i = 0; i < 3; ++i) agregarSiNoEsNulo(posiciones, sumar(peon.posicionActual, blancas_filas[i], blancas_colum[i]));
			} else { // negra
				for(int i = 0; i < 3; ++i) agregarSiNoEsNulo(posiciones, sumar(peon.posicionActual, negras_filas[i], negras_colum[i]));
			}
		}
		return posiciones;
	}
	
	@Override
	protected int getColumna(int i) {
		return 0;
	}

	@Override
	protected int getFila(int i) {
		return 0;
	}
	
}
