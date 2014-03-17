package org.uqbar.vainilla.pong.pongscene;

import ar.edu.unq.games.vainillautils.Vector2D;

public interface PelotaRule {

	boolean mustApply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);
	void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);
}
