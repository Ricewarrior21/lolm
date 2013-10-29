package lolm;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

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
	
	DynamicEntity de; // test dynamic entity
	PathingEntity pe; // Test entity
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
		
		// Test pathing with dynamic entity
		// Path p = pf.findPath(null, 0, 0, 10, 10);
		de = new DynamicEntity(new Circle(0,0,5f), 0, 0);
		de.setPosition(0f, 0f);
		de.setPath(pc.createPath(de, new Vector2f(600,151)));
		de.setRange(775f);
		
		// Test smooth movement
		pe = new PathingEntity(new Circle(0,0,2f), pf, map, 0, 0);
		pe.setPosition(851f, 124f);
		pe.setTarget(851f,124f);
		pe.setColor(Color.blue);
		pe.setRange(100f);
	}
	
	public void update() {
		timer.update();
		im.update(this);
		cam.update(timer.getTick());
		de.goPath(timer);
		pe.goPath(timer);
	}
	
	public void render() {
		r.drawEntity(map.getMapEntity());
		r.render();
		r.drawEntity(de);
		de.drawRange(r.getGraphics());
		r.drawEntity(pe);
		pe.drawRange(r.getGraphics());
		r.printDebug(timer);
	}
	
	public void setTarget(float x, float y) {
		pe.setTarget(x,y);
	}
	
	public void setPath(float x, float y) {
		//pe.setPath(x, y);
		de.setPath(pc.createPath(de, new Vector2f((int)x, (int)y)));
	}
	
	// Getters
	public Timer getTimer() {
		return timer;
	}
	
	public Camera getCamera() {
		return cam;
	}
	
	public DynamicEntity getEntity() {
		return de;
	}
	
	public Map getMap() {
		return map;
	}
}
