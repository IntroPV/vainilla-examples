package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PongScene;

import ar.edu.unq.games.vainillautils.Vector2D;

public class ChoqueAIzquierdaRule extends ChoqueLateralRule {

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return nuevaPosicion.getX() <= 0;
	}

	@Override
	protected double newX(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		
		return 0;
	}

}
