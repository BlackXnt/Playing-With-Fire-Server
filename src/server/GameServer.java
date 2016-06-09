package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.entities.Player;
import server.managers.ClientsManager;
import server.managers.GroundEntitiesManager;
import server.managers.MapsManager;
import server.network.Client;
import server.network.NetworkMessenger;
import server.util.Direction;

public class GameServer {
	private static final int port = 23351;
	private boolean running = true;
	private static ClientsManager clientsManager;
	private static GroundEntitiesManager groundEntitiesManager;
	private static MapsManager mapsManager;
	
	public GameServer(){
		ServerSocket serverSocket;
		clientsManager = new ClientsManager();
		groundEntitiesManager = new GroundEntitiesManager();
		mapsManager = new MapsManager();
		Thread mainThread = new Thread(new MainThread(clientsManager, mapsManager, groundEntitiesManager));
		mainThread.start();
		try{
			serverSocket = new ServerSocket(port);
			while(running){
				Socket socket = serverSocket.accept();
				Client client = new Client(socket);
				clientsManager.addClient(client);
				NetworkMessenger.initConnection(client);
				ClientThread st = new ClientThread(client);
				Thread t = new Thread(st);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static ClientsManager getClientsManager(){
		return clientsManager;
	}
	
	public static GroundEntitiesManager getGroundEntitiesManager(){
		return groundEntitiesManager;
	}
	
	public static MapsManager getMapsManager(){
		return mapsManager;
	}
	
	public static void main(String[] args) {
		GameServer gameServer = new GameServer();
	}
	
}
