package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Marcador;
import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PongScene;

import com.uqbar.vainilla.GameScene;

import ar.edu.unq.games.vainillautils.Vector2D;

public class GolPlayerRule extends GolRule {

	public GolPlayerRule(PongScene scene) {
		super(scene.getMarcadorPlayer());
	}

	@Override
	protected boolean isGol(Pelota pelota, Vector2D nuevaPosicion) {

		return nuevaPosicion.getY() + pelota.getAppearance().getHeight() < 0;

	}

}
