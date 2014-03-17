package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PongScene;

import ar.edu.unq.games.vainillautils.Vector2D;

public class GolComputerRule extends GolRule {

	public GolComputerRule(PongScene scene) {
		super(scene.getMarcadorComputer());
	}

	@Override
	protected boolean isGol(Pelota pelota, Vector2D nuevaPosicion) {
		return nuevaPosicion.getY() > pelota.getGame().getDisplayHeight();
	}

}
