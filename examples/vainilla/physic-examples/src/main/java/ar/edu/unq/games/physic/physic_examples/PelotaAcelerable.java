package ar.edu.unq.games.physic.physic_examples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;

public class PelotaAcelerable<T extends GameScene> extends GameComponent<T> {

	private static double aceleracionModulo = 100; // pixeles sobre segundos
													// cuadrados

	private double ejeRotacional = 0; //da la rotacion de la nave
	private double friccion = 0.3; // una aceleracion negativa para que frene la nave
	private double rapidezDisparo = 50; 
	private Vector2D aceleracion = new Vector2D(aceleracionModulo, 0); //la aceleracion la manejo en polares para modificar los valores 
	private Vector2D velocidad = new Vector2D(0, 0); // la velocidad está en cartesianas
	private int diametro; //diametro de la nave

	public PelotaAcelerable(int diametro) {
		this.diametro = diametro;
		this.setAppearance(new Circle(Color.BLUE, diametro));
		this.setAceleracion(new Vector2D(aceleracionModulo, ejeRotacional));
	}


	@Override
	public void update(DeltaState deltaState) {
		actualizarAceleracion(deltaState);
		actualizarVelocidad(deltaState);
		actualizarPosicion(deltaState);
		actualizarDisparo(deltaState);
	}


	private void actualizarDisparo(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.SPACE)) {
			disparar();
		}
	}

	//Modifica el ángulo de la nave y por consiguiente la aceleracion que se va a aplicar
	//de manera impulsiva al apretar la tecla de aceleracion
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
		this.setAceleracion(new Vector2D(aceleracionModulo, ejeRotacional));
	}

	// cuanto gira por segundo
	public double getVelocidadAngular() {
		return Math.PI; //media vuelta por segundo
	}

	protected void actualizarVelocidad(DeltaState deltaState) {
		aplicarImpulso(deltaState);
		aplicarFriccion(deltaState);
	}

	private void aplicarFriccion(DeltaState deltaState) {
		if (!deltaState.isKeyPressed(Key.UP)) {
			this.setVelocidad(this.getVelocidad().suma( 
					this.getVelocidad().producto(
							-deltaState.getDelta() * friccion)));
		}
	}

	private void aplicarImpulso(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.UP)) {
			this.setVelocidad(this.getVelocidad().suma(
					this.getAceleracion().toCartesians().producto(deltaState.getDelta())));
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
		Stroke stroke2 = new BasicStroke((float) getDiametro() / 10);
		graphics.setStroke(stroke2);
		Vector2D centro = this.getCentro();
		Vector2D puntaDelCanion = puntaCanion();
		graphics.drawLine((int) centro.getX(), (int) centro.getY(),
				(int) puntaDelCanion.getX(), (int) puntaDelCanion.getY());
		graphics.setColor(c);
		graphics.setStroke(stroke);
	}

	protected Vector2D getCentro() {
		return new Vector2D(this.getX() + getDiametro() / 2, this.getY()
				+ getDiametro() / 2);
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		this.renderCanion(graphics);
		this.renderVelocity(graphics);
	}

	private Vector2D puntaCanion() {
		Vector2D polarCanionPosition = new Vector2D(this.getDiametro(),
				this.ejeRotacional);
		Vector2D cartesianCanionPosition = polarCanionPosition.toCartesians();
		return cartesianCanionPosition.suma(this.getCentro());
	}

	protected void disparar() {
		Vector2D centro = getOrigenDisparo();
		Bala<GameScene> bala = new Bala<GameScene>(centro.getX(),
				centro.getY(), getVelocidadBala());

		this.getScene().addComponent(bala);
	}

	protected void actualizarPosicion(DeltaState delta) {
		Vector2D posicionPropuesta = this.getPosicion().suma(getVelocidad().producto(delta.getDelta()));

		double x = posicionPropuesta.getX();
		double y = posicionPropuesta.getY();

		if (x + getDiametro() <= 0) {
			x = this.getGame().getDisplayWidth();
		} else if (x >= this.getGame().getDisplayWidth()) {
			x = -getDiametro();
		}

		if (y + getDiametro() <= 0) {
			y = this.getGame().getDisplayHeight();
		} else if (y >= this.getGame().getDisplayHeight()) {
			y = -getDiametro();
		}
		this.setX(x);
		this.setY(y);

	}

	public double getEjeRotacional() {
		return ejeRotacional;
	}


	protected Vector2D getVelocidadBala() {
		return this.getVelocidad().suma(
				new Vector2D(rapidezDisparo, ejeRotacional).toCartesians());
	}

	protected Vector2D getOrigenDisparo() {
		return puntaCanion();
	}

	private Vector2D getPosicion() {
		return new Vector2D(this.getX(), this.getY());
	}

	public int getDiametro() {
		return diametro;
	}

	public Vector2D getAceleracion() {
		return aceleracion;
	}

	public void setAceleracion(Vector2D aceleracion) {
		this.aceleracion = aceleracion;
	}

	public Vector2D getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Vector2D velocidad) {
		this.velocidad = velocidad;
	}
	
	public String getHelp() {
		String out = "Acelera la nave sin modificar la velocidad directamente\n";
		out += "IZQ y DER giran la nave\n";
		out += "ARR aplica un impulso en la dirección del eje de la nave\n";
		out += "SPACE dispara en la dirección del eje de la nave";
		return out;
	}

}
