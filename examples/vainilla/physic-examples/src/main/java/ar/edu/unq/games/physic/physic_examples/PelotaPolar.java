package ar.edu.unq.games.physic.physic_examples;

import java.awt.Color;
import java.awt.Graphics2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class PelotaPolar<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	private Vector2D velocidadPolar = new Vector2D(0, 0);
	private int diametro;
	private double rapidezDisparo = 100;

	public PelotaPolar(int diameter) {
		this.diametro = diameter;
		this.setAppearance(new Circle(Color.BLUE, diameter));
	}

	@Override
	public void update(DeltaState deltaState) {
		actualizarVelocidad(deltaState);
		actualizarPosicion(deltaState.getDelta());
	}

	public void actualizarVelocidad(DeltaState deltaState) {
		double deltaSpeed = 5;

		double ro = velocidadPolar.getX();
		double theta = velocidadPolar.getY();

		if (deltaState.isKeyBeingHold(Key.UP)) {
			ro += deltaSpeed * deltaState.getDelta();
		} else if (deltaState.isKeyBeingHold(Key.DOWN)) {
			ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));
		} else if (deltaState.isKeyBeingHold(Key.RIGHT)) {
			theta = theta + (getVelocidadAngular() * deltaState.getDelta());
			theta = ajustarAnguloEntrePiYMenosPi(theta);
		} else if (deltaState.isKeyBeingHold(Key.LEFT)) {
			theta = theta - (getVelocidadAngular() * deltaState.getDelta());
			theta = ajustarAnguloEntrePiYMenosPi(theta);
		}
		if (deltaState.isKeyPressed(Key.SPACE)) {
			disparar();
		}
		this.setVelocidadPolar( new Vector2D(ro, theta) );
	}

	//cuantos grados por segundo va a girar
	public double getVelocidadAngular() {
		return Math.PI; // media vuelta por segundo
	}

	//TODO, asi como está solo sirve si me pasé una vuelta sola, por eso 
	//solo se puede usar en el caso que el delta aplicado sobre el angulo
	//sean menor a 2*PI.
	//Lo que hace el metodo es quitar o sumar 2*PI para que quede en el angulo en ese radio
	protected double ajustarAnguloEntrePiYMenosPi(double theta) {
		theta = theta > getVelocidadAngular() ? theta - 2 * getVelocidadAngular() : theta;
		theta = theta < -getVelocidadAngular() ? theta + 2 * getVelocidadAngular() : theta;
		return theta;
	}

	protected void disparar() {
		Vector2D centro = getOrigenDisparo();
		Bala<GameScene> bala = new Bala<GameScene>(centro.getX(),
				centro.getY(),
				getVelocidadBala());
		
		this.getScene().addComponent(bala);
	}

	protected Vector2D getVelocidadBala() {
		return this.velocidadPolar.suma(new Vector2D(rapidezDisparo, 0))
				.toCartesians();
	}

	// Esta version la bala sale del centro del objeto
	protected Vector2D getOrigenDisparo() {
		return getCentro();
	}

	public void actualizarPosicion(double delta) {

		Vector2D cartesianPosition = getPosition().suma(
				getVelocidadPolar().toCartesians().producto(delta));

		setXVisible(cartesianPosition.getX());
		setYVisible(cartesianPosition.getY());
	}

	public Vector2D getVelocidadPolar() {
		return velocidadPolar;
	}

	private void setYVisible(double yCartesiano) {
		if (yCartesiano > 0
				&& yCartesiano < this.getGame().getDisplayHeight() - diametro) {
			this.setY(yCartesiano);
		}
	}

	private void setXVisible(double xCartesiano) {
		if (xCartesiano > 0
				&& xCartesiano < this.getGame().getDisplayWidth() - diametro) {
			this.setX(xCartesiano);
		}
	}

	private Vector2D getPosition() {
		return new Vector2D(this.getX(), this.getY());
	}

	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
		renderVelocity(graphics);
	}

	private void renderVelocity(Graphics2D graphics) {
		Vector2D vel = this.velocidadPolar.toCartesians();
		Color c = graphics.getColor();
		graphics.setColor(Color.RED);
		graphics.drawLine((int) this.getX() + diametro / 2, (int) this.getY()
				+ diametro / 2,
				(int) (this.getX() + diametro / 2 + vel.getX()),
				(int) (this.getY() + diametro / 2 + vel.getY()));
		graphics.setColor(c);
	}

	public String getHelp() {
		return "Usa cordenadas polares para simplicar \n"
				+ "el cálculo de la dirección y velocidad\n"
				+ "Use IZQ y DER para cambiar el ángulo\n"
				+ "Use ARR y ABJ para cambiar la rapidez\n"
				+ "En rojo se dibuja el vector velocidad\n"
				+ "Con el SPACE se dispara, la velocidad es constante\n"
				+ "con respecto a la velocidad inicial de la nave";
	}

	protected int getDiametro() {
		return diametro;
	}

	protected Vector2D getCentro() {
		return new Vector2D(this.getX() + diametro / 2, this.getY() + diametro
				/ 2);
	}
	protected double getRapidezDisparo() {
		return rapidezDisparo;
	}
	
	protected void setVelocidadPolar(Vector2D vector2d) {
		this.velocidadPolar = vector2d;
	}
	
}
