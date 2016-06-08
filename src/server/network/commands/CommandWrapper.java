package server.network.commands;

import java.awt.event.KeyEvent;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import map.GameMap;
import server.GameServer;
import server.managers.ClientsManager;
import server.network.Client;
import server.util.Direction;

public class CommandWrapper {

	private String command;

	public CommandWrapper(String command) {
		this.command = command;
	}

	public void execute() {
		String[] content = command.split("\\|");
		if (content[0].equals("key")) {
			Client client = GameServer.getClientsManager().getClientById(Integer.parseInt(content[1]));
			switch (content[2]) {
			case "pressed":
				int key = Integer.parseInt(content[3]);
				if (getDirection(key) != null) {
					client.getPlayer().move(getDirection(key));
				} else if (key == KeyEvent.VK_SPACE) {
					client.getPlayer().placeBomb();
				}
				break;
			case "released":
				client.getPlayer().stop();
				break;
			default:
				break;
			}
		}
	}

	private Direction getDirection(int key) {
		switch (key) {
		case KeyEvent.VK_UP:
			return Direction.Up;
		case KeyEvent.VK_DOWN:
			return Direction.Down;
		case KeyEvent.VK_RIGHT:
			return Direction.Right;
		case KeyEvent.VK_LEFT:
			return Direction.Left;
		default:
			return null;
		}
	}
}
