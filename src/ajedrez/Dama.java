package ajedrez;

import utils.PiezasEnum;

public class Dama extends Pieza {

	public Dama(Posicion posicion) {
		super(PiezasEnum.DAMA.nomenclatura, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion posicionSiguiente) {
		// TODO Auto-generated method stub
		return false;
	}

}
