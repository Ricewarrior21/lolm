package lolm;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

public class PathCreator {
	Vector2f mapSize;
	AStarPathFinder pf;
	Map map;
	public PathCreator(AStarPathFinder pf, Map map) {
		this.pf = pf;
		this.map = map;
	}
	
	public Path createPath(Entity e, Vector2f target) {
		// Converting target into grid format
		int sx = (int)e.getPosition().getX()/10;
		int sy = (int)e.getPosition().getY()/10;
		int tx = (int)target.getX()/10;
		int ty = (int)target.getY()/10;
		Path p = new Path();
		// Create new path only if it is possible/valid
		if ((tx < 100) && (ty < 100) && (tx > 0) && (ty > 0) && !map.blocked(tx, ty)) {
			Path t = pf.findPath(null, sx, sy, tx, ty);
			p.appendStep((int)e.getPosition().getX(), (int)e.getPosition().getY());
			for(int i = 0; i < t.getLength(); i++) {
				if ((i+2) < t.getLength()) {
					int x = t.getX(i+2)-t.getX(i);
					int y = t.getX(i+2)-t.getX(i);
					int diff = x + y;
					if ((diff == 0) && x < 0) {
						p.appendStep(t.getX(i+1)*10, t.getY(i)*10);
					} else if ((diff == 0) && x > 0) {
						p.appendStep(t.getX(i)*10, t.getY(i+1)*10);
					} else if ((diff == 2) && x > 0) {
						p.appendStep(t.getX(i)*10, t.getY(i+1)*10);
					} else if ((diff == -2) && x < 0) {
						p.appendStep(t.getX(i+1)*10, t.getY(i)*10);
					}
				} else {
					p.appendStep(t.getX(i)*10, t.getY(i)*10);
				}
			}
			// Final step
			p.appendStep((int)target.getX(), (int)target.getY());
		} else {
			// Give it a path to itself if invalid path
			p.appendStep((int)e.getPosition().getX(), (int)e.getPosition().getY());
		}
		return p;
	}
	
}
