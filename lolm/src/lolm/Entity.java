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
	
	Vector2f scaledPosition;
	Shape scaledShape;
	float scale;
	float range;
	
	public Entity() {
		shape = new Rectangle(0,0,0,0);
		scaledShape = shape;
		position = new Vector2f(0,0);
		scaledPosition = new Vector2f(0,0);
		scale = 1f;
		setPosition(0,0);
		color = Color.white;
		image = null;
		range = 0f;
	}
	
	public Entity(Shape shape, float x, float y) {
		this.shape = shape;
		scaledShape = shape;
		position = new Vector2f(x,y);
		scaledPosition = new Vector2f(0,0);
		scale = 1f;
		setPosition(position.getX(), position.getY());
		color = Color.white;
		image = null;
		range = 0f;
	}
	
	public Entity(float[] points) {
		shape = new Polygon(points);
		scaledShape = shape;
		position = new Vector2f(0,0);
		scaledPosition = new Vector2f(0,0);
		scale = 1f;
		setPosition(shape.getCenterX(), shape.getCenterY());
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
		if (scale == 1f) {
			scaledPosition.set(position);
			scaledShape = shape;
		} else {
			scaledPosition.set(x*scale,y*scale);
			scaledShape.setCenterX(scaledPosition.getX()); scaledShape.setCenterY(scaledPosition.getY());
		}
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
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
		float[] sPoints = shape.getPoints();
		float[] points = new float[sPoints.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = sPoints[i];
			points[i] *= scale;
		}
		scaledShape = new Polygon(points);
		setPosition(position.getX(), position.getY());
	}
	
	// Getters
	public Shape getShape() {
		return scaledShape;
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
	public float getScale() {
		return scale;
	}
}
