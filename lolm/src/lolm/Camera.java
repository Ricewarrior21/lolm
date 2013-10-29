package lolm;

import org.newdawn.slick.Input;

public class Camera {
	float cam_x, cam_y;
	float target_x, target_y;
	float screenX, screenY;
	float speed_x, speed_y;
	float speed, max_speed, acceleration;
	Input input;
	public Camera(Input input) {
		this.input = input;
		cam_x = 0f; cam_y = 0f; 
		target_x = 0f; target_y = 0;
		speed = 1f; max_speed = 10f;
		acceleration = 1.10f;
	}
	
	public void update(long tick) {
		cam_x = target_x;
		cam_y = target_y;
		if ((tick % 10) == 0) {
			
		}
	}
	// Getters
	public float getCameraX() {
		return cam_x;
	}
	
	public float getCameraY() {
		return cam_y;
	}
	
	public int getMouseX() {
		return input.getMouseX();
	}
	
	public int getMouseY() {
		return input.getMouseY();
	}
	
	public float getTargetX() {
		return target_x;
	}
	
	public float getTargetY() {
		return target_y;
	}

	// Setters
	public void setTarget(float x, float y) {
		target_x = cam_x - (x - (screenX / 2));
		target_y = cam_y - (y - (screenY / 2));
	}
	
	public void setScreenSize(float x, float y) {
		screenX = x;
		screenY = y;
	}
}
