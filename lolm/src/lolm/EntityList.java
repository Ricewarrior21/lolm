package lolm;

import java.util.ArrayList;

public class EntityList {
	// for display map background
	// List of map shapes for collision detection
	ArrayList<Entity> mEntities;
	Map map;
	public EntityList() {
		mEntities = new ArrayList<Entity>();
		mEntities.add(new Entity(new float[]{506,112,488,130,534,174,574,168,580,146,570,110,551,105,543,117}));
	}
	
	public void addEntity(Entity e) {
		mEntities.add(e);
	}
	
	// Getters
	public ArrayList<Entity> getMapEntities() {
		return mEntities;
	}
}
