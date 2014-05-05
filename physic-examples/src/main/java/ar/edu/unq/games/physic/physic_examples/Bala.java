package ar.edu.unq.games.physic.physic_examples;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;

public class Bala<T extends GameScene> extends GameComponent<GameScene> {

	private Vector2D velocity;

	public Bala(double x, double y, Vector2D velocity) {
		super(new Rectangle(Color.BLUE, 10, 10), x, y);
		this.velocity = velocity;
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D newDelta = velocity.producto(deltaState.getDelta());
		this.setX(this.getX() + newDelta.getX());
		this.setY(this.getY() + newDelta.getY());
	}

}
