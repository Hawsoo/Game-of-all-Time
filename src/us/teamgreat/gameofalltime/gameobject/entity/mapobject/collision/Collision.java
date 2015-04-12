package us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.MapObject;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Generalization of something for
 * entities to collide into.
 * @author Timothy Bennett
 *
 */
public abstract class Collision extends MapObject
{
	public static final int TYPE_WALL = 1;
	public static final int TYPE_EVENT_COLLIDER = 2;
	
	private int type;
	
	protected Sprite sprite;
	
	/**
	 * Gets the type of collision.
	 * @param type
	 */
	public Collision(double x, double y, double z, Sprite sprite, int type, Game game)
	{
		super(x, y, z, game);
		this.type = type;
		this.sprite = sprite;
	}
	
	/**
	 * Gets the type of collision.
	 * @return
	 */
	public int getType()
	{
		return type;
	}
	
	@Override
	public void render()
	{
		// Render sprite
		sprite.render((int)x, (int)(z * Resources.Z_RATIO + y));
	}
}
