package ajedrez;

public class NorOesteExplorer extends Explorer {

	public NorOesteExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return -(i+1);
	}

	@Override
	protected int getFila(int i) {
		return i+1;
	}

}
