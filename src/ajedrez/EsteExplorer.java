package ajedrez;

public class EsteExplorer extends Explorer {

	public EsteExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return -(i+1);
	}

	@Override
	protected int getFila(int i) {
		return 0;
	}

}
