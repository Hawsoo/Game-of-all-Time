package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import org.lwjgl.opengl.GL11;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;

/**
 * A non-abstract map object.
 * @author Timothy Bennett
 *
 */
public class MapObjectContainer extends MapObject
{
	private Sprite sprite;
	
	/**
	 * Create a generic sprite-based map object.
	 * @param x
	 * @param y
	 * @param z
	 * @param sprite
	 * @param game
	 */
	public MapObjectContainer(double x, double y, double z, Sprite sprite, Game game)
	{
		super(x, y, z, game);
		this.sprite = sprite;
	}
	
	@Override
	public void update() {}
	
	@Override
	public void render()
	{
		// Render the sprite
		GL11.glColor3f(1, 1, 1);
		sprite.render((int)x, (int)(z + y));
	}
	
}
