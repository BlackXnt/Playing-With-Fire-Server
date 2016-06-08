package server.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import server.entities.Entity;

public class GroundEntitiesManager {
	private static List<Entity> groundEntities = new ArrayList<>(); 
	
	public static boolean addGroundItem(Entity entity){
		return groundEntities.add(entity);
	}
	
	public static void updateGroundItems() {
		Iterator<Entity> iter = groundEntities.iterator();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if (entity.isValid()) {
				entity.tick();
			} else {
				iter.remove();
			}
		}
	}
}
