package me.mcblueparrot.client.util.data;


import me.mcblueparrot.client.util.Utils;


public class Rectangle {

	
	private int x;
	
	private int y;
	
	private int width;
	
	private int height;

	public Rectangle offset(int x, int y) {
		return new Rectangle(this.x + x, this.y + y, width, height);
	}

	public boolean contains(Position position) {
		return contains(position.getX(), position.getY());
	}

	public boolean contains(int x, int y) {
		return x > this.x && x < this.x + width
				&& y > this.y && y < this.y + height;
	}

	public void fill(Colour colour) {
		Utils.drawRectangle(this, colour);
	}

	public void stroke(Colour colour) {
		Utils.drawOutline(this, colour);
	}

	public Rectangle multiply(float scale) {
		return new Rectangle(x, y, (int) (width * scale), (int) (height * scale));
	}

	public Rectangle(int x, int y) {
		this(x, y, 1, 1);
	}

	public Rectangle(int x2, int y2, int width2, int height2) {
		this.x = x2;
		this.y = y2;
		this.width = width2;
		this.height = height2;
	}

	public int getEndX() {
		return y + height;
	}

	public int getEndY() {
		return y + height;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
