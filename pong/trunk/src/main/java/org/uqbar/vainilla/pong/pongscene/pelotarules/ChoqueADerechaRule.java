package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PongScene;
import org.uqbar.vainilla.utils.Vector2D;

public class ChoqueADerechaRule extends ChoqueLateralRule {

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return pelota.getGame().getDisplayWidth() <= nuevaPosicion.getX() + pelota.getAppearance().getWidth();
	}

	@Override
	protected double newX(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		return pelota.getGame().getDisplayWidth()-pelota.getAppearance().getWidth();
	}

}
