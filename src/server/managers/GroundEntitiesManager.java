package server.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import server.entities.Entity;
import server.network.NetworkMessenger;

public class GroundEntitiesManager {
	private List<Entity> groundEntities = new ArrayList<>(); 
	
	public GroundEntitiesManager(){
		groundEntities = new ArrayList<>();
	}
	
	public boolean addGroundItem(Entity entity){
		return groundEntities.add(entity);
	}
	
	public void update() {
		Iterator<Entity> iter = groundEntities.iterator();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if (entity.isValid()) {
				entity.tick();
				//NetworkMessenger.drawGroundEntity(entity);
			} else {
				iter.remove();
			}
		}
	}
}
