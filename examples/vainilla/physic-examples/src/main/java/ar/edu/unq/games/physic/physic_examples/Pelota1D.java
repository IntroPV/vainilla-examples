package ar.edu.unq.games.physic.physic_examples;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota1D<T extends GameScene> extends GameComponent<T> {

	private int direccion = 1;
	private double velocidad = 100;
	private double aceleracion = 0;
	private int diametro;


	public Pelota1D(int diameter) {
		this.diametro = diameter;
		this.setAppearance(new Circle(Color.BLUE, diameter));
	}
	

	@Override
	public void update(DeltaState deltaState) {
		double posicionPropuesta = this.getX() + getDirection() * getVelocity()
				* deltaState.getDelta();
		
		actualizarAceleracion(deltaState);
		actualizarVelocidad(deltaState);
		actualizarPosicion(posicionPropuesta);
	}


	private void actualizarAceleracion(DeltaState deltaState) {
		double deltaAcceleration = 0.3;
		if(deltaState.isKeyBeingHold(Key.RIGHT)) {
			setAcceleration(aceleracion + (deltaAcceleration * deltaState.getDelta()));
		}
		else if(deltaState.isKeyBeingHold(Key.LEFT)){
			setAcceleration(aceleracion  - (deltaAcceleration * deltaState.getDelta()));					
		}
	}


	private void actualizarVelocidad(DeltaState deltaState) {
		setVelocity(getVelocity() + getAcceleration()*deltaState.getDelta());
	}


	private void actualizarPosicion(double posicionPropuesta) {
		if (posicionPropuesta <= 0) {
			this.setX(0);
			setDirection(-getDirection());
		} else if (posicionPropuesta + getDiameter() >= this.getGame().getDisplayWidth()) {
			this.setX(this.getGame().getDisplayWidth() - getDiameter());
			setDirection(-getDirection());
		}
		else {
			this.setX(posicionPropuesta);
		}
	}

	public int getDirection() {
		return direccion;
	}

	public void setDirection(int direction) {
		this.direccion = direction;
	}

	public double getVelocity() {
		return velocidad;
	}

	public void setVelocity(double velocity) {
		this.velocidad = velocity;
	}

	public double getAcceleration() {
		return aceleracion;
	}

	public void setAcceleration(double acceleration) {
		this.aceleracion = acceleration;
	}

	public double getDiameter() {
		return diametro;
	}

	public String getHelp() {
		return "Muestra el uso de la aceleración en una dimensión.\n Use las flechas DER e IZQ para aumentar \n o disminuir la aceleración.";
	}

	
}
