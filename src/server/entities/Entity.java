package server.entities;

import java.awt.Rectangle;
import java.io.Serializable;

import map.Location;

public abstract class Entity implements Serializable{
	protected Location location; 
	protected int width, height;
	protected boolean valid = true;
	
	public Entity(int x, int y, int width, int height){
		this.location = new Location(x, y);
		this.width = width;
		this.height = height;
	}
	
	public Entity(Location location, int width, int height){
		this.location = new Location(location);
		this.width = width;
		this.height = height;
	}
	
	public Entity(int x, int y){
		this.location = new Location(x, y);
	}
	
	public Entity(Location location){
		this.location = new Location(location);
	}
	
	public Location getLocation(){
		return new Location(location);
	}
	
	public void setLocation(int x, int y) {
		this.location = new Location(x, y);
	}	
	
	public void setLocation(Location location){
		this.location = new Location(location);
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
	
	public abstract void tick();
	
	public Rectangle getBounds() {
	    return new Rectangle(location.getX(), location.getY(), (int)width, (int)height);
	}
	
	public boolean isValid(){
		return valid;
	}
}
