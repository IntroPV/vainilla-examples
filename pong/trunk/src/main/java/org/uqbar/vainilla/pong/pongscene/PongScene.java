package org.uqbar.vainilla.pong.pongscene;

import org.uqbar.vainilla.pong.Pong;

import com.uqbar.vainilla.GameScene;

public class PongScene extends GameScene {


	private Pelota pelota;
	private Marcador marcadorComputer;
	private Marcador marcadorPlayer;
	private Raqueta raquetaComputer;
	private Raqueta raquetaPlayer;
	private int maxScore = 3;

	public Marcador getMarcadorComputer() {
		return marcadorComputer;
	}

	public Marcador getMarcadorPlayer() {
		return marcadorPlayer;
	}

	public Raqueta getRaquetaComputer() {
		return raquetaComputer;
	}

	public Raqueta getRaquetaPlayer() {
		return raquetaPlayer;
	}


	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.addComponent(pelota);
		this.pelota = pelota;
	}

	public void setMarcadorPlayer(Marcador marcadorPlayer) {
		this.addComponent(marcadorPlayer);
		this.marcadorPlayer = marcadorPlayer;
	}

	public void setMarcadorComputer(Marcador marcadorComputer) {
		this.addComponent(marcadorComputer);
		this.marcadorComputer = marcadorComputer;
	}

	public void setRaquetaComputer(Raqueta raqueta) {
		this.raquetaComputer = raqueta;
		this.addComponent(raqueta);
	}

	public void setRaquetaPlayer(Raqueta raqueta) {
		this.raquetaPlayer = raqueta;
		this.addComponent(raqueta);
	}


	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public void fin() {
		this.getGame().setCurrentScene(((Pong)this.getGame()).buildEndScene(this.marcadorComputer, this.marcadorPlayer));		
	}

}
