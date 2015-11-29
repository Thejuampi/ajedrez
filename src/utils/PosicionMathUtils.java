package utils;

import java.util.List;

import com.google.common.base.Preconditions;

import ajedrez.Posicion;

public class PosicionMathUtils {

	public static Posicion sumar(Posicion pos, int nFila, int nColumna) {
		Preconditions.checkNotNull(pos);
		
		Posicion p = new Posicion(pos);
		char cFila = p.getFila().charAt(0);
		char cColu = p.getColumna().charAt(0);
		
		char posibleFilaSiguiente = (char) (cFila+nFila);
		char posibleColuSiguiente = (char) (cColu+nColumna);
		
		if(posibleFilaSiguiente > '8' || posibleColuSiguiente > 'h' || posibleFilaSiguiente < '1' || posibleColuSiguiente < 'a'){
			//throw new RuntimeException("La posicion pretendida no es posible: [fila=" + posibleFilaSiguiente +"] columna = [" + posibleColuSiguiente + "]");
			p = null;
		} else {
			p.setFila(String.valueOf(posibleFilaSiguiente));
			p.setColumna(String.valueOf(posibleColuSiguiente));
		}
		return p;
	}
	
	public static void agregarSiNoEsNulo(List<Posicion> posiciones, Posicion p) {
		if(p != null) { posiciones.add(p); }
	}
		
}
