package com.uqbar.bolitas.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import com.uqbar.bolitas.scenes.Level;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class Bolita extends GameComponent<Level> {

	private int radius = 15;
	private double speed = 30;
	private double i, j;
	private Color changedColor;

	public Bolita(Color baseColor, Color changedColor) {
		this.setAppearance(new Circle(baseColor, 2 * this.radius));

		this.changedColor = changedColor;
	}

	@Override
	public void onSceneActivated() {
		Random random = new Random();

		this.setX(random.nextDouble() * this.getGame().getDisplayWidth());
		this.setY(random.nextDouble() * this.getGame().getDisplayHeight());
		this.i = random.nextDouble() * 2 - 1;
		this.j = random.nextDouble() * 2 - 1;

		double m = Math.sqrt(this.i * this.i + this.j * this.j);

		this.i = this.i / m;
		this.j = this.j / m;

		super.onSceneActivated();
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
	}

	@Override
	public void update(DeltaState deltaState) {
		double advanced = this.speed * deltaState.getDelta();

		this.move(this.i * advanced, this.j * advanced);

		for(Bolita bolita : this.getScene().getBolitas()) {
			if(this.colisionaConBolita(bolita) && this.changedColor != bolita.changedColor) {
				this.setAppearance(new Circle(this.changedColor, 2 * this.radius));
			}
		}

		super.update(deltaState);
	}

	private boolean colisionaConBolita(Bolita bolita) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstCircle(
			this.getX(), this.getY(), this.radius,
			bolita.getX(), bolita.getY(), bolita.radius
			);
	}
}