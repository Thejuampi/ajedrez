package ajedrez;

import static java.lang.System.out;
import static utils.TjUtils.to80Chars;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Main {

	
	public static void main(String[] args) {

		PiezasManager mng = PiezasManager.INSTANCE;

		out.println(to80Chars(""));
		out.println(to80Chars(" Proyecto Final Electiva \"Ajedrez\" "));
		out.println(to80Chars(""));
		
		out.println();
		
		out.println("Ingrese la posición de las piezas BLANCAS con notacion estandar:");
		out.println("Ejemplo: Rh6, d2, Aa2");
		
		String blancas = readLine();
		out.println("Ahora ingrese la posición de las piezas NEGRAS:");
		String negras = readLine();
		
		
		
		if( mng.init(blancas, negras) ) { 
				
			out.println(to80Chars("A continuación, el sistema buscara todas las posibles jugadas"));
			out.println(to80Chars("que en 2 movimientos, sin captura inicial, sin jaque inicial "));
			out.println(to80Chars("y sin blablabla den jaque mate                               "));
			
			Collection<Posicion> solucion = mng.buscarSolucion();
			StringBuilder str = new StringBuilder();
			if(!solucion.isEmpty()) {
				for(Posicion p : solucion) {
					str.append(p.toString());
				}
				out.println("La Solución encontrada es: " + str.toString());
			} else {
				out.println("No ha sido posible encontrar una solución");
			}
			
		} else {
			out.print(to80Chars(""));
			out.println(to80Chars("Datos Ingresados no validos:"));
			out.print(to80Chars(""));
		}
	}
	
	private static String readLine() {
        String line = null;
        Console c = System.console();
        if (c != null) {
             line = c.readLine();
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                 line = bufferedReader.readLine();
            } catch (IOException e) { 
                //Ignore    
            }
        }
        return line;
    }

}
