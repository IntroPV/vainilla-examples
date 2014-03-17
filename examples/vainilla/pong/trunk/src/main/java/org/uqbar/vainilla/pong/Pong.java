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

import ar.edu.unq.games.vainillautils.Vector2D;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

public class Pong extends Game {

	static public Dimension dimension = new Dimension(800, 600);
	static Color colorPlayer = Color.BLUE;
	static Color colorComputer = Color.BLACK;
	static double velocidadRaquetaPlayer = 0.8;
	static double velocidadRaquetaComputer = 0.4;
			
	
	@Override
	protected void initializeResources() {
		
	}

	@Override
	protected void setUpScenes() {
		GameScene pongScene = buildPongScene();
		
		this.setCurrentScene(pongScene);
	}

	public GameScene buildPongScene() {
		PongScene pongScene = new PongScene();
		pongScene.setPelota(new Pelota((int)dimension.getWidth()/22, dimension.getWidth()/2 , dimension.getHeight()/2, new Vector2D(0, 1), 0.5));
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
