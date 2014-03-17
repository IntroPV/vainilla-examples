package org.uqbar.vainilla.pong.pongscene;

import com.uqbar.vainilla.DeltaState;

public interface RaquetaStrategy {

	public void update(Raqueta raqueta, PongScene scene, DeltaState deltaState);
}
