package ajedrez;

public abstract class Pieza {

	private String nombre;
	
	private Posicion posicionActual;
	
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
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}
	
}
