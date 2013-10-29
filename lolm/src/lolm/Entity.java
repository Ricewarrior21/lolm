package lolm;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	final float rlf = 0.05f; // LoL range constant
	Shape shape;
	Vector2f position;
	Color color;
	Image image;
	
	float range;
	
	public Entity() {
		shape = new Rectangle(0,0,0,0);
		position = new Vector2f(0,0);
		setPosition(0,0);
		color = Color.white;
		image = null;
		range = 0f;
	}
	
	public Entity(Shape shape, float x, float y) {
		this.shape = shape;
		position = new Vector2f(x,y);
		color = Color.white;
		image = null;
		range = 0f;
	}
	
	public Entity(float[] points) {
		shape = new Polygon(points);
		position = new Vector2f(0,0);
		color = Color.white;
		image = null;
		range = 0f;
	}
	
	public void drawImage() {
		if (image != null) {
			image.draw(position.getX(),position.getY());
		}
	}
	
	public void drawRange(Graphics g) {
		g.setColor(Color.green);
		g.draw(new Circle(shape.getCenterX(),shape.getCenterY(),range*rlf));
	}
	
	// Setters
	public void setPosition(float x, float y) {
		position.set(x,y);
		shape.setCenterX(position.getX()); shape.setCenterY(position.getY());
	}
	
	public void setPosition(Vector2f position) {
		this.position.set(position);
		shape.setCenterX(position.getX()); shape.setCenterY(position.getY());
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setImage(String name) throws SlickException {
		image = new Image(name);
	}
	
	public void setImageSize(int x, int y) {
		if (image != null) {
			image = image.getScaledCopy(x, y);
		}
	}
	
	public void setRange(float range) {
		this.range = range;
	}
	
	// Getters
	public Shape getShape() {
		return shape;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Color getColor() {
		return color;
	}
	public Vector2f getPosition() {
		return position;
	}
}
