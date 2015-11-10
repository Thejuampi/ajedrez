package ajedrez;

public class HorizontalesGenerator implements MovimientoHorizontal {

	public static final HorizontalesGenerator INSTANCE = new HorizontalesGenerator();
	
	private HorizontalesGenerator(){}
	
	public void generarHorizontales(int ultimaPosicion, int[] filas, int[] colum) {
		for (int i = 0; i < 8; ++i) {
			filas[ultimaPosicion + 2*i]    =  0;
			filas[ultimaPosicion + 2*i +1] =  0;
			colum[ultimaPosicion + 2*i] = (i+1);
			colum[ultimaPosicion + 2*i +1] = -(i+1);
		}
		ultimaPosicion += 16;
	}

}
