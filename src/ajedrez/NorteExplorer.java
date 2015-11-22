package ajedrez;

public class NorteExplorer extends Explorer {

	public NorteExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return 0;
	}

	@Override
	protected int getFila(int i) {
		return i+1;
	}

}
