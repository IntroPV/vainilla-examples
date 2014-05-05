package ar.edu.unq.games.physic.physic_examples;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.events.constants.Key;

public class Menu extends GameComponent {

	private Physic physic;
	public Menu(Physic physic) {
		this.physic = physic;
	}
	@Override
	public void update(DeltaState deltaState) {
		for(int i = 1; i <= physic.levelCount(); i++) {
			if(deltaState.isKeyPressed(Key.fromKeyCode(Key.ZERO.getKeyCode() + i))) {
				physic.setLevel(i);
			}
		}
	}
}
