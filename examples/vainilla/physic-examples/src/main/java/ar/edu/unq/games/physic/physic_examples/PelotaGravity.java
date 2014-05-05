package ar.edu.unq.games.physic.physic_examples;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;

public class PelotaGravity<T extends GameScene> extends GameComponent<T>{
	
	//es la gravedad
	private Vector2D aceleracion = new Vector2D(0, 9.8);
	private Vector2D velocidad= new Vector2D(0, 0);
	
	private double friccion = 0.1;
	private double elasticidad = 0.4;
	
	private int diametro;
	
	private double aceleracionXAAplicar = 0;
	private double aceleracionYAAplicar = 0;
	
	
	
	public PelotaGravity(int diameter) {
		this.diametro = diameter;
		this.setAppearance(new Circle(Color.BLUE, diameter));
	}
	

	@Override
	public void update(DeltaState deltaState) {
		
		actualizarAceleracionAAplicar(deltaState);
		actualizarVelocidad(deltaState);
		Vector2D posicionPropuesta = this.getPosicion().suma(getVelocidad().producto(deltaState.getDelta()));
		actualizarPosicion(posicionPropuesta);
		aplicarFriccion(deltaState.getDelta());
	}


	private void actualizarAceleracionAAplicar(DeltaState deltaState) {
		double deltaAcceleration = 0.3;
		
		if(deltaState.isKeyBeingHold(Key.RIGHT)) {
			setAceleracionXAAplicar(deltaAcceleration + aceleracionXAAplicar); 
		}
		else if(deltaState.isKeyBeingHold(Key.LEFT)){
			setAceleracionXAAplicar(aceleracionXAAplicar -deltaAcceleration );
		}
		
		if(deltaState.isKeyBeingHold(Key.UP)) {
			setAceleracionYAAplicar(aceleracionYAAplicar + deltaAcceleration); 
		}
		else if(deltaState.isKeyBeingHold(Key.DOWN)){
			setAceleracionYAAplicar(aceleracionYAAplicar -deltaAcceleration);
		}
		
		if(deltaState.isKeyPressed(Key.ENTER)) {
			aplicarAceleracionInstantanea(new Vector2D(getAceleracionXAAplicar(), getAceleracionYAAplicar()));
		}
		if(deltaState.isKeyBeingHold(Key.SPACE)) {
			aplicarImpulso(deltaState.getDelta(), new Vector2D(getAceleracionXAAplicar(), getAceleracionYAAplicar()));			
		}
	}


	private void actualizarVelocidad(DeltaState deltaState) {
		Vector2D deltaVelocidad = getAceleracion().producto(deltaState.getDelta());
		setVelocidad(getVelocidad().suma(deltaVelocidad));
	}


	private void actualizarPosicion(Vector2D posicionPropuesta) {
		
		double x = posicionPropuesta.getX();
		double y = posicionPropuesta.getY();
		
		if (x + getDiameter() <= 0) {
			x = this.getGame().getDisplayWidth();
		} else if (x >= this.getGame().getDisplayWidth()) {
			x = -getDiameter();
		}
		
		if (y >= this.getGame().getDisplayHeight() - diametro) {
			y = this.getGame().getDisplayHeight() - diametro;
			this.rebotar();
		}
		this.setX(x);
		this.setY(y);
		
	}

	private void rebotar() {
		this.velocidad = new Vector2D(velocidad.getX(), -velocidad.getY() * elasticidad);			
	}
	
	public double getFriccion() {
		return friccion;
	}


	public void setFriccion(double friccion) {
		this.friccion = friccion;
	}


	public double getElasticidad() {
		return elasticidad;
	}


	public void setElasticidad(double elasticidad) {
		this.elasticidad = elasticidad;
	}


	public void setAceleracion(Vector2D aceleracion) {
		this.aceleracion = aceleracion;
	}


	public void setDiameter(int diameter) {
		this.diametro = diameter;
	}


	private void aplicarFriccion(double delta) {
		if(distanciaAlPiso() < 2 ) {
			this.velocidad = new Vector2D(velocidad.getX() - (velocidad.getX() * friccion * delta), velocidad.getY());
		}
	}


	private double distanciaAlPiso() {
		return Math.abs(this.getY() - (this.getGame().getDisplayHeight()-this.diametro) );
	}

	private void aplicarAceleracionInstantanea(Vector2D vector2d) {
		this.velocidad = this.velocidad.suma(vector2d);
	}
	
	private void aplicarImpulso(double deltaT, Vector2D vector2d) {
		this.velocidad = this.velocidad.suma(vector2d.producto(deltaT));
	}


	public Vector2D getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Vector2D velocidad) {
		this.velocidad= velocidad;
	}

	public Vector2D getAceleracion() {
		return aceleracion;
	}

	public void setAcceleration(Vector2D aceleracion) {
		this.aceleracion = aceleracion;
	}

	public double getDiameter() {
		return diametro;
	}

	public Vector2D getPosicion() {
		return new Vector2D(this.getX(), this.getY());
	}


	public double getAceleracionXAAplicar() {
		return aceleracionXAAplicar;
	}


	public void setAceleracionXAAplicar(double aceleracionXAAplicar) {
		this.aceleracionXAAplicar = aceleracionXAAplicar;
	}


	public double getAceleracionYAAplicar() {
		return aceleracionYAAplicar;
	}


	public void setAceleracionYAAplicar(double aceleracionYAAplicar) {
		this.aceleracionYAAplicar = aceleracionYAAplicar;
	}

	public String getHelp() {
	return "Utiliza la aceleracion para modelar la gravedad.\n "
	+ "Además se puede aplicar una aceleración instantánea\n"
	+ "o un impulso\n"
	+ "Use las flechas DER e IZQ para setear\nla aceleración en X a aplicar.\n" 
	+ "Use las flechas ARR y ABJ para cambiar\nla aceleración en Y a aplicar.\n"
	+ "Use ENTER para aplicar instantáneamente\n"
	+ "Use SPACE para aplicar como impulso\n"
	+ "La friccion frena en el eje X \ny la gravedad en el Y";
	}

}
