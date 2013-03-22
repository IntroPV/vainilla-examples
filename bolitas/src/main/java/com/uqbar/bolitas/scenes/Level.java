package com.uqbar.bolitas.scenes;

import java.util.ArrayList;
import java.util.Collection;
import com.uqbar.bolitas.components.Bolita;
import com.uqbar.vainilla.GameScene;

public class Level extends GameScene {

	private Collection<Bolita> bolitas = new ArrayList<Bolita>();

	public Collection<Bolita> getBolitas() {
		return this.bolitas;
	}

	public void addBolita(Bolita b) {
		this.bolitas.add(b);
		this.addComponent(b);
	}
}
