package org.uqbar.vainilla;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class PatitoScene extends GameScene {

	private int patitoHeigth;
	private int patitoWidth;
	private double velocity = 100;
	private int gap = 1;
	private int miraWidth = 3;
	private Dimension gameDimension;
	GameComponent<PatitoScene> patito;
	private boolean playState = true;
	private GameComponent<GameScene> backGround;

	public PatitoScene(int patitoWidth, double velocity, Dimension gameDimension) {
		super();
		this.patitoWidth = patitoWidth;
		this.patitoHeigth = patitoWidth;
		this.velocity = velocity;
		this.gameDimension = gameDimension;
		this.buildBackground(Color.GRAY);
		patito = new GameComponent<PatitoScene>(new Circle(Color.blue,
				this.patitoWidth), 0, this.getPatitoY()) {
			@Override
			public void update(DeltaState deltaState) {
				
				if(playState) {
					if(deltaState.isKeyPressed(Key.SPACE) || this.getX() + PatitoScene.this.patitoWidth >= this.getGame()
						.getDisplayWidth()) {
						this.getScene().shoot();
					}
					else {
						this.setX(this.getX() + 
								PatitoScene.this.velocity * deltaState.getDelta());
					}
				}
				else if (deltaState.isKeyPressed(Key.SPACE)){
					this.getScene().newGame();					
				}
			}

		};
		this.addComponent(patito);
		this.addMira();
	}

	protected void shoot() {
		this.buildBackground(this.win() ? Color.GREEN : Color.RED);
		this.playState = false;
	}

	private boolean win() {
		return this.patito.getX() >= this.getMiraStart() && this.patito.getX() + this.patitoWidth <= this.getMiraEnd();
	}

	private void newGame() {
		this.buildBackground(Color.gray);
		this.patito.setX(0);
		this.playState = true;
	}

	private void buildBackground(Color color) {
		if(backGround != null) {
			this.removeComponent(this.backGround);
		}
		this.backGround = new GameComponent<GameScene>(new Rectangle(color, gameDimension.width, gameDimension.height), 0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);
		
	}

	private void addMira() {
		this.addMira(getMiraStart() - miraWidth, this.getPatitoY());
		this.addMira(getMiraEnd(), this.getPatitoY());
	}

	private int getMiraEnd() {
		return ((int) this.gameDimension.getWidth() + this.patitoWidth) / 2
				+ gap;
	}

	private int getMiraStart() {
		return ((int) this.gameDimension.getWidth() - this.patitoWidth) / 2
				- gap;
	}

	private int getPatitoY() {
		return ((int) gameDimension.getHeight() - this.patitoHeigth) / 2;
	}

	private void addMira(int x, double y) {
		this.addComponent(new GameComponent(new Rectangle(Color.black,
				miraWidth, this.patitoHeigth), x, y));
	}

}
