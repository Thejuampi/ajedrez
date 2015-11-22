package ajedrez;

import utils.PiezasEnum;

public class Dama extends Pieza {

	public Dama(Posicion posicion) {
		super(PiezasEnum.DAMA.nomenclatura, posicion);
	}

	@Override
	protected Explorer[] getExplorers() {
		return new Explorer[] { new NorteExplorer(this), new SurExplorer(this), new EsteExplorer(this),
				new OesteExplorer(this), new SurEsteExplorer(this), new SurOesteExplorer(this),
				new NorOesteExplorer(this), new NorEsteExplorer(this) };
	}

}
