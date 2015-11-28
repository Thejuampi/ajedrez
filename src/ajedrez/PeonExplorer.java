package ajedrez;

import static utils.PosicionMathUtils.sumar;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PeonExplorer extends Explorer {

	private int blancas_filas[] = new int[] { 1, 1, 1, 2 };

	private int blancas_colum[] = new int[] { 0, 1, -1, 0 };

	private int negras_filas[] = new int[] { -1, -1, -1, -2 };

	private int negras_colum[] = new int[] { 0, 1, -1, 0 };

	
	public PeonExplorer(Pieza pieza) {
		super(pieza);
	}

	@Override
	public List<Posicion> explorar(Collection<Pieza> piezas) {
		Peon peon = (Peon) pieza;
		// FIXME! Considerar la posibilidad de que las piezas puedan ser del mismo color.
		// En dicho caso, no se debe poder agregar el movimiento a la lista de movimientos (posiciones)
		
		Map<Posicion, Pieza> mapa = Maps.uniqueIndex(piezas, new Function<Pieza, Posicion>() {
			public Posicion apply(Pieza input) {
				return input.posicionActual;
			}
		});
		
		List<Posicion> posiciones = Lists.newArrayListWithExpectedSize(4);
		
		boolean isBlanca = peon.isBlanca();
		Posicion posicionActual = peon.getPosicionActual();
		Posicion N =  sumar(posicionActual, isBlanca? 1 : -1, 0);
		Posicion N2 = sumar(posicionActual, isBlanca ? 2: -2, 0);
		Posicion NE = sumar(posicionActual, isBlanca ? 1:-1, 1);
		Posicion NO = sumar(posicionActual, isBlanca ? 1:-1, -1);

		if(N != null) {
			Pieza piezaAtN = mapa.get(N);
			if( piezaAtN == null ) { // si el lugar esta desocupado
				posiciones.add( N);
			} else if(piezaAtN.isBlanca() != peon.isBlanca()) { // si el lugar esta ocupado por una pieza contraria
				posiciones.add( N);
			}	
		}
		
		if(NE != null) {
			Pieza piezaAtNe = mapa.get(NE);
			if(piezaAtNe != null && piezaAtNe.blanca != peon.blanca) {
				posiciones.add( NE);
			}
		}
		
		if(NO != null){
			Pieza piezaAtNO = mapa.get(NO);
			if(piezaAtNO != null && piezaAtNO.blanca != peon.blanca) {
				posiciones.add( NO);
			}	
		}
		
		if(peon.posicionInicial && N2 != null) {
			Pieza piezaAtN2 = mapa.get(N2);
			if(piezaAtN2 == null) {
				posiciones.add( N2);
			} else if(piezaAtN2.blanca != peon.blanca) {
				posiciones.add( N2);
			}
			
		}
		return posiciones;
	}

	@SuppressWarnings("unused")
	private void metodoViejo(Collection<Pieza> piezas, Peon peon, List<Posicion> posiciones) {
		if (peon.posicionInicial) { // el peon puede adelantar 2 posiciones si esta en posicion inicial
			if (peon.isBlanca()) {
				for(int i = 0; i < 4; ++i) {
					agregarPosicion(piezas, peon, posiciones, i, blancas_filas, blancas_colum);
				}
			} else { // si es negra
				for(int i = 0; i < 4; ++i) {
					agregarPosicion(piezas, peon, posiciones, i, negras_filas, negras_colum);
				}
			}
		} else { // no es posicion inicial
			if(peon.isBlanca()) {
				for(int i = 0; i < 3; ++i) {
					agregarPosicion(piezas, peon, posiciones, i, blancas_filas, blancas_colum);
				}
			} else { // negra
				for(int i = 0; i < 3; ++i) {
					agregarPosicion(piezas, peon, posiciones, i, negras_filas, negras_colum);
				}
			}
		}
	}

	private void agregarPosicion(Collection<Pieza> piezas, Peon peon, List<Posicion> posiciones, int i, int[] filas, int[] colum) {
		Posicion pos = sumar(peon.posicionActual, filas[i], colum[i]);
		if(verificarPosicion(piezas, pos)) {
			posiciones.add(pos);
		}
	}
	
	protected boolean verificarPosicion(Collection<Pieza> piezas, Posicion pos) {
		if(pos == null) {
			return false;
		}
		for(Pieza pieza : piezas) {
			if( !pieza.equals(this.pieza) && pos.equals(pieza.getPosicionActual())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected int getColumna(int i) {
		return 0;
	}

	@Override
	protected int getFila(int i) {
		return 0;
	}
	
}
