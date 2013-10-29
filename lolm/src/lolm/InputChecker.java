package lolm;

import org.newdawn.slick.Input;

public class InputChecker {
	Input input;
	public InputChecker(Input input) {
		this.input = input;
	}
	
	public void update(Game game) {
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			game.setTarget(input.getMouseX(), input.getMouseY());
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			game.setPath(input.getMouseX()-game.getCamera().getCameraX(), input.getMouseY()-game.getCamera().getCameraY());
			
		}
		if (input.isKeyPressed(Input.KEY_W)) {
			game.getMap().setScale(0.9f);
			game.getMap().printScale();
		} else if (input.isKeyPressed(Input.KEY_S)) {
			game.getMap().resetScale();
			game.getMap().printScale();
		} else if (input.isKeyPressed(Input.KEY_E)) {
			game.getTimer().increaseSpeed();
		} else if (input.isKeyPressed(Input.KEY_Q)) {
			game.getTimer().decreaseSpeed();
		}
	}
	
	// Getters
	public Input getInput() {
		return input;
	}
	
	public int getMouseX() {
		return input.getMouseX();
	}
	
	public int getMouseY() {
		return input.getMouseY();
	}
	
}
