package org.uqbar.vainilla.pong.pongscene;

import java.awt.Color;

import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Pelota extends GameComponent<PongScene> {

	private Vector2D direccion;
	private double velocidad;
	private double xInicial;
	private double yInicial;
	private Vector2D direccionInicial;
	private double velocidadInicial;
	private double velocidadStep = 0.1;
	
	public Pelota(int radio, double xInicial, double yInicial, Vector2D direccionInicial, double velocidadInicial) {
		super(new Circle(Color.RED, radio), xInicial, xInicial);
		this.xInicial = xInicial;
		this.yInicial = yInicial;
		this.direccion = direccionInicial.asVersor();
		this.direccionInicial = this.direccion;
		this.velocidad = velocidadInicial;
		this.velocidadInicial = velocidadInicial;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		Vector2D nuevaPosicion = this.direccion.producto(velocidad).suma(new Vector2D(this.getX(), this.getY()));
		//TODO convertir en reglas
		
		if(this.getScene().resolverSiColisiona(this, nuevaPosicion)) {
			//break;
		} 
		else if(this.golPlayer(nuevaPosicion)) {
			this.centrar();
			this.getScene().golPlayer();
		}
		else if(this.golComputer(nuevaPosicion)) {
			this.centrar();
			this.getScene().golComputer();
		}
		else if(this.chocaALaDerecha(nuevaPosicion)) {
			this.invertirX();
			this.setX(this.getGame().getDisplayWidth()-this.getAppearance().getWidth());
			this.setY(nuevaPosicion.getY());
		}
		else if(this.chocaALaIzquierda(nuevaPosicion)) {
			this.invertirX();
			this.setX(0);
			this.setY(nuevaPosicion.getY());
		}
		else {
			this.setearNuevaPosicion(nuevaPosicion);
		}
		
		super.update(deltaState);
	}


	private void invertirY() {
		this.direccion= new Vector2D(this.direccion.getX(), -this.direccion.getY());
	}

	private boolean golComputer(Vector2D nuevaPosicion) {
		return nuevaPosicion.getY() > this.getGame().getDisplayHeight();
	}

	private boolean golPlayer(Vector2D nuevaPosicion) {
		return nuevaPosicion.getY() + this.getAppearance().getHeight() < 0;
	}

	private void centrar() {
		this.setX(this.xInicial);
		this.setY(this.yInicial);
		this.direccion = this.direccionInicial;
		this.velocidad = this.velocidadInicial;
	}

	private boolean chocaALaIzquierda(Vector2D nuevaPosicion) {
		return nuevaPosicion.getX() <= 0;
	}

	private boolean chocaALaDerecha(Vector2D nuevaPosicion) {
		return this.getGame().getDisplayWidth() <= nuevaPosicion.getX() + this.getAppearance().getWidth();
	}

	private void invertirX() {
		this.direccion= new Vector2D(-this.direccion.getX(), this.direccion.getY());
	}

	public void setearNuevaPosicion(Vector2D nuevaPosicion) {
		this.setX(nuevaPosicion.getX());
		this.setY(nuevaPosicion.getY());
	}

	public void setDireccion(Vector2D vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direccion;
	}

	public void masRapido() {
		this.velocidad = this.velocidad + this.getVelocidadStep();
	}

	public double getVelocidadStep() {
		return velocidadStep;
	}

	public void setVelocidadStep(double velocidadStep) {
		this.velocidadStep = velocidadStep;
	}
	
}
