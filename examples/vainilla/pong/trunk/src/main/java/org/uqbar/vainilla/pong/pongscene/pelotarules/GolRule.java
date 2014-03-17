package org.uqbar.vainilla.pong.pongscene.pelotarules;

import org.uqbar.vainilla.pong.pongscene.Marcador;
import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PelotaRule;
import org.uqbar.vainilla.pong.pongscene.PongScene;

import ar.edu.unq.games.vainillautils.Vector2D;

public abstract class GolRule implements PelotaRule {

	private Marcador marcador;
	
	public GolRule(Marcador marcador) {
		super();
		this.marcador = marcador;
	}

	@Override
	public boolean mustApply(Pelota pelota, Vector2D nuevaPosicion,
			PongScene scene) {
		return this.isGol(pelota, nuevaPosicion);
	}

	protected abstract boolean isGol(Pelota pelota, Vector2D nuevaPosicion);

	@Override
	public void apply(Pelota pelota, Vector2D nuevaPosicion, PongScene scene) {
		pelota.centrar();
		scene.getRaquetaComputer().centrar();
		scene.getRaquetaPlayer().centrar();
		this.changeMarcador();
		this.revisarFinJuego(scene);
	}

	protected void revisarFinJuego(PongScene scene) {
		if(marcador.finJuego(scene)) {
			scene.fin();
		}
	}

	protected void changeMarcador() {
		this.marcador.gol();
	}


}
