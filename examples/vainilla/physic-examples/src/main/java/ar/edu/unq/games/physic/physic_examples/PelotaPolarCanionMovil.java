package ar.edu.unq.games.physic.physic_examples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.events.constants.Key;

public class PelotaPolarCanionMovil<T extends GameScene> extends PelotaPolar<T>{

	//es el angulo con respecto al centro del componente
	//no tiene sentido mantener un vector porque el radio siempre es constante
	private double anguloCanion = 0;
	
	public PelotaPolarCanionMovil(int diameter) {
		super(diameter);
	}
	
	public double cannonSize() {
		return this.getDiametro();
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if(deltaState.isKeyBeingHold(Key.A)) {
			double theta = anguloCanion + this.getVelocidadAngular() * deltaState.getDelta();
			anguloCanion = this.ajustarAnguloEntrePiYMenosPi(theta);
		}
		if(deltaState.isKeyBeingHold(Key.S)) {
			double theta = anguloCanion - this.getVelocidadAngular() * deltaState.getDelta();
			anguloCanion = this.ajustarAnguloEntrePiYMenosPi(theta);			
		}
	}

	
	@Override
	public void render(Graphics2D graphics) {
		Color c = graphics.getColor();
		graphics.setColor(Color.green);
		Stroke stroke = graphics.getStroke();
		Stroke stroke2 = new BasicStroke(getDiametro()/10); 
	    graphics.setStroke(stroke2);
		Vector2D centro = this.getCentro();
		Vector2D puntaDelCanion = puntaCanion(); 
		graphics.drawLine((int)centro.getX(), (int)centro.getY(), (int)puntaDelCanion.getX(), (int)puntaDelCanion.getY());	
		graphics.setColor(c);
		graphics.setStroke(stroke);
		super.render(graphics);
	}

	private Vector2D puntaCanion() {
		Vector2D polarCanionPosition = new Vector2D(this.getDiametro(), this.getVelocidadPolar().getY() + this.anguloCanion); 
		Vector2D cartesianCanionPosition = polarCanionPosition.toCartesians();
		return cartesianCanionPosition.suma(this.getCentro());
	}
	
	@Override
	protected Vector2D getOrigenDisparo() {
		return puntaCanion();
	}
	
	@Override
	protected Vector2D getVelocidadBala() {
		return this.getVelocidadPolar().suma(new Vector2D(this.getRapidezDisparo(), this.anguloCanion))
				.toCartesians();
	}
	
	public String getHelp() {
		return "Igual que el ejemplo anterior pero con \nel cañon independiente"
				+ "use las teclas A y W para girar el cañon sin afectar\n la direccion"
				+ "de la nave";
	}
	public double getAnguloCanion() {
		return anguloCanion;
	}
	
	
}
