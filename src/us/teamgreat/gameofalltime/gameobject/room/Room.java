package us.teamgreat.gameofalltime.gameobject.room;

import java.util.ArrayList;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.GameObject;
import us.teamgreat.gameofalltime.gameobject.entity.Entity;

/**
 * Basic room for objects.
 * @author Timothy Bennett
 *
 */
public abstract class Room implements GameObject
{
	protected Game game;
	protected ArrayList<Entity> entities;
	
	/**
	 * Initializes room.
	 */
	public Room(Game game)
	{
		this.game = game;
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
