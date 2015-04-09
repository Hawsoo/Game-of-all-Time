package us.teamgreat.gameofalltime.gameobject.room;

import java.util.ArrayList;

import us.teamgreat.gameofalltime.gameobject.GameObject;
import us.teamgreat.gameofalltime.gameobject.entity.Entity;

/**
 * Basic room for objects.
 * @author Timothy Bennett
 *
 */
public abstract class Room implements GameObject
{
	protected ArrayList<Entity> entities;
	
	/**
	 * Initializes room.
	 */
	public Room()
	{
		entities = new ArrayList<Entity>();
	}
	
	@Override
	public void update()
	{
		// Update each entity
		for (Entity entity : entities)
			entity.update();
	}
	
	@Override
	public void render()
	{
		// Render each entity
		for (Entity entity : entities)
			entity.render();
	}
}
