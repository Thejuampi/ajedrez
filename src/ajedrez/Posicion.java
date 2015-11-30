package ajedrez;

public class Posicion {

	private String fila;

	private String columna;

	public Posicion(String fila, String columna) {
		super();
		this.fila = fila;
		this.columna = columna;
	}
	
	public Posicion(Posicion pos){
		this.fila = pos.fila;
		this.columna = pos.columna;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (columna == null) {
			if (other.columna != null)
				return false;
		} else if (!columna.equals(other.columna))
			return false;
		if (fila == null) {
			if (other.fila != null)
				return false;
		} else if (!fila.equals(other.fila))
			return false;
		return true;
	}

	public String getColumna() {
		return columna;
	}

	public String getFila() {
		return fila;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columna.hashCode() + fila.hashCode();
		return result;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	@Override
	public String toString() {
		return "["+columna+fila+"]";
	}

}
