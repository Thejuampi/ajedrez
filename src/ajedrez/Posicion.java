package ajedrez;

public class Posicion {
	

	private String fila;
	
	private String columna;

	public Posicion(String fila, String columna) {
		super();
		this.fila = fila;
		this.columna = columna;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getColumna() {
		return columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

}
