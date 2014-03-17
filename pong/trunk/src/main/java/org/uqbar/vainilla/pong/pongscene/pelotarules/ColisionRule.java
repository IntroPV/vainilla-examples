package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PelotaRule;
import org.uqbar.vainilla.pong.pongscene.PongScene;
import org.uqbar.vainilla.pong.pongscene.Raqueta;

import com.uqbar.vainilla.colissions.CollisionDetector;

import ar.edu.unq.games.vainillautils.Vector2D;

public class ColisionRule implements PelotaRule {

	private static double anguloDelta = 0.1;
	private static double anguloMayor = -Math.PI/2 + anguloDelta;
	private static double anguloMenor = Math.PI/2 - anguloDelta;
	private Raqueta raqueta;

	public ColisionRule(Raqueta raqueta) {
		super();
		this.raqueta = raqueta;
	}

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return this.colisiona(raqueta, pelota, nuevaPosicion) ;
	}
	
	private boolean colisiona(Raqueta raqueta, Pelota pelota,
			Vector2D nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nuevaPosicion.getX(), nuevaPosicion.getY(), pelota
						.getAppearance().getWidth() / 2, raqueta.getX(),
				raqueta.getY(), raqueta.getAppearance().getWidth(), raqueta
						.getAppearance().getHeight());
	}

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
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



}
