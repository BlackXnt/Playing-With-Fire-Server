package server;

import server.managers.ClientsManager;
import server.managers.GroundEntitiesManager;
import server.managers.MapsManager;
import server.network.NetworkMessenger;

public class MainThread implements Runnable{
	private ClientsManager clientsManager;
	private MapsManager mapsManager;
	private GroundEntitiesManager groundEntitiesManager;
	
	
	public MainThread(ClientsManager clientsManager, MapsManager mapsManager, GroundEntitiesManager groundEntitiesManager) {
		this.clientsManager = clientsManager;
		this.mapsManager = mapsManager;
		this.groundEntitiesManager = groundEntitiesManager;
	}
	
	@Override
	public void run() {
		while(true){
		clientsManager.update();
		groundEntitiesManager.update();
		NetworkMessenger.sendMap(mapsManager.getCurrentMap());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
