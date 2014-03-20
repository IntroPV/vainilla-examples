package org.uqbar.vainilla.pong.pongscene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.vainilla.pong.pongscene.pelotarules.ChoqueADerechaRule;
import org.uqbar.vainilla.pong.pongscene.pelotarules.ChoqueAIzquierdaRule;
import org.uqbar.vainilla.pong.pongscene.pelotarules.ColisionRule;
import org.uqbar.vainilla.pong.pongscene.pelotarules.DesplazamientoLibreRule;
import org.uqbar.vainilla.pong.pongscene.pelotarules.GolComputerRule;
import org.uqbar.vainilla.pong.pongscene.pelotarules.GolPlayerRule;
import org.uqbar.vainilla.utils.Vector2D;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Pelota extends GameComponent<PongScene> {

	private Vector2D direccion;
	private double velocidad;
	private double xInicial;
	private double yInicial;
	private Vector2D direccionInicial;
	private double velocidadInicial;
	private double velocidadStep = 0.1;
	private List<PelotaRule> rules = new ArrayList<PelotaRule>();
	
	public Pelota(int radio, double xInicial, double yInicial, Vector2D direccionInicial, double velocidadInicial) {
		super(new Circle(Color.RED, radio), xInicial, xInicial);
		this.xInicial = xInicial;
		this.yInicial = yInicial;
		this.direccion = direccionInicial.asVersor();
		this.direccionInicial = this.direccion;
		this.velocidad = velocidadInicial;
		this.velocidadInicial = velocidadInicial;
	}
	
	private void initRules() {
		this.rules.add(new ColisionRule(this.getScene().getRaquetaComputer()));	
		this.rules.add(new ColisionRule(this.getScene().getRaquetaPlayer()));
		this.rules.add(new GolPlayerRule(this.getScene()));
		this.rules.add(new GolComputerRule(this.getScene()));
		this.rules.add(new ChoqueADerechaRule());
		this.rules.add(new ChoqueAIzquierdaRule());
		this.rules.add(new DesplazamientoLibreRule());
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D nuevaPosicion = this.direccion.producto(velocidad*deltaState.getDelta()).suma(new Vector2D(this.getX(), this.getY()));
		//TODO convertir en reglas
		
		for(PelotaRule rule : this.getRules()) {
			if(rule.mustApply(this, nuevaPosicion, this.getScene())) {
				rule.apply(this, nuevaPosicion, this.getScene());
				break;
			}
		}
		super.update(deltaState);
	}


	private List<PelotaRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}

	public void centrar() {
		this.setX(this.xInicial);
		this.setY(this.yInicial);
		this.direccion = this.direccionInicial;
		this.velocidad = this.velocidadInicial;
	}


	public void setDireccion(Vector2D vector2d) {
		this.direccion = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direccion;
	}

	public void masRapido() {
		this.velocidad = this.velocidad + this.getVelocidadStep();
	}

	public double getVelocidadStep() {
		return velocidadStep;
	}

	public void setVelocidadStep(double velocidadStep) {
		this.velocidadStep = velocidadStep;
	}
	
}
