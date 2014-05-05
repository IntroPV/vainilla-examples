package ar.edu.unq.games.physic.physic_examples;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;

public class LoggerComponent<T extends GameScene> extends GameComponent<T> {

	private Object bean;
	private Collection<Method> getters = new ArrayList<Method>();
	private Collection<String> exceptions;

	
	public LoggerComponent(Object bean) {
		this(bean, new ArrayList<String>());
	}
	
	public LoggerComponent(Object bean, Collection<String> exceptions) {
		this(bean, 0,0, exceptions);
		
	}
	
	public LoggerComponent(Object bean, double x,
			double y, Collection<String> exceptions) {
		this(bean, new Font("verdana", Font.PLAIN, 24), Color.BLACK, x, y, exceptions);
	}
	
	public LoggerComponent(Object bean, Font font, Color color, double x,
			double y, Collection<String> exceptions) {
		this.exceptions = exceptions;
		this.setZ(-1);
		this.bean = bean;
		this.setX(x);
		this.setY(y);
		init();
		this.setAppearance(new Label(font, color, getText()));
	}

	private void init() {
		for (Method m : this.bean.getClass().getMethods()) {
			if (isGetter(m) && !exceptions.contains(m.getName())) {
				getters.add(m);
			}
		}
	}

	private boolean isGetter(Method m) {
		return m.getName().startsWith("get") && !m.getName().equals("getClass")
				&& m.getParameterTypes().length == 0
				&& !m.getReturnType().equals(Void.TYPE)
				&& ((m.getModifiers() & Method.PUBLIC) == Method.PUBLIC);
	}

	
	private String getText() {
		String st = "";
		for(Method getter : this.getters) {
			st += getText(getter) + "\n";
		}
		return st;
	}

	private String getText(Method getter) {
		return getter.getName().substring(3) + ": " + getValue(getter);
	}

	private Object getValue(Method getter) {
		try {
			return getter.invoke(this.bean, null);
		}
		catch (Exception e) {
			//TODO decido loguear por consola y mostrar por pantalla
			e.printStackTrace();
			return e.getMessage() != null && e.getMessage().equals("") ? e.getMessage() : e.getClass().getName();
		}
	}
	
	@Override
	public void update(DeltaState deltaState) {
		((Label)this.getAppearance()).setText(getText());
	}

}
