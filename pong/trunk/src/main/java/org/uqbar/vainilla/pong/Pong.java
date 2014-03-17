package org.uqbar.vainilla.pong;

import java.awt.Color;
import java.awt.Dimension;

import org.uqbar.vainilla.pong.endscene.EndScene;
import org.uqbar.vainilla.pong.pongscene.Marcador;
import org.uqbar.vainilla.pong.pongscene.Pelota;
import org.uqbar.vainilla.pong.pongscene.PongScene;
import org.uqbar.vainilla.pong.pongscene.Raqueta;
import org.uqbar.vainilla.pong.pongscene.strategies.RaquetaComputerStrategy;
import org.uqbar.vainilla.pong.pongscene.strategies.RaquetaPlayerStrategy;

import ar.edu.unq.games.vainillautils.Tuning;
import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class Pong extends Game {

	private Dimension dimension;
	private Color colorPlayer;
	private Color colorComputer;
	private double velocidadRaquetaPlayer;
	private double velocidadRaquetaComputer;
			
	
	@Override
	protected void initializeResources() {
		Tuning.load();
		dimension = new Dimension(Tuning.getInteger("dimension.width", 800), Tuning.getInteger("dimension.height", 600));
		colorPlayer = Tuning.getColor("player.color", Color.BLUE);
		colorComputer = Tuning.getColor("computer.color", Color.BLACK);
		velocidadRaquetaPlayer = Tuning.getDouble("player.speed", 0.8);
		velocidadRaquetaComputer = Tuning.getDouble("computer.speed", 0.4);
		
	}

	@Override
	protected void setUpScenes() {
		GameScene pongScene = buildPongScene();
		
		this.setCurrentScene(pongScene);
	}

	public GameScene buildPongScene() {
		PongScene pongScene = new PongScene();
		pongScene.setPelota(new Pelota((int)dimension.getWidth()/Tuning.getInteger("pelota.radio"), dimension.getWidth()/2 , dimension.getHeight()/2, new Vector2D(0, 1), 0.5));
		pongScene.setMarcadorPlayer(new Marcador(dimension.getWidth()/4, dimension.getHeight()/2, colorPlayer));
		pongScene.setMarcadorComputer(new Marcador(3*dimension.getWidth()/4, dimension.getHeight()/2, colorComputer));
		
		int raquetaAncho = (int)dimension.getWidth()/8;
		int raquetaAlto = 3;
		double raquetaX= dimension.getWidth()/2 - raquetaAncho/2;

		pongScene.setRaquetaComputer(new Raqueta(raquetaX, dimension.getHeight()/10, raquetaAncho, raquetaAlto, colorComputer, velocidadRaquetaComputer, 0, dimension.getWidth(), new RaquetaComputerStrategy()));
		pongScene.setRaquetaPlayer(new Raqueta(raquetaX, 9 * dimension.getHeight()/10, raquetaAncho, raquetaAlto, colorPlayer, velocidadRaquetaPlayer,0, dimension.getWidth(),new RaquetaPlayerStrategy()));
		return pongScene;
	}
	
	public GameScene buildEndScene(Marcador computer, Marcador player) {
		return new EndScene(computer, player, dimension.getWidth()/6, dimension.getHeight()/10);
	}
	

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Pong";
	}

	public static void main(String[] args) {
		new DesktopGameLauncher(new Pong()).launch();
	}
}
