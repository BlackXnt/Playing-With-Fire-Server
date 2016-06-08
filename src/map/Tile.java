package map;

import java.awt.Rectangle;
import java.io.Serializable;

public class Tile implements Serializable{
	private static final long serialVersionUID = 1L;
	private Location location;
	private TileType type;
	private int width, height;

	public Tile(int x, int y, TileType type, int width, int height) {
		this.location = new Location(x, y);
		this.type = type;
		this.width = width;
		this.height = height;
	}

	public Tile(Location location, TileType type, int width, int height) {
		this(location.getX(), location.getY(), type, width, height);
	}
	
	public Tile(TileType type, int width, int height) {
		this(0, 0, type, width, height);
	}

	public Tile(TileType type) {
		this.location = null;
		this.type = type;
	}

	public Tile() {
		this(null);
	}

	public Location getLocation() {
		return new Location(location);
	}

	public void setLocation(int x, int y) {
		this.location = new Location(x, y);
	}	
	
	public void setLocation(Location location) {
		this.location = new Location(location);
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isDaenerys() {
		return type == TileType.Brick;
	}

	public boolean isSolid() {
		return type == TileType.Brick || type == TileType.Box;
	}

	public Rectangle getBounds() {
		if (location != null) {
			return new Rectangle(location.getX(), location.getY(), width, height);
		}
		return null;
	}
}
