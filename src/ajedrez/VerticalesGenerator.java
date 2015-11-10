package ajedrez;

public class VerticalesGenerator implements MovimientoVertical {

	public static final VerticalesGenerator INSTANCE = new VerticalesGenerator();

	private VerticalesGenerator(){}
	
	public void generarVerticales(int ultimaPosicion, int[] filas, int[] colum) {
		for (int i = 0; i < 8; ++i) {
			filas[ultimaPosicion + 2*i]    =  (i+1);
			filas[ultimaPosicion + 2*i +1] = -(i+1);
			colum[ultimaPosicion + 2*i]    = 0;
			colum[ultimaPosicion + 2*i +1] = 0;
		}
		ultimaPosicion += 16;
	}

}
