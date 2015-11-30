package ajedrez_;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import ajedrez.Dama;
import ajedrez.Pieza;
import ajedrez.PiezasManager;
import ajedrez.Posicion;
import ajedrez.Rey;

public class PiezasManagerTest {

	PiezasManager manager = PiezasManager.INSTANCE;
	
	@Test
	public void testParseReyValido() {
		assertNotNull(manager.parse("Ra1"));
	}
	
	@Test
	public void testParseReyNoValido(){
		assertNull(manager.parse("Ra9"));
		assertNull(manager.parse("Ri1"));
	}
	
	@Test
	public void testParsePeonValido(){
		assertNotNull(manager.parse("b1"));
	}
	
	@Test
	public void testParsePeonNoValido(){
		assertNull(manager.parse("i1"));
		assertNull(manager.parse("a9"));
	}
	
	@Test
	public void testStringsNoValidos(){
		assertNull(manager.parse("abcd"));
		assertNull(manager.parse("ab"));
		assertNull(manager.parse(""));
		assertNull(manager.parse("78c"));
		assertNull(manager.parse("l8"));
		assertNull(manager.parse("z19"));
	}
	
	@Test
	public void testJaqueMate() {
		
		Rey reyBlanco = new Rey(new Posicion("6", "c"));
		Dama damaBlanca = new Dama(new Posicion("7", "b"));
		Collection<Pieza> blancas = Lists.newArrayList(reyBlanco , damaBlanca);
		Rey reyNegro = new Rey(new Posicion("7", "a"));
		reyNegro.setBlanca(false);
		Collection<Pieza> negras = Lists.newArrayList(reyNegro);
		
		manager.addPieza(reyBlanco.getPosicionActual(), reyBlanco);
		manager.addPieza(damaBlanca.getPosicionActual(), damaBlanca);
		manager.addPieza(reyNegro.getPosicionActual(), reyNegro);
		
		boolean result = manager.isJaqueMate(damaBlanca,damaBlanca.getPosicionActual() , blancas, negras);
		
		Assert.assertTrue(result);
		
	}

}
