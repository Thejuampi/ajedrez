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

}
