package ar.edu.unq.games.physic.physic_examples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.events.constants.Key;

public class PelotaAcelerablePolar<T extends GameScene> extends Pelota2D<T> {

	public double ejeRotacional = 0;
	public double aceleracionModulo = 100; //pixeles sobre segundos cuadrados
	private double friccion = 0.3;
	private double rapidezDisparo = 50;

	public PelotaAcelerablePolar(int diameter) {
		super(diameter);
		this.setAcceleration(new Vector2D(aceleracionModulo, ejeRotacional).toCartesians());
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if(deltaState.isKeyPressed(Key.SPACE)) {
			disparar();
		}
	}
	
	protected void actualizarAceleracion(DeltaState deltaState) {
		if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			ejeRotacional = AngleUtils
					.ajustarAnguloEntrePiYMenosPi(ejeRotacional
							+ getVelocidadAngular() * deltaState.getDelta());
		}
		if (deltaState.isKeyBeingHold(Key.LEFT)) {
			ejeRotacional = AngleUtils
					.ajustarAnguloEntrePiYMenosPi(ejeRotacional
							- getVelocidadAngular() * deltaState.getDelta());
		}
		this.setAcceleration(new Vector2D(this.getAceleracion().getModule(),
				ejeRotacional).toCartesians());
	}

	// cuando gira por segundo
	public double getVelocidadAngular() {
		return Math.PI;
	}

	@Override
	protected void actualizarVelocidad(DeltaState deltaState) {
		aplicarImpulso(deltaState);
		aplicarFriccion(deltaState);
	}

	private void aplicarFriccion(DeltaState deltaState) {
		if (!deltaState.isKeyPressed(Key.UP)) {
		this.setVelocidad(this.getVelocidad()
				.suma(this.getVelocidad().producto(
						-deltaState.getDelta() * friccion)));
		}
	}

	private void aplicarImpulso(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.UP)) {
			this.setVelocidad(this.getVelocidad().suma(
					this.getAceleracion().producto(deltaState.getDelta())));
		}
	}

	protected void renderVelocity(Graphics2D graphics) {
		Vector2D vel = this.getVelocidad();
		Color c = graphics.getColor();
		graphics.setColor(Color.RED);
		Vector2D centro = this.getCentro();
		graphics.drawLine((int) centro.getX(), (int) centro.getY(),
				(int) (centro.getX() + vel.getX()),
				(int) (centro.getY() + vel.getY()));
		graphics.setColor(c);
	}

	protected void renderCanion(Graphics2D graphics) {
		Color c = graphics.getColor();
		graphics.setColor(Color.green);
		Stroke stroke = graphics.getStroke();
		Stroke stroke2 = new BasicStroke((float) getDiameter() / 10);
		graphics.setStroke(stroke2);
		Vector2D centro = this.getCentro();
		Vector2D puntaDelCanion = puntaCanion();
		graphics.drawLine((int) centro.getX(), (int) centro.getY(),
				(int) puntaDelCanion.getX(), (int) puntaDelCanion.getY());
		graphics.setColor(c);
		graphics.setStroke(stroke);
	}

	protected Vector2D getCentro() {
		return new Vector2D(this.getX() + getDiameter() / 2, this.getY()
				+ getDiameter() / 2);
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		this.renderCanion(graphics);
		this.renderVelocity(graphics);
	}

	private Vector2D puntaCanion() {
		Vector2D polarCanionPosition = new Vector2D(this.getDiameter(), this.ejeRotacional);
		Vector2D cartesianCanionPosition = polarCanionPosition.toCartesians();
		return cartesianCanionPosition.suma(this.getCentro());
	}

	public double getEjeRotacional() {
		return ejeRotacional;
	}
	
	protected void disparar() {
		Vector2D centro = getOrigenDisparo();
		Bala<GameScene> bala = new Bala<GameScene>(centro.getX(),
				centro.getY(),
				getVelocidadBala());
		
		this.getScene().addComponent(bala);
	}
	
	protected Vector2D getVelocidadBala() {
		return this.getVelocidad().suma(new Vector2D(rapidezDisparo , ejeRotacional).toCartesians());
	}

	protected Vector2D getOrigenDisparo() {
		return puntaCanion();
	}


}
