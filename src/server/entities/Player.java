package server.entities;

import java.awt.Rectangle;

import map.GameMap;
import map.Location;
import map.TileType;
import server.GameServer;
import server.managers.ClientsManager;
import server.managers.GroundEntitiesManager;
import server.managers.MapsManager;
import server.network.Client;
import server.network.NetworkMessenger;
import server.util.Direction;

public class Player extends MovingEntity{
	private int maxNumberOfBombs = 1;
	private int bombsPlaced; 
	
	public Player(Location location, int width, int height, int speed, Direction direction) {
		super(location, width, height, speed, direction);
	}
	
	public Player(int x, int y, int width, int height, int speed, Direction direction) {
		super(x, y, width, height, speed, direction);
	}
	
	public Player(int x, int y) {
		super(x, y);
	}
	
	public void move(Direction direction){
		currentSpeed = speed;
		this.direction = direction;
	}
	
	public void stop(){
		currentSpeed = 0;
		this.direction = null;
	}
	
	@Override
	public void tick() {
		int newX = location.getX(), newY = location.getY();
		if(direction == null || currentSpeed == 0){
			return;
		}
		
		switch (direction) {
		case Up:
			newY -= currentSpeed;
			break;
		case Down:
			newY += currentSpeed;
			break;
		case Right:
			newX += currentSpeed;
			break;
		default:
			newX -= currentSpeed;
			break;
		}

		// Collision checking
		GameMap map = GameServer.getMapsManager().getCurrentMap();
		if (map.getInteractingTilesTypes(new Rectangle(newX, newY, width, height))
				.contains(TileType.Brick)
				|| map.getInteractingTilesTypes(new Rectangle(newX, newY, width, height))
						.contains(TileType.Box)) {
			stop();
		}

		if (currentSpeed != 0) {
			setLocation(newX, newY);
		}
	}
	
	public void placeBomb(){
		if(bombsPlaced < maxNumberOfBombs){
			Bomb bomb = new Bomb(location.getX(), location.getY(), GameServer.getClientsManager().getClientByPlayer(this).getId());
			GameServer.getGroundEntitiesManager().addGroundItem(bomb);
			bombsPlaced++;
			System.out.println(20000);
			NetworkMessenger.drawGroundEntity(bomb);
		}
		
	}
	
	public void removeDetonatedBomb(){
		if(bombsPlaced > 0){
			bombsPlaced--;
		}
	}
}
