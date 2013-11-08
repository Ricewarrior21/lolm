package lolm;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

public class PathingEntity extends Entity {
	Vector2f target;
	Vector2f velocity;
	float distance;
	float speed;
	
	Vector2f finalTarget;

	AStarPathFinder pf;
	Map map;
	Path p; // Path for entity to move along
	int pathCount; // Track how far along path we are
	boolean pathing; // Whether or not entity is moving
	
	public PathingEntity(Shape shape, float x, float y) {
		super(shape,x,y);
		target = new Vector2f(0,0);
		velocity = new Vector2f(0,0);
		finalTarget = new Vector2f(0,0);
		distance = position.distance(target);
		speed = 325f;
		pathCount = 0;
		pathing = false;
	}
	
	public PathingEntity(Shape shape, AStarPathFinder pf, Map map, float x, float y) {
		super(shape,x,y);
		this.pf = pf;
		this.map = map;
		target = new Vector2f(0,0);
		velocity = new Vector2f(0,0);
		finalTarget = new Vector2f(0,0);
		distance = position.distance(target);
		speed = 325f;
		pathCount = 0;
		pathing = false;
	}
	
	void update(Timer timer) {
		distance = position.distance(target);
		float lf = 0.065f; // league of legends speed factor
		float x = (target.getX()-position.getX());
		float y = (target.getY()-position.getY());
		float vx = speed*lf * (x / distance);
		float vy = speed*lf * (y / distance);
		velocity.set(vx,vy);
		if ((timer.getTick() % (timer.getTimerSetting().getSpeed()*0.05f)) == 0) {
			Vector2f v = velocity.scale(0.05f);
			if (distance < 1f) {
				position.set(target);
				pathCount++;
			} else {
				position.set(position.getX()+v.getX(), position.getY()+v.getY());
				setPosition(position.getX(), position.getY());
			}
		}
	}

	public void goPath(Timer timer) {
		if (p != null) {
			if (pathCount == p.getLength()) {
				target.set(finalTarget);
				pathCount = 0;
				pathing = false;
			}
			if (pathCount < p.getLength() && pathing == true) {
				if ((pathCount + 2) < p.getLength()) {
					int x = p.getX(pathCount+2)-p.getX(pathCount);
					int y = p.getY(pathCount+2)-p.getY(pathCount);
					int diff = x+y;
					if ((diff == 0) && x < 0) {
						setTarget(p.getX(pathCount+1)*10, p.getY(pathCount)*10);
					} else if ((diff == 0) && x > 0) {
						setTarget(p.getX(pathCount)*10, p.getY(pathCount+1)*10);
					} else if ((diff == 2) && x > 0) {
						setTarget(p.getX(pathCount)*10, p.getY(pathCount+1)*10);
					} else if ((diff == -2) && x < 0) {
						setTarget(p.getX(pathCount+1)*10, p.getY(pathCount)*10);
					}
				} else {
					setTarget(p.getX(pathCount)*10, p.getY(pathCount)*10);
				}
			} 
		}
		update(timer);
	}
	
	public void drawPath(Graphics g) {
		g.setColor(Color.red);
		if (p != null) {
			for (int i = 0, j = 1; i < p.getLength(); i++, j++) {
				if (j < p.getLength()) {
					g.drawLine(p.getX(i)*10, p.getY(i)*10, p.getX(j)*10, p.getY(j)*10);
				}
			}
		}
	}
	
	// Setters
	public void setPath(float x, float y) {
		int sx = (int)shape.getCenterX()/10;
		int sy = (int)shape.getCenterY()/10;
		int tx = (int)x/10;
		int ty = (int)y/10;
		if ((pf != null) && (map != null)) {
			if ((tx < 100) && (ty < 100) && (tx > 0) && (ty > 0)) {
				if (!map.blocked(tx,ty)) {
					Path p = pf.findPath(null, sx, sy, tx, ty);
					this.p = p;
					finalTarget.set(x,y);
					pathing = true;
					pathCount = 0;
				}
			}
		}
	}
	
	// Setup pathfinding objects
	public void setPathFinding(AStarPathFinder pf, Map map) {
		this.pf = pf;
		this.map = map;
	}
	
	public void setTarget(float x, float y) {
		target.set(x,y);
	}
	
	public void setTarget(Vector2f target) {
		this.target.set(target);
	}
	
}
