package ajedrez;

public abstract class Pieza {

	private String nombre;
	
	private Posicion posicionActual;

	private boolean blanca;
	
	public Pieza(String nombre, Posicion posicion) {
		this.nombre = nombre;
		this.posicionActual = posicion;
	}

	public boolean cambiarPosicion(Posicion posicionSiguiente){
		
		if(esMovimientoValido(posicionSiguiente)){
			this.posicionActual = posicionSiguiente;
			return true;
		} else {
			return false;
		}
		
	}

	public abstract boolean esMovimientoValido(Posicion posicionSiguiente);

	public String getNombre() {
		return nombre;
	}

	public Posicion getPosicionActual() {
		return posicionActual;
	}
	
	@Override
	public String toString() {
		return "Pieza [nombre=" + nombre + ", posicionActual=" + posicionActual + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((posicionActual == null) ? 0 : posicionActual.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (posicionActual == null) {
			if (other.posicionActual != null)
				return false;
		} else if (!posicionActual.equals(other.posicionActual))
			return false;
		return true;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}

	public void setBlanca(boolean isBlanca) {
		this.blanca = isBlanca;
	}
	
	public boolean isBlanca() {
		return blanca;
	}
	
}
