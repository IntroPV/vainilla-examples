package org.uqbar.vainilla;

import java.awt.Dimension;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class Patito extends Game {

	private Dimension dimension;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(600, 100);

	}

	@Override
	protected void setUpScenes() {
		GameScene scene = new PatitoScene("duck.png", 125, dimension, 1, "shoot.wav");
		
		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Patito";
	}


}
