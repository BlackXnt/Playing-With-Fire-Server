package server.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.entities.Player;

public class Client {
	private int id; 
	private Player player;
	private Socket socket;
	private ObjectOutputStream out;
	
	public Client(int id, Player player, Socket socket) {
		this.id = id;
		this.player = player;
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Client(Player player, Socket socket) {
		this(0 , player, socket);
	}
	
	public Client(Socket socket){
		this(0 , null, socket);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void send(Object object) {
		try {
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			System.err.println("Couldnt read or write");
			System.exit(1);
		}
	}
	
	public void update(){
		if(player != null){
			player.tick();
			NetworkMessenger.drawPlayer(this);
		}
	}
}
