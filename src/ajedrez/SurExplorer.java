package ajedrez;

public class SurExplorer extends Explorer {

	public SurExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return 0;
	}

	@Override
	protected int getFila(int i) {
		return -(i+1);
	}

}
