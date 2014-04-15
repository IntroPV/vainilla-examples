package org.uqbar.vainilla;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import com.uqbar.vainilla.sound.SoundPlayer;

public class PatitoScene extends GameScene {

	private Sprite image;
	private double velocity;
	private int gap = 1;
	private int miraWidth = 3;
	private Dimension gameDimension;
	GameComponent<PatitoScene> patito;
	private boolean playState = true;
	private GameComponent<GameScene> backGround;
	private Sound shootSound;

	public PatitoScene(String imagePath, int velocity, Dimension gameDimension,
			int gap, String soundFile) {
		super();
		this.image = Sprite.fromImage(imagePath);
		this.gap = gap;
		this.velocity = velocity;
		this.gameDimension = gameDimension;

		this.buildBackground(Color.GRAY);
		patito = new GameComponent<PatitoScene>(

		image, 0, this.getPatitoY()) {
			@Override
			public void update(DeltaState deltaState) {

				if (this.getScene().getPlayState()) {
					if (deltaState.isKeyPressed(Key.SPACE) || llegoAlFin()) {
						this.getScene().shoot();
					} else {
						mover(deltaState);
					}
				} else if (deltaState.isKeyPressed(Key.SPACE)) {
					this.getScene().newGame();
				}
			}

			private void mover(DeltaState deltaState) {
				this.setX(this.getX() + this.getScene().getVelocity()
						* deltaState.getDelta());
			}

			private boolean llegoAlFin() {
				return this.getX() + this.getScene().getPatitoWidth() >= this
						.getGame().getDisplayWidth();
			}

		};
		this.addComponent(patito);
		this.addMira();
		this.initSound(soundFile);
	}

	protected void initSound(String soundFile) {
		this.shootSound= new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream(soundFile));
	}

	protected boolean getPlayState() {
		return this.playState;
	}

	protected double getVelocity() {
		return this.velocity;
	}

	protected void shoot() {
		this.buildBackground(this.win() ? Color.GREEN : Color.RED);
		this.shootSound.play();
		this.playState = false;
	}

	private boolean win() {
		return this.patito.getX() >= this.getMiraStart()
				&& this.patito.getX() + this.getPatitoWidth() <= this
						.getMiraEnd();
	}

	private void newGame() {
		this.buildBackground(Color.gray);
		this.patito.setX(0);
		this.playState = true;
	}

	private void buildBackground(Color color) {
		if (backGround != null) {
			this.removeComponent(this.backGround);
		}
		this.backGround = new GameComponent<GameScene>(new Rectangle(color,
				gameDimension.width, gameDimension.height), 0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);

	}

	private void addMira() {
		this.addMira(getMiraStart() - miraWidth, this.getPatitoY());
		this.addMira(getMiraEnd(), this.getPatitoY());
	}

	private int getMiraEnd() {
		return ((int) this.gameDimension.getWidth() + this.getPatitoWidth())
				/ 2 + gap;
	}

	private int getMiraStart() {
		return ((int) this.gameDimension.getWidth() - this.getPatitoWidth())
				/ 2 - gap;
	}

	private int getPatitoY() {
		return ((int) gameDimension.getHeight() - this.getPatitoHeight()) / 2;
	}

	private void addMira(int x, double y) {
		this.addComponent(new GameComponent(new Rectangle(Color.black,
				miraWidth, this.getPatitoHeight()), x, y));
	}

	public int getPatitoHeight() {
		return (int) this.getImage().getHeight();
	}

	private Sprite getImage() {
		return this.image;
	}

	public int getPatitoWidth() {
		return (int) this.getImage().getWidth();
	}
	
	

}
