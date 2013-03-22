package com.uqbar.bolitas;

import java.awt.Color;
import java.awt.Dimension;
import com.uqbar.bolitas.components.Bolita;
import com.uqbar.bolitas.scenes.Level;
import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class Bolitas extends Game {

	// ****************************************************************
	// ** STATICS
	// ****************************************************************

	public static void main(String[] args) throws Exception {
		new DesktopGameLauncher(new Bolitas()).launch();
	}

	@Override
	protected void initializeResources() {
	}

	@Override
	protected void setUpScenes() {
		Level scene = new Level();

		for(int i = 0; i < 1000; i++) {
			scene.addBolita(new Bolita(Color.RED, Color.BLUE));
		}
		scene.addBolita(new Bolita(Color.YELLOW, Color.YELLOW));

		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(500, 500);
	}

	@Override
	public String getTitle() {
		return "Demo Bolitas";
	}
}