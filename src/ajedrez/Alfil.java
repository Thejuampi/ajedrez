package ajedrez;

import utils.PiezasEnum;

public class Alfil extends Pieza{

	public Alfil(Posicion posicion) {
		super(PiezasEnum.ALFIL.nomenclaruta, posicion);
	}

	@Override
	public boolean esMovimientoValido(Posicion posicionSiguiente) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
