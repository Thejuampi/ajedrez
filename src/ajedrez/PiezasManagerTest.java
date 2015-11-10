package ajedrez;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;

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
	

}
