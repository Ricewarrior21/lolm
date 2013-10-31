package lolm;

import org.newdawn.slick.geom.Circle;

public class Tower extends Entity{
	public Tower(TowerType tt) {
		shape = new Circle(0,0,5f);
		range = 775f;
		position = tt.getPosition();
	}
}
