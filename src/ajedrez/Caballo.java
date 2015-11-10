package ajedrez;

import utils.PiezasEnum;

public class Caballo extends Pieza {

	public Caballo(Posicion posicion) {
		super(PiezasEnum.CABALLO.nomenclaruta, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion posicionSiguiente) {
		// TODO Auto-generated method stub
		return false;
	}

}
