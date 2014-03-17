package org.uqbar.vainilla.pong.pongscene;


import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Raqueta extends GameComponent<PongScene> {


	private double velocidad;
	
	private double velocidadInicial;
	private double xInicial;
	private double yInicial;

	private double xMin;
	private double xMax;
	
	private RaquetaStrategy strategy;

	public Raqueta(double x, double y, int ancho, int alto, Color color, double velocidad, double xMin, double xMax, RaquetaStrategy strategy) {
		super(new Rectangle(color, ancho, alto), x, y);
		this.setVelocidad(velocidad);
		this.xInicial = x;
		this.yInicial = y;
		this.velocidadInicial = velocidad;
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.strategy = strategy;
	}

	public void izquierda() {
		this.setX(Math.max(this.getX()-getVelocidad(), getxMin()));
	}
	
	public void derecha() {
		this.setX(Math.min(getxMax() - this.getAppearance().getWidth(), this.getX()+getVelocidad()));
	}
	
	public void centrar() {
		this.setVelocidad(velocidadInicial);
		this.setX(xInicial);
		this.setY(yInicial);
	}

	public RaquetaStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(RaquetaStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		this.strategy.update(this, this.getScene(), deltaState);
		super.update(deltaState);
	}

	public double getxMin() {
		return xMin;
	}

	public void setxMin(double xMin) {
		this.xMin = xMin;
	}

	public double getxMax() {
		return xMax;
	}

	public void setxMax(double xMax) {
		this.xMax = xMax;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	
}
