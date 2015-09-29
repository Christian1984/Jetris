package block;

import java.awt.Color;

public class Block {
	private int x;
	private int y;
	private Color color;
	
	public Block(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public Block(int x, int y) {
		this(x, y, Color.RED);
	}
	
	public Block(Color color) {
		this(0, 0);
	}
	
	public Block() {
		this(Color.RED);
	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}
	
	//public methods
	public void move(int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
	}
	
	public void setPos(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	public void setColor(Color newColor) {
		if (newColor != null) {
			color = newColor;
		}
	}
	
	//collision
	/*public boolean isColliding(Block other) {
		return isColliding(other.x, other.y);
	}
	
	public boolean isColliding(int x, int y) {
		return this.x == x && this.y == y; 
	}*/
	
	//dump
	@Override
	public String toString() {
		return "X = " + x + "; Y = " + y;
	}

	public boolean inCanvas(int width, int height) {
		return x >= 0 && y >= 0 && x < width && y < height;
	}
}
