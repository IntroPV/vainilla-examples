package org.uqbar.vainilla.pong.endscene;


import java.awt.Color;
import java.awt.Font;

import org.uqbar.vainilla.pong.Pong;
import org.uqbar.vainilla.pong.pongscene.Marcador;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class WinOrLoseComponent extends GameComponent<EndScene> {

	public WinOrLoseComponent(Marcador marcadorComputer, Marcador marcadorPlayer, double x, double y) {
		super(new Label(new Font("verdana",  Font.BOLD, 24), Color.BLUE, "", marcadorComputer.isBetter(marcadorPlayer)?"PERDISTE!":"GANASTE", marcadorPlayer.getValue()+"-"+marcadorComputer.getValue(), "Presione N para un juego nuevo"), x, y);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(DeltaState deltaState) {
		if(deltaState.isKeyPressed(Key.N)) {
			this.getGame().setCurrentScene(((Pong)this.getGame()).buildPongScene());
		}
		super.update(deltaState);
	}

}
