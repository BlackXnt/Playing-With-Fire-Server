package server.network;

import java.awt.Rectangle;

import map.GameMap;
import server.GameServer;
import server.entities.Entity;
import server.entities.Player;

public class NetworkMessenger {
	public static void sendMap(GameMap map) {
		GameServer.getClientsManager().sendAll(map);
		//System.out.println(map.getInteractingTilesTypes(new Rectangle(0, 0, 200, 200)));
	}

	public static void initConnection(Client client){
		client.send("player|init|" + client.getId());
	}
	
	public static void drawPlayer(Client client) {
		Player player = client.getPlayer();
		GameServer.getClientsManager().sendAll("player|draw|" + client.getId() + "|" + player.getDirection() + "|"
				+ player.getLocation().getX() + "|" + player.getLocation().getY());
	}
	
	public static void drawGroundEntity(Entity entity){
		GameServer.getClientsManager().sendAll("entity|" + entity.toString());
	}
}
