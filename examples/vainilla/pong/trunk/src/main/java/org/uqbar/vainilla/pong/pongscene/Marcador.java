package org.uqbar.vainilla.pong.pongscene;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;

public class Marcador extends GameComponent<PongScene> {

	private int value;
	
	public Marcador(double x, double y, Color color) {		
		super(new Label(new Font("verdana",  Font.BOLD, 36), color, "0"), x, y);
		this.value = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void gol() {
		this.setValue(this.getValue() + 1);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		((Label)this.getAppearance()).setText(Integer.toString(this.getValue())); 
		super.update(deltaState);
	}
	
	public boolean isBetter(Marcador other) {
		return this.value > other.value;
	}
	

}
