package ar.edu.unq.games.vainillautils;

import org.junit.Assert;
import org.junit.Test;


public class Vector2DTest {

	@Test
	public void testModule(){
		Assert.assertEquals(1, new Vector2D(1, 0).getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(-1, 0).getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(0, 1).getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(0, -1).getModule(), 0.01);
		Assert.assertEquals(2, new Vector2D(Math.sqrt(2), -Math.sqrt(2)).getModule(), 0.01);
		Assert.assertEquals(6.40, new Vector2D(5, -4).getModule(), 0.01);
	}
	
	@Test
	public void testVersor(){
		Assert.assertEquals(1, new Vector2D(1, 0).asVersor().getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(-1, 0).asVersor().getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(0, 1).asVersor().getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(0, -1).asVersor().getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(Math.sqrt(2), -Math.sqrt(2)).asVersor().getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(5, -4).asVersor().getModule(), 0.01);
	}
	
	@Test
	public void testProducto(){
		Assert.assertEquals(7, new Vector2D(1, 0).producto(7).getModule(), 0.01);
		Assert.assertEquals(8, new Vector2D(-1, 0).producto(8).getModule(), 0.01);
		Assert.assertEquals(3, new Vector2D(0, 1).producto(-3).getModule(), 0.01);
		Assert.assertEquals(1, new Vector2D(0, -1).producto(-1).getModule(), 0.01);
		Assert.assertEquals(14, new Vector2D(Math.sqrt(2), -Math.sqrt(2)).producto(-7).getModule(), 0.01);
	}
	
	@Test
	public void testSuma(){
		Assert.assertEquals(new Vector2D(6,3), new Vector2D(2, 1).suma(new Vector2D(4,2)));
		Assert.assertEquals(new Vector2D(-3,3), new Vector2D(3, 3).suma(new Vector2D(-6,0)));
	}
	
}
