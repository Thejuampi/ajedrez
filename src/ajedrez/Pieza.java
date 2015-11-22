package ajedrez;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public abstract class Pieza {

	protected String nombre;
	
	protected Posicion posicionActual;
	
	protected boolean habilitada = true;

	protected boolean blanca = true;

	protected List<Explorer> explorers = Lists.newArrayListWithCapacity(8);
	
	protected abstract Explorer[] getExplorers();
	
	public Pieza(String nombre, Posicion posicion) {
		checkNotNull(posicion, "La posicion no puede ser nula!");
		checkNotNull(nombre, "El nombre no puede ser nulo!");
		this.nombre = nombre;
		this.posicionActual = posicion;
		Collections.addAll(explorers, getExplorers());
	}

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

	public List<Posicion> getProximosMovimientos(Collection<Pieza> piezas) {
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(64);
		for (Explorer explorer : explorers) {
			posiciones.addAll(explorer.explorar(piezas));
		}
		return posiciones;
	}
	
}
