package ar.edu.unq.games.physic.physic_examples;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;

public class Physic extends Game {

	private Dimension dimension;
	private List<GameScene> examples;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(800, 600);

	}

	@Override
	protected void setUpScenes() {
		examples = new ArrayList<GameScene>();
		addLevel(new Pelota1D<PhysicScene>(50));
		addLevel(new Pelota2D<PhysicScene>(50));
		addLevel(new PelotaGravity<PhysicScene>(50));
		addLevel(new PelotaPolar<PhysicScene>(50));
		addLevel(new PelotaPolarCanionMovil<PhysicScene>(50));

		this.setLevel(1);
	}

	public void setLevel(int i) {
		if (i >= 1 && i <= examples.size()) {
			this.setCurrentScene(examples.get(i - 1));
		}
	}

	private void addLevel(GameComponent<PhysicScene> component) {
		addLevel(new PhysicScene(component));
	}

	private void addLevel(GameScene level) {
		this.examples.add(level);
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Physic";
	}

	public int levelCount() {
		return this.examples.size();
	}

}
