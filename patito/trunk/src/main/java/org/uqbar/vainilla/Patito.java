package org.uqbar.vainilla;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;

public class Patito extends Game {

	private Dimension dimension;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(600, 300);

	}

	@Override
	protected void setUpScenes() {
		GameScene scene = new PatitoScene(50, (dimension.getHeight()-50)/2, dimension);
		
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
