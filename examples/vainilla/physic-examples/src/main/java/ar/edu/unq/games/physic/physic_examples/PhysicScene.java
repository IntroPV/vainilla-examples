package ar.edu.unq.games.physic.physic_examples;

import java.util.ArrayList;
import java.util.Collection;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class PhysicScene extends GameScene {
	
	private GameComponent<PhysicScene> component;
	private LoggerComponent<PhysicScene> logger;
	private Menu menu;
	
	public PhysicScene(GameComponent<PhysicScene> pelota) {
		this.component = pelota;
	}

	@Override
	public void setGame(Game game) {
		super.setGame(game);
		if (game != null) {
			component.setY((double) game.getDisplayHeight() / 2
					- component.getAppearance().getHeight() / 2);
			component.setX((double) game.getDisplayWidth() / 2
					- component.getAppearance().getWidth() / 2);
			Collection<String> exp = new ArrayList<String>();
			exp.add("getScene");
			exp.add("getGame");
			exp.add("getDiameter");
			exp.add("getAppearance");
			exp.add("getZ");
			
			logger = new LoggerComponent<PhysicScene>(component, exp);
			menu = new Menu((Physic) this.getGame());
			this.addComponent(component);
			this.addComponent(menu);
			this.addComponent(logger);
		}
		else {
			this.removeComponent(component);
			this.removeComponent(logger);
			this.removeComponent(menu);
		}
	}
}
