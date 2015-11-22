package ajedrez;

public class OesteExplorer extends Explorer {

	public OesteExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	protected int getColumna(int i) {
		return i+1;
	}

	@Override
	protected int getFila(int i) {
		return 0;
	}

}
