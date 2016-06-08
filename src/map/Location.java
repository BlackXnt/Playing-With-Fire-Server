package map;

import java.io.Serializable;

public class Location implements Serializable{
	int x;
	int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Location(Location loction) {
		if (loction != null) {
			this.x = loction.getX();
			this.y = loction.getY();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Location [x=" + x + ", y=" + y + "]";
	}
}
