package ajedrez;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

public class GameState {

	private Collection<Pieza> blancas;

	private Collection<Pieza> negras;
	
	public GameState(Collection<Pieza> blancas, Collection<Pieza> negras){
		saveState(blancas, negras);
	}

	public void saveState(Collection<Pieza> blancas, Collection<Pieza> negras) {
		int nBlancas = blancas.size();
		int nNegras = negras.size();
		List<Pieza> nuevasBlancas = Lists.newArrayListWithExpectedSize(nBlancas);
		List<Pieza> nuevasNegras = Lists.newArrayListWithExpectedSize(nNegras);
		for (Pieza blanca : blancas) {
			nuevasBlancas.add(new Pieza(blanca));
		}
		for (Pieza negra : negras) {
			nuevasNegras.add(new Pieza(negra));
		}
		this.blancas = nuevasBlancas;
		this.negras = nuevasNegras;
	}

	public void getState(Collection<Pieza> blancas, Collection<Pieza> negras) {
		blancas = this.blancas;
		negras = this.negras;
	}
	
	public void moverPieza(Pieza pieza, Posicion posicion) {
		pieza.setPosicionActual(posicion);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blancas == null) ? 0 : blancas.hashCode());
		result = prime * result + ((negras == null) ? 0 : negras.hashCode());
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
		GameState other = (GameState) obj;
		if (blancas == null) {
			if (other.blancas != null)
				return false;
		} else if (!blancas.equals(other.blancas))
			return false;
		if (negras == null) {
			if (other.negras != null)
				return false;
		} else if (!negras.equals(other.negras))
			return false;
		return true;
	}

}
