package me.mcblueparrot.client.util.data;



public class Position {

	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position offset(int x, int y) {
		return new Position(this.x + x, this.y - y);
	}

	public Rectangle expandToRectangle(int width, int height) {
		return new Rectangle(x, y, width, height);
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

}
