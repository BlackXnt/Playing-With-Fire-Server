package server.entities;

import map.GameMap;
import map.TileType;
import server.GameServer;
import server.managers.ClientsManager;
import server.managers.MapsManager;
import server.util.Direction;

public class Bomb extends Entity {
	//The player that placed the bomb
	private int placerId; 

	private static final int CYCLES_TO_DETONATE = 100;
	private static final int CYCLES_TO_INVALIDATION = 120;
	private int currentCycle;
	private boolean exploded = false;
	private int power = 4;

	public Bomb(int x, int y, int width, int height, int placerId) {
		super(x, y, width, height);
		this.placerId = placerId;
	}
	
	public Bomb(int x, int y, int placerId) {
		super(x, y);
		this.placerId = placerId;
	}
	
	@Override
	public void tick() {
		if (currentCycle == CYCLES_TO_DETONATE) {
			if(exploded == false){
				GameServer.getClientsManager().getClientById(placerId).getPlayer().placeBomb();
			}
			GameMap map = MapsManager.getCurrentMap();
			int bombCenterX = (int) (location.getX() + width / 2);
			int bombCenterY = (int) (location.getY() + height / 2);
			map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Center);
			for (Direction dir : Direction.values()) {
				for (int i = 1; i <= power; i++) {
					if (dir == Direction.Up || dir == Direction.Down) {
						if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, i)) {
							if (i != power) {
								map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Vertical, dir, i);
							} else {
								switch (dir) {
								case Up:
									if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, power)) {
										map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Up, dir, power);
									}
									break;
								case Down:
									if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, power)) {
										map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Down, dir, power);
									}
									break;
								}
							}
						} else {
							break;
						}
					}
					if (dir == Direction.Right || dir == Direction.Left) {
						if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, i)) {
							if (i != power) {
								map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Horizontal, dir, i);
							} else {
								switch (dir) {
								case Right:
									if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, power)) {
										map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Right, dir, power);
									}
									break;
								case Left:
									if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, power)) {
										map.setTileType(bombCenterX, bombCenterY, TileType.Explosion_Left, dir, power);
									}
									break;
								}
							}
						} else {
							break;
						}
					}
				}
			}
		} else if(currentCycle >= CYCLES_TO_INVALIDATION){
			if (valid) {
				GameMap map = MapsManager.getCurrentMap();
				int bombCenterX = (int) (location.getX() + width / 2);
				int bombCenterY = (int) (location.getY() + height / 2);
				for (Direction dir : Direction.values()) {
					for (int i = 0; i < power + 1; i++) {
						if (!map.isTileDaenerys(bombCenterX, bombCenterY, dir, i)) {
							map.setTileType(bombCenterX, bombCenterY, TileType.Air, dir, i);
						} else {
							break; 
						}
					}
				}
			}
			valid = false;
		}
		currentCycle++;
	}
	
	public String toString(){
		return "entity|bomb|" + location.getX() + "|" + location.getY() + "|" + placerId;
	}
	
}
