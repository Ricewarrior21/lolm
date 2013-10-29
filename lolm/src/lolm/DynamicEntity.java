package lolm;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Path;

public class DynamicEntity extends Entity {
	final float slf = 0.065f; // LoL move speed constant
	Vector2f target;
	Vector2f velocity;
	float distance;
	float speed;

	boolean pathing;
	
	Path p;
	int pCount;
	
	public DynamicEntity(Shape shape, float x, float y) {
		super(shape,x,y);
		target = new Vector2f(0,0);
		velocity = new Vector2f(0,0);
		pathing = false;
		distance = position.distance(target);
		speed = 325f;
		pCount = 0;
	}
	
	void update(Timer timer) {
		if (pathing) {
			distance = position.distance(target);
			float x = (target.getX()-position.getX());
			float y = (target.getY()-position.getY());
			float vx = speed*slf * (x / distance);
			float vy = speed*slf * (y / distance);
			velocity.set(vx,vy);
			if ((timer.getTick() % (timer.getTimerSetting().getSpeed()*0.05f)) == 0) {
				Vector2f v = velocity.scale(0.05f);
				if (distance < 1f) {
					position.set(target);
				} else {
					position.set(position.getX()+v.getX(), position.getY()+v.getY());
					setPosition(position);
				}
			}
		}
	}
	
	public void goPath(Timer timer) {
		update(timer);
		if (position.equals(target) && (pCount == p.getLength()-1)) {
			pathing = false;
		} else if (position.equals(target)) {
			pCount++;
			target.set(p.getX(pCount),p.getY(pCount));
		}
	}
	
	// Setters
	public void setTarget(float x, float y) {
		target.set(x,y);
		pathing = true;
	}
	
	public void setTarget(Vector2f target) {
		this.target.set(target);
		pathing = true;
	}
	
	public void setPath(Path p) {
		pCount = 0;
		this.p = p;
		target.set(p.getX(pCount),p.getY(pCount));
		pathing = true;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
