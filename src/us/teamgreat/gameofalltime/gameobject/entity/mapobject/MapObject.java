package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.entity.Entity;

/**
 * Generic object for placement
 * on the map.
 * @author Timothy Bennett
 *
 */
public abstract class MapObject extends Entity
{
	protected double z;
	protected double hspeed, vspeed;
	
	/**
	 * Create mapobject.
	 * @param x
	 * @param y
	 * @param z
	 */
	public MapObject(int x, int y, int z, Game game)
	{
		super(x, y, game);
		this.z = z;
	}
}
