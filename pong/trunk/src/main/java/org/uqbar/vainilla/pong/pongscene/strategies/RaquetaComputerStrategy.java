package org.uqbar.vainilla.pong.pongscene.strategies;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PongScene;
import org.uqbar.vainilla.pong.pongscene.Raqueta;
import org.uqbar.vainilla.pong.pongscene.RaquetaStrategy;

import com.uqbar.vainilla.DeltaState;

public class RaquetaComputerStrategy implements RaquetaStrategy {

	@Override
	public void update(Raqueta raqueta, PongScene scene, DeltaState deltaState) {
		if (seAcerca(raqueta, scene.getPelota())) {
			irALaCaida(raqueta, scene, deltaState.getDelta());
		}
		else {
			irAlCentro(raqueta, deltaState.getDelta());
		}

	}

	private void irAlCentro(Raqueta raqueta, double delta) {
		double xMedio = (raqueta.getxMax() - raqueta.getxMin()) / 2;
		
		if(xMedio < raqueta.getX() + raqueta.getAppearance().getWidth()/2 ) {
			raqueta.izquierda(delta);
		};
		if(xMedio > raqueta.getX() + raqueta.getAppearance().getWidth()/2 ) {
			raqueta.derecha(delta);
		};
		
	}

	private void irALaCaida(Raqueta raqueta, PongScene scene, double delta) {
		double xDeCaida = calcularXCaida(raqueta, scene.getPelota());
		if (xDeCaida < raqueta.getX() + raqueta.getAppearance().getWidth()/2) {
			raqueta.izquierda(delta);
		}
		if (xDeCaida > raqueta.getX() + raqueta.getAppearance().getWidth()/2) {
			raqueta.derecha(delta);
		}
	}

	private boolean seAcerca(Raqueta raqueta, Pelota pelota) {
		return (pelota.getDireccion().getY() < 0 && raqueta.getY()
				+ raqueta.getAppearance().getHeight() < pelota.getY())
				|| (pelota.getDireccion().getY() > 0 && raqueta.getY() > pelota
						.getY() + pelota.getAppearance().getHeight());
	}

	private double calcularXCaida(Raqueta raqueta, Pelota pelota) {
		double yRaqueta;
		double yPelota;
		double direccionXPelota = pelota.getDireccion().getX();
		double direccionYPelota = pelota.getDireccion().getY();
		double xPelota = pelota.getX() + pelota.getAppearance().getWidth() / 2;
		if (pelota.getDireccion().getY() > 0) {
			yRaqueta = raqueta.getY();
			yPelota = pelota.getY() + pelota.getAppearance().getHeight();
		} else {
			yRaqueta = raqueta.getY() + raqueta.getAppearance().getHeight();
			yPelota = pelota.getY();
		}

		return (yRaqueta - yPelota) * (direccionXPelota / direccionYPelota)
				+ xPelota;
	}

}
