package ajedrez;

import utils.PiezasEnum;

public class Peon extends Pieza {

	public Peon(Posicion posicion) {
		super(PiezasEnum.PEON.nomenclaruta, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion posicionSiguiente) {
		// TODO Auto-generated method stub
		return false;
	}

}
