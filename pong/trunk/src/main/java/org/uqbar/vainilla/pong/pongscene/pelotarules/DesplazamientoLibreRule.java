package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PelotaRule;
import org.uqbar.vainilla.pong.pongscene.PongScene;
import org.uqbar.vainilla.utils.Vector2D;

public class DesplazamientoLibreRule implements PelotaRule {

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return true;
	}

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		pelota.setX(nuevaPosicion.getX());
		pelota.setY(nuevaPosicion.getY());
	}

}
