package ajedrez;

public class DiagonalesGenerator implements MovimientoDiagonal {

	public static final DiagonalesGenerator INSTANCE = new DiagonalesGenerator ();
	
	private DiagonalesGenerator () {}
	
	public void generarDiagonales(int ultimaPosicion, int[] filas, int[] colum) {
		for(int i = 0; i < 8; ++i) {
			filas[ultimaPosicion + 4*i] = (i+1);
			filas[ultimaPosicion + 4*i + 1] = (i+1);
			filas[ultimaPosicion + 4*i + 2] = -(i+1);
			filas[ultimaPosicion + 4*i + 3] = -(i+1);
			
			colum[ultimaPosicion + 4*i] = i+1;
			colum[ultimaPosicion + 4*i+1] = -(i+1);
			colum[ultimaPosicion + 4*i+2] = i+1;
			colum[ultimaPosicion + 4*i+3] = -(i+1);
		}
		ultimaPosicion += 32;
	}

}
