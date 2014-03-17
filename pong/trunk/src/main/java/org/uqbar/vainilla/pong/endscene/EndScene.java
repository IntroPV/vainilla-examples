package org.uqbar.vainilla.pong.endscene;

import org.uqbar.vainilla.pong.pongscene.Marcador;

import com.uqbar.vainilla.GameScene;

public class EndScene extends GameScene {

	public EndScene(Marcador marcadorComputer, Marcador marcadorPlayer, double x, double y) {
		super(new WinOrLoseComponent(marcadorComputer, marcadorPlayer, x, y));
	}

}
