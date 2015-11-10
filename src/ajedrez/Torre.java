package ajedrez;

import utils.PiezasEnum;

public class Torre extends Pieza {

	public Torre(Posicion posicion) {
		super(PiezasEnum.TORRE.nomenclatura, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion posicionSiguiente) {
		// TODO Auto-generated method stub
		return false;
	}

}
