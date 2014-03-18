package org.uqbar.vainilla.pong.pongscene.strategies;

import com.uqbar.vainilla.events.constants.Key;

public class Player2Strategy extends RaquetaPlayerStrategy {
	public Player2Strategy() {
		super();
		this.setLeftKey(Key.A);
		this.setRigthKey(Key.S);
	}
}
