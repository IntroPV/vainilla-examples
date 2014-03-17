package org.uqbar.vainilla.pong.pongscene;

import org.uqbar.vainilla.pong.Pong;

import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class PongScene extends GameScene {

	private static double anguloDelta = 0.1;
	private static double anguloMayor = -Math.PI/2 + anguloDelta;
	private static double anguloMenor = Math.PI/2 - anguloDelta;

	private Pelota pelota;
	private Marcador marcadorComputer;
	private Marcador marcadorPlayer;
	private Raqueta raquetaComputer;
	private Raqueta raquetaPlayer;
	private int maxScore = 3;

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

	public void golComputer() {
		this.marcadorComputer.gol();
		if(marcadorComputer.getValue() >= this.getMaxScore()) {
			this.endGame();
		}
		this.centrarRaquetas();
	}

	private void centrarRaquetas() {
		this.raquetaComputer.centrar();
		this.raquetaPlayer.centrar();
	}

	public void golPlayer() {
		this.marcadorPlayer.gol();
		if(marcadorPlayer.getValue() >= this.getMaxScore()) {
			this.endGame();
		}
		this.centrarRaquetas();
	}

	private void endGame() {
		this.getGame().setCurrentScene(((Pong)this.getGame()).buildEndScene(this.marcadorComputer, this.marcadorPlayer));
	}

	public void setRaquetaComputer(Raqueta raqueta) {
		this.raquetaComputer = raqueta;
		this.addComponent(raqueta);
	}

	public void setRaquetaPlayer(Raqueta raqueta) {
		this.raquetaPlayer = raqueta;
		this.addComponent(raqueta);
	}

	/**
	 * Tiene efecto y devuelve si hubo colisión
	 * 
	 * @param pelota
	 * @param nuevaPosicion
	 *            TODO
	 * @return true si colisiona;
	 */
	public boolean resolverSiColisiona(Pelota pelota, Vector2D nuevaPosicion) {
		return this.resolverSiColisiona(raquetaPlayer, pelota, nuevaPosicion)
				|| this.resolverSiColisiona(raquetaComputer, pelota,
						nuevaPosicion);
	}

	protected boolean resolverSiColisiona(Raqueta raqueta, Pelota pelota,
			Vector2D nuevaPosicion) {

		if (colisiona(raqueta, pelota, nuevaPosicion)) {
			double puntoDeColision = getPuntoColision(raqueta, pelota,
					nuevaPosicion);

			double signoY = Math.signum(pelota.getDireccion().getY());

			double anguloNuevo = ((anguloMenor - anguloMayor) / raqueta
					.getAppearance().getWidth())
					* puntoDeColision
					+ anguloMayor;
			// aprovecho e invierto el signo que traia Y con el truquito de
			// multiplicarlo por -1
			pelota.setDireccion(new Vector2D(Math.sin(anguloNuevo), -1 * signoY
					* Math.cos(anguloNuevo)));

			// pelota.setX(nuevaPosicion.getX());
			pelota.setY(signoY > 0 ? raqueta.getY()
					- pelota.getAppearance().getHeight() - 1 : raqueta.getY()
					+ raqueta.getAppearance().getHeight() + 1);
			
			pelota.masRapido();

			return true;
		}
		;
		return false;
	}

	private double getPuntoColision(Raqueta raqueta, Pelota pelota,
			Vector2D nuevaPosicion) {
		if (pelota.getX() > raqueta.getX()
				&& pelota.getX() + pelota.getAppearance().getWidth() < raqueta
						.getX() + raqueta.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX()
					+ pelota.getAppearance().getWidth() / 2;

			return xCentroPelota - raqueta.getX();
		} else if (pelota.getX() < raqueta.getX()) {
			return 0;
		} else {
			return raqueta.getAppearance().getWidth();
		}
	}

	private boolean colisiona(Raqueta raqueta, Pelota pelota,
			Vector2D nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nuevaPosicion.getX(), nuevaPosicion.getY(), pelota
						.getAppearance().getWidth() / 2, raqueta.getX(),
				raqueta.getY(), raqueta.getAppearance().getWidth(), raqueta
						.getAppearance().getHeight());
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

}
