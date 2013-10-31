package lolm;

import java.io.FileWriter;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Map implements TileBasedMap{
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	private int[][] MAP = new int[100][100];
	float scale, oldScale;
	Vector2f mapSize;
	Entity map_bg;
	public Map() {
		mapSize = new Vector2f(1000f,1000f);
		map_bg = new Entity();
		try {
			map_bg.setImage("SummonersRift.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		map_bg.setImageSize((int)mapSize.getX(), (int)mapSize.getY());
		scale = 1f;
		oldScale = 1f;
	}
	@Override
	public boolean blocked(PathFindingContext context, int x, int y) {
		return MAP[x][y] != 0;
	}
	public boolean blocked(int x, int y) {
		return MAP[x][y] != 0;
	}
	@Override
	public float getCost(PathFindingContext context, int x, int y) {
		return 1.0f;
	}
	@Override
	public int getHeightInTiles() {
		return HEIGHT;
	}
	@Override
	public int getWidthInTiles() {
		return WIDTH;
	}
	@Override
	public void pathFinderVisited(int x, int y) {}
	
	private boolean checkCollision(Graphics g, Shape s, ArrayList<Entity> mEntities, float x, float y) {
		boolean collide = false;
		s.setCenterX(x);
		s.setCenterY(y);
		for(int i = 0; i < mEntities.size(); i++) {
			if(s.intersects(mEntities.get(i).getShape()) || mEntities.get(i).getShape().contains(s)) {
				collide = true;
			}
		}
		return collide; 
	}
	
	public void loadMap(Graphics g, ArrayList<Entity> mEntities) {
		Shape s = new Circle(0,0,2f);
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				if ((checkCollision(g, s, mEntities, (x*10)-5, (y*10)-5)) == true) {
					MAP[x][y] = 1;
				} else {
					MAP[x][y] = 0;
				}
			}
		}
		try {
			FileWriter fw = new FileWriter("mapoutput.txt");
			for(int y = 0; y < HEIGHT; y++) {
				for(int x = 0; x < WIDTH; x++) {
					fw.write(String.valueOf(MAP[x][y]));
				}
				fw.write("\n");
			}
			fw.close();
		} catch (Exception e) {
			
		}
		
	}
	
	private void updateScale(float scale) {
		mapSize = mapSize.scale(scale);
		map_bg.setImageSize((int)mapSize.getX(), (int)mapSize.getY());
	}
	
	public void resetScale() {
		updateScale(oldScale);
		scale = 1f;
		oldScale = 1f;
	}
	
	// Setters
	public void setScale(float scale) {
		oldScale = (1 / scale) * oldScale;
		this.scale = scale;
		updateScale(scale);
	}
	
	public void printScale() {
		System.out.println("scale: " + scale + " oldScale: " + oldScale);
	}
	
	// Getters
	
	public Entity getMapEntity() {
		return map_bg;
	}
	
	public Vector2f getMapSize() {
		return mapSize;
	}
	
	public float getScale() {
		return scale;
	}
	
	public float getOldScale() {
		return oldScale;
	}
}
