package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PelotaRule;
import org.uqbar.vainilla.pong.pongscene.PongScene;
import org.uqbar.vainilla.utils.Vector2D;

public abstract class ChoqueLateralRule implements PelotaRule {

	@Override
	public abstract boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene);

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		this.invertirX(pelota);
		pelota.setX(this.newX(pelota, nuevaPosicion, scene));
		pelota.setY(nuevaPosicion.getY());
	}

	protected abstract double newX(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);

	protected void invertirX(Pelota pelota) {
		pelota.setDireccion(new Vector2D(-pelota.getDireccion().getX(), pelota.getDireccion().getY()));
	}


}
