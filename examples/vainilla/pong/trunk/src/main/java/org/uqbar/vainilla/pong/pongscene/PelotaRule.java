package org.uqbar.vainilla.pong.pongscene;

import org.uqbar.vainilla.utils.Vector2D;

public interface PelotaRule {

	boolean mustApply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);
	void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene);
}
