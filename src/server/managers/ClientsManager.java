package server.managers;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import server.entities.Player;
import server.network.Client;
import server.network.NetworkMessenger;

public class ClientsManager {
	private List<Client> clients;
	private int numberOfPlayers;
	private Object lock = new Object();
	
	public ClientsManager(){
		clients = new ArrayList<Client>();
	}
	
	public ClientsManager(List<Client> clients){
		this.clients = new ArrayList<Client>(clients);
	}
	
	public boolean addClient(Client client){
		synchronized (lock) {
			if(clients.add(client)){
				numberOfPlayers++;
				client.setId(numberOfPlayers);
				return true;
			}
			return false;
		}
	}
	
	public Client getClientById(int id){
		for(Client client: clients){
			if(client.getId() == id){
				return client;
			}
		}
		return null;
	}
	
	public Client getClientByPlayer(Player player){
		for(Client client: clients){
			if(client.getPlayer().equals(player)){
				return client;
			}
		}
		return null;
	}
	
	public void sendAll(Object object){
		synchronized (lock) {
			for (Client client : clients) {
				client.send(object);
			}
		}
	}
	
	public void update(){
		synchronized (lock) {
			for (Client client : clients) {
				client.update();
			}
		}
	}
}
