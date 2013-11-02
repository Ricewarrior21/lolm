package lolm;

import org.newdawn.slick.geom.Vector2f;

public enum TowerType {
	BLUE_OUTER_TOP(310f,60f);
	Vector2f position;
	private TowerType(float x, float y) {
		position = new Vector2f(x,y);
	}
	public Vector2f getPosition() {
		return position;
	}
}
