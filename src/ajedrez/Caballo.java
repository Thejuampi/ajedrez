package ajedrez;

import utils.PiezasEnum;

public class Caballo extends Pieza {

	public Caballo(Posicion posicion) {
		super(PiezasEnum.CABALLO.nomenclatura, posicion);
	}

	@Override
	protected Explorer[] getExplorers() {
		return new Explorer[] { new CaballoExplorer(this) };
	}

}
