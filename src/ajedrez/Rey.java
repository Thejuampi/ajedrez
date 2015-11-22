package ajedrez;

import utils.PiezasEnum;

public class Rey extends Pieza {

	public Rey(Posicion posicion) {
		super(PiezasEnum.REY.nomenclatura, posicion);
	}

	@Override
	protected Explorer[] getExplorers() {
		return new Explorer[] { new ReyExplorer(this) };
	}

}
