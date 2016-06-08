package server;

import server.managers.ClientsManager;
import server.managers.MapsManager;
import server.network.NetworkMessenger;

public class MainThread implements Runnable{
	private ClientsManager clientsManager;
	private MapsManager mapsManager;
	
	
	public MainThread(ClientsManager clientsManager, MapsManager mapsManager) {
		this.clientsManager = clientsManager;
		this.mapsManager = mapsManager;
	}
	
	@Override
	public void run() {
		while(true){
		clientsManager.update();
		NetworkMessenger.sendMap(mapsManager.getCurrentMap());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
