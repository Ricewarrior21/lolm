package lolm;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Renderer {
	Graphics g;
	Camera cam;
	EntityList el;
	Map map;
	public Renderer(Graphics g) {
		g.setColor(Color.white);
		this.g = g;
	}
	
	// Print debugging information
	public void printDebug(Timer timer, Entity e) {
		g.translate(-cam.getCameraX(), -cam.getCameraY());
		g.setColor(Color.yellow);
		g.drawString(String.valueOf("Time: " + timer.getTime()), 15, 35);
		g.setColor(Color.red);
		g.drawString(String.valueOf("mouseX: " + cam.getMouseX()), 15, 55);
		g.drawString(String.valueOf("mouseY: " + cam.getMouseY()), 15, 75);
		g.setColor(Color.white);
		g.drawString(String.valueOf("tick: " + timer.getTick()), 15, 95);
		g.setColor(Color.green);
		g.drawString(String.valueOf("scale: " + e.getScale()), 15, 115);
		g.drawString(String.valueOf("px: " + e.getPosition().getX()), 15, 135);
		g.drawString(String.valueOf("py: " + e.getPosition().getY()), 15, 155);
	}
	
	public void render() {
		g.translate(cam.getCameraX(), cam.getCameraY());
		drawEntityList(el.getMapEntities());
	}
	
	public void drawEntity(Entity e) {
		g.setColor(e.getColor());
		if (e.getImage() != null) {
			e.drawImage();
		}
		if (e.getShape() != null) {
			g.fill(e.getShape());
		}
	}
	
	public void drawEntity(DynamicEntity e) {
		g.setColor(e.getColor());
		g.fill(e.getShape());
	}
	
	public void drawEntityList(ArrayList<Entity> el) {
		for(int i = 0; i < el.size(); i++) {
			if (el.get(i).getScale() != (1/map.getOldScale())) {
				el.get(i).setScale(1/map.getOldScale());
			}
			drawEntity(el.get(i));
		}
	}
	// Getters
	public Graphics getGraphics() {
		return g;
	}
	
	// Setters
	public void setCamera(Camera cam) {
		this.cam = cam;
	}
	public void setEntityList(EntityList el) {
		this.el = el;
	}
	public void setMap(Map map) {
		this.map = map;
	}
}
