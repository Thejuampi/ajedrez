package ajedrez;

import utils.PiezasEnum;

public class Torre extends Pieza {

	public Torre(Posicion posicion) {
		super(PiezasEnum.TORRE.nomenclatura, posicion);
	}

	@Override
	protected Explorer[] getExplorers() {
		return new Explorer[] { new NorteExplorer(this), new SurExplorer(this), new OesteExplorer(this),
				new EsteExplorer(this) };
	}

}
