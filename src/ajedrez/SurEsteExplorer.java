package ajedrez;

public class SurEsteExplorer extends Explorer {

	public SurEsteExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return i+1;
	}

	@Override
	protected int getFila(int i) {
		return -(i+1);
	}

}
