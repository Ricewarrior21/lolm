package lolm;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;

public class InputChecker {
	Input input;
	public InputChecker(Input input) {
		this.input = input;
	}
	
	public void update(Game game) {
		int dWheel = Mouse.getDWheel();
		if (dWheel < 0) { // Mouse wheel scrolling down
			game.getMap().setScale(0.95f);
		} else if (dWheel > 0) { // Mouse wheel scrolling up
			game.getMap().setScale(1.05f);		
		}
		
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			game.setTarget(input.getMouseX(), input.getMouseY());
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			float scale = (game.getMap().getOldScale());
			float mx = (input.getMouseX()-game.getCamera().getCameraX())*scale;
			float my = (input.getMouseY()-game.getCamera().getCameraY())*scale;
			game.setPath(mx,my);
			
		}
		if (input.isKeyPressed(Input.KEY_R)) {
			game.getMap().resetScale();
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
