package ajedrez;

import utils.PiezasEnum;

public class Rey extends Pieza {

	public Rey(Posicion posicion) {
		super(PiezasEnum.REY.nomenclaruta, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion posicionSiguiente) {
		// TODO Auto-generated method stub
		return false;
	}

}
