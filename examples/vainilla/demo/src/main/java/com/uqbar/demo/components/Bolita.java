package com.uqbar.demo.components;

import java.awt.Color;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;

public class Bolita extends GameComponent<GameScene> {
	private int speed = 200;
	private int diameter = 60;
	private Color color = Color.RED;

	public Bolita(int x, int y) {
		super(x, y);

		this.setAppearance(new Circle(this.color, this.diameter));
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);

		this.move(0, deltaState.getDelta() * this.speed);

		if(this.getY() > this.getGame().getDisplayHeight() - this.diameter) {
			this.setY(-this.diameter);
		}
	}
}