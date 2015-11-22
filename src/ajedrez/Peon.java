package ajedrez;

import utils.PiezasEnum;

public class Peon extends Pieza {

	public boolean posicionInicial = true;

	public Peon(Posicion posicion) {
		super(PiezasEnum.PEON.nomenclatura, posicion);
		if (isBlanca()) {
			if (posicion.getFila().equals("2")) {
				posicionInicial = true;
			}
		} else { // si es negra
			if (posicion.getFila().equals("6")) {
				posicionInicial = true;
			}
		}
	}

	@Override
	public void setPosicionActual(Posicion posicionActual) {
		super.setPosicionActual(posicionActual);
		if (this.posicionActual != null && !this.posicionActual.equals(posicionActual)) {
			this.posicionInicial = false;
		}
	}

	@Override
	protected Explorer[] getExplorers() {
		return new Explorer[] { new PeonExplorer(this) };
	}

}
