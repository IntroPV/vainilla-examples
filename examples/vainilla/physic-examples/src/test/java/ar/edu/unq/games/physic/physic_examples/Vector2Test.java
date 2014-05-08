package ar.edu.unq.games.physic.physic_examples;

import junit.framework.Assert;

import org.junit.Test;

public class Vector2Test {

	@Test
	public void coordinates() {
		Assert.assertTrue(new Vector2D(1.5, -1).distance(new Vector2D(1.5, -1).toPolar().toCartesians()) < 0.01);
	}
}
