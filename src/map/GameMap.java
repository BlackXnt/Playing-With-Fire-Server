package map;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import server.util.Direction;

public class GameMap implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private int width;
	private int height;
	private Tile[][] tiles;

	public GameMap(String name, int id, int width, int height, Tile[][] tiles) {
		this.name = name;
		this.id = id;
		this.width = width;
		this.height = height;
		this.tiles = tiles;
		configure();
	}
	
	public GameMap(String name, int width, int height, Tile[][] tiles) {
		this(name, 0, width, height, tiles);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void configure() {
		int x = 0, y = 0, j;
		Tile lastTile = tiles[0][0];
		for (int i = 0; i < tiles.length; i++) {
			for (j = 0; j < tiles[i].length; j++) {
				Tile tile = tiles[i][j];
				if (tile != null) {
					tile.setLocation(x, y);
					lastTile = tile;
				}
				y += lastTile.getHeight();
			}
			x += lastTile.getWidth();
			y = 0;
		}
	}

	private Tile getTile(int x, int y, Direction direction, int shifter) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getBounds().contains(x, y)) {
					if(direction == null){
						return tiles[i][j];
					}
					switch (direction) {
					case Up:
						if(j - shifter < 0 || j - shifter >= tiles[i].length) return null;
						return tiles[i][j - shifter];
					case Down:
						if(j + shifter < 0 || j + shifter >= tiles[i].length) return null;
						return tiles[i][j + shifter];
					case Right:
						if(i + shifter < 0 || i + shifter >= tiles.length) return null;
						return tiles[i + shifter][j];
					case Left:
						if(i - shifter < 0 || i - shifter >= tiles.length) return null;
						return tiles[i - shifter][j];
					default:
						return tiles[i][j];
					}
				}
			}
		}
		return null;
	}
	
	public TileType getTileType(int x, int y, Direction direction, int shifter) {
		Tile tile = getTile(x, y, direction, shifter);
		if (tile != null) {
			return tile.getType();
		}
		return null;
	}
	
	public Set<TileType> getInteractingTilesTypes(Rectangle box){
		Set<TileType> interactingTiles = new HashSet<>();
		System.out.println(box);
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getBounds().intersects(box)) {
					interactingTiles.add(tiles[i][j].getType());
				}
			}
		}
		return interactingTiles;
	}
	
	public TileType getTileType(int x, int y, Direction direction) {
		return getTileType(x, y, direction, 1);
	}
	
	public TileType getTileType(int x, int y) {
		return getTileType(x, y, null, 0);
	}

	public void setTileType(int x, int y, TileType type, Direction direction, int shifter) {
		Tile tile = getTile(x, y, direction, shifter);
		if (tile != null) {
			tile.setType(type);
		}
	}
	
	public void setTileType(int x, int y, TileType type, Direction direction) {
		setTileType(x, y, type, direction, 1);
	}
	
	public void setTileType(int x, int y, TileType type) {
		setTileType(x, y, type, null, 0);
	}
	
	public boolean isTileDaenerys(int x, int y, Direction direction, int shifter){
		Tile tile = getTile(x, y, direction, shifter);
		return tile != null && tile.isDaenerys();
	}
	
	public boolean isTileDaenerys(int x, int y, Direction direction){
		return isTileDaenerys(x, y, direction, 1);
	}
	
	public boolean isTileDaenerys(int x, int y){
		return isTileDaenerys(x, y, null, 0);
	}
	
	public boolean isTileSolid(int x, int y, Direction direction, int shifter){
		Tile tile = getTile(x, y, direction, shifter);
		return tile != null && tile.isSolid();
	}
	
	public boolean isTileSolid(int x, int y, Direction direction){
		return isTileSolid(x, y, direction, 1);
	}
	
	public boolean isTileSolid(int x, int y){
		return isTileSolid(x, y, null, 0);
	}
	
}
