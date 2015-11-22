package ajedrez;

import utils.PiezasEnum;

public class Alfil extends Pieza {

	public Alfil(Posicion posicion) {
		super(PiezasEnum.ALFIL.nomenclatura, posicion);
	}

	@Override
	public Explorer[] getExplorers() {
		return new Explorer[] { new NorOesteExplorer(this), new SurOesteExplorer(this), new NorEsteExplorer(this),
				new SurEsteExplorer(this) };
	}

}
