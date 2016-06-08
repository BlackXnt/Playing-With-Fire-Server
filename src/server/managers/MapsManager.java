package server.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import map.GameMap;
import map.Tile;
import map.TileType;

public class MapsManager {
	private static List<GameMap> maps = new ArrayList<>();
	private static int numberOfMaps;
	private static int currentMapId;

	public MapsManager() {
		readMaps();
	}

	public GameMap getMap(int i) {
		return maps.get(i);
	}

	public static void addMap(GameMap map) {
		if (maps.add(map)) {
			numberOfMaps++;
			map.setId(numberOfMaps);
		}
	}

	public static int getCurrentMapId() {
		return currentMapId;
	}

	public static void setCurrentMapId(int mapId) {
		currentMapId = mapId;
	}

	public static GameMap getCurrentMap() {
		return maps.get(currentMapId);
	}

	private void readMaps() {
		File[] files = new File("maps").listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				maps.add(praseMapFile(file));
			}
		}
	}

	private GameMap praseMapFile(File file) {
		String line = null;
		int j = 0;
		Tile[][] tiles = new Tile[25][20];
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) { 
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '#') {
						tiles[i][j] = new Tile(TileType.Brick, 32, 32);
					} else if (line.charAt(i) == '$') {
						tiles[i][j] = new Tile(TileType.Box, 32, 32);
					} else if (line.charAt(i) == ' ') {
						tiles[i][j] = new Tile(TileType.Air, 32, 32);
					} else if (Character.isDigit(line.charAt(i))){
						tiles[i][j] = new Tile(TileType.Spawn, 32, 32);
					}
				}
				j++;
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error! Was unable to find map file");
			return null;
		} catch (IOException ex) {
			System.out.println("Error! Was unable to read map file");
			ex.printStackTrace();
			return null;
		}
		return new GameMap(file.getName(), 800, 640, tiles);
	}
}
