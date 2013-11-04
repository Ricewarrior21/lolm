package lolm;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;

public class EntityList {
	// for display map background
	// List of map shapes for collision detection
	ArrayList<Entity> mEntities;
	Map map;
	public EntityList() {
		mEntities = new ArrayList<Entity>();
		mEntities.add(new Entity(new float[]{506,112,488,130,534,174,574,168,580,146,570,110,551,105,543,117}));
		mEntities.add(new Entity(new Circle(0,0,5), 500f, 500f));
		mEntities.get(1).setColor(Color.green);
	}
	
	public void addEntity(Entity e) {
		mEntities.add(e);
	}
	
	// Getters
	public ArrayList<Entity> getMapEntities() {
		return mEntities;
	}
}
