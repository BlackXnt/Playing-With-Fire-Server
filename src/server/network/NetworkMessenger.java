package server.network;

import map.GameMap;
import server.GameServer;
import server.entities.Player;

public class NetworkMessenger {
	public static void sendMap(GameMap map) {
		GameServer.getClientsManager().sendAll(map);
	}

	public static void initConnection(Client client){
		client.send("player|init|" + client.getId());
	}
	
	public static void drawPlayer(Client client) {
		Player player = client.getPlayer();
		GameServer.getClientsManager().sendAll("player|draw|" + client.getId() + "|" + player.getDirection() + "|"
				+ player.getLocation().getX() + "|" + player.getLocation().getY());
	}
}
