package ajedrez;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.TjUtils;

public class Main {

	public static void main(String[] args) {

		out.println(TjUtils.to80Chars(""));
		out.println(TjUtils.to80Chars(" Proyecto Final Electiva \"Ajedrez\" "));
		out.println(TjUtils.to80Chars(""));
		
		out.println();
		
		out.println("Ingrese la posición de las piezas BLANCAS con notacion estandar:");
		out.println("Ejemplo: Rh6, d2, Aa2");
		
		String blancas = readLine();
		out.println("Ahora ingrese la posición de las piezas NEGRAS:");
		String negras = readLine();
		
		String[] piezas = blancas.split(",");
		for(String pieza : piezas){
			
			
			
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
