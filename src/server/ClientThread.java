package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import server.entities.Player;
import server.network.Client;
import server.network.NetworkMessenger;
import server.network.commands.CommandWrapper;

public class ClientThread implements Runnable{
	private Client client; 
	private BufferedWriter out;
	
	public ClientThread(Client client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		Player player = new Player(400, 400, 27, 27, 3, null);
		client.setPlayer(player);
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
			String clientString;
			while ((clientString = in.readLine()) != null) {
				CommandWrapper cw = new CommandWrapper(clientString);
				cw.execute();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Disconnected from server");
		System.exit(0);
	}
	
}
