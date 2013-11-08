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
		ArrayList<String> dStrings = new ArrayList<String>();
		dStrings.add("Time: " + timer.getTime());
		dStrings.add("mouseX: " + (cam.getMouseX()-cam.getCameraX()));
		dStrings.add("mouseY: " + (cam.getMouseY()-cam.getCameraY()));
		dStrings.add("scaledMouseX: " + (cam.getMouseX()-cam.getCameraX())*(map.getOldScale()));
		dStrings.add("scaledMouseY: " + (cam.getMouseY()-cam.getCameraY())*(map.getOldScale()));
		dStrings.add("tick: " + timer.getTick());
		dStrings.add("scale: " + e.getScale());
		dStrings.add("px: " + e.getPosition().getX());
		dStrings.add("py: " + e.getPosition().getY());
		g.translate(-cam.getCameraX(), -cam.getCameraY());
		for (int i = 0; i < dStrings.size(); i++) {
			g.drawString(String.valueOf(dStrings.get(i)), 10, 30 + i*16);
		}
		
		/*
		g.translate(-cam.getCameraX(), -cam.getCameraY());
		g.setColor(Color.yellow);
		g.drawString(String.valueOf("Time: " + timer.getTime()), 15, 35);
		g.setColor(Color.red);
		g.drawString(String.valueOf("mouseX: " + (cam.getMouseX()-cam.getCameraX())), 15, 55);
		g.drawString(String.valueOf("mouseY: " + (cam.getMouseY()-cam.getCameraY())), 15, 75);
		g.setColor(Color.orange);
		g.drawString(String.valueOf("scaledMouseX: " + (cam.getMouseX()-cam.getCameraX())*(map.getOldScale())), 15, 95);
		g.drawString(String.valueOf("scaledMouseY: " + (cam.getMouseY()-cam.getCameraY())*(map.getOldScale())), 15, 115);
		g.setColor(Color.white);
		g.drawString(String.valueOf("tick: " + timer.getTick()), 15, 135);
		g.setColor(Color.green);
		g.drawString(String.valueOf("scale: " + e.getScale()), 15, 155);
		g.drawString(String.valueOf("px: " + e.getPosition().getX()), 15, 175);
		g.drawString(String.valueOf("py: " + e.getPosition().getY()), 15, 195); */
	}
	
	public void render() {
		g.translate(cam.getCameraX(), cam.getCameraY());
		drawEntity(map.getMapEntity());
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
