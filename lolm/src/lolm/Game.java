package lolm;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;

public class Game {
	// Timer object that keeps track of time relative to the game start
	Timer timer;
	// Renderer draws stuff in game, gets info from timer, InputChecker
	Renderer r;
	// InputChecker handles input, gets accesses functions through game class
	InputChecker im;
	// Allows player to move camera about map, calculated at end
	// Maybe place inside RenderManager?
	Camera cam;
	// EntityList holds all entities
	EntityList el;
	// Map that holds collision info
	Map map;
	// Pathfinder object
	AStarPathFinder pf;
	// PathCreator object
	PathCreator pc;
	
	Entity e;
	public Game(Renderer r, InputChecker im) {
		timer = new Timer();
		this.r = r;
		this.im = im;
		cam = new Camera(im.getInput());
		el = new EntityList();
		r.setCamera(cam);
		r.setEntityList(el);
		
		map = new Map();
		map.loadMap(r.getGraphics(), el.getMapEntities());
		pf = new AStarPathFinder(map, 200, false);
		cam.setTarget(0f, 0f);
		cam.setScreenSize(1024f, 768f);
		
		pc = new PathCreator(pf,map);
		
		e = new Entity(new Circle(0,0,5), 250f, 250f);
		e.setColor(Color.darkGray);
		e.setScale(map.getScale());
	}
	
	public void update() {
		timer.update();
		im.update(this);
		cam.update(timer.getTick());
		if (e.getScale() != (1/map.getOldScale())) {
			e.setScale(1/map.getOldScale());
		}
	}
	
	public void render() {
		r.drawEntity(map.getMapEntity());
		r.drawEntity(e);
		r.render();
		r.printDebug(timer, e);
	}
	
	public void setTarget(float x, float y) {
		
	}
	
	public void setPath(float x, float y) {

	}
	
	// Getters
	public Timer getTimer() {
		return timer;
	}
	
	public Camera getCamera() {
		return cam;
	}
	
	public Map getMap() {
		return map;
	}
}
