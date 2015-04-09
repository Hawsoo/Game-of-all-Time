package us.teamgreat.gameofalltime.gameobject.entity;

import us.teamgreat.gameofalltime.gameobject.GameObject;

/**
 * Basic form of an entity.
 * @author Timothy Bennett
 *
 */
public abstract class Entity implements GameObject
{
	protected double x, y;
	
	/**
	 * Creates entity.
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
