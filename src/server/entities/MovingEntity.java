package server.entities;

import map.Location;
import server.util.Direction;

public abstract class MovingEntity extends Entity{
	protected int speed, currentSpeed;
	protected Direction direction;
	
	public MovingEntity(int x, int y, int width, int height, int speed, Direction direction) {
		super(x, y, width, height);
		this.speed = speed;
		this.direction = direction;
	}
	
	public MovingEntity(Location location, int width, int height, int speed, Direction direction) {
		super(location, width, height);
		this.speed = speed;
		this.direction = direction;
	}
	
	public MovingEntity(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public MovingEntity(Location location, int width, int height) {
		super(location, width, height);
	}
	
	public MovingEntity(int x, int y) {
		super(x, y);
	}
	
	public MovingEntity(Location location){
		super(location);
	}
	
	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public abstract void move(Direction direction);
	
	public abstract void stop();
}
