package ar.edu.unq.games.physic.physic_examples;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota2D<T extends GameScene> extends GameComponent<T>{

	
	private Vector2D aceleracion = new Vector2D(0, 0);
	private Vector2D velocidad= new Vector2D(0, 0);
	private int diametro;
	
	public Pelota2D(int diameter) {
		this.diametro = diameter;
		this.setAppearance(new Circle(Color.BLUE, diameter));
	}
	

	@Override
	public void update(DeltaState deltaState) {
		
		actualizarAceleracion(deltaState);
		actualizarVelocidad(deltaState);
		
		Vector2D posicionPropuesta = this.getPosicion().suma(getVelocidad().producto(deltaState.getDelta()));
		actualizarPosicion(posicionPropuesta);
	}


	private void actualizarAceleracion(DeltaState deltaState) {
		double deltaAcceleration = 0.3;
		double deltaX = 0;
		double deltaY = 0;
		
		if(deltaState.isKeyBeingHold(Key.RIGHT)) {
			deltaX = deltaAcceleration; 
		}
		else if(deltaState.isKeyBeingHold(Key.LEFT)){
			deltaX = -deltaAcceleration;
		}
		
		if(deltaState.isKeyBeingHold(Key.UP)) {
			deltaY = deltaAcceleration; 
		}
		else if(deltaState.isKeyBeingHold(Key.DOWN)){
			deltaY = -deltaAcceleration;
		}
		
		setAcceleration(getAceleracion().suma(new Vector2D(deltaX, deltaY)));
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
		
		if (y + getDiameter() <= 0) {
			y = this.getGame().getDisplayHeight();
		} else if (y >= this.getGame().getDisplayHeight()) {
			y = -getDiameter();
		}
		this.setX(x);
		this.setY(y);
		
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

	public String getHelp() {
		return "Muestra el uso de la aceleración en dos dimensiones.\n "
				+ "La aceleracion y la velocidad son vectores\n"
				+ "Use las flechas DER e IZQ para cambiar la aceleración en X." 
				+ "Use las flechas ARR y ABJ para cambiar la aceleración en Y." ;
	}

	
}
