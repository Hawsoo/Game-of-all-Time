package us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.wall;

import org.lwjgl.opengl.GL11;

import us.teamgreat.MainClass;
import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;

/**
 * Generic wall for collision.
 * @author Timothy Bennett
 *
 */
public class Wall extends Collision
{
	/**
	 * Creates a ground.
	 * @param x
	 * @param y
	 * @param z
	 * @param color
	 */
	public Wall(double x, double y, double z, Sprite sprite, Game game)
	{
		super(x, y, z, sprite, Collision.TYPE_WALL, game);
	}

	@Override
	public void update() {}
	
	@Override
	public void render()
	{
		// Render
		if (MainClass.DEBUG)
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
		else
			GL11.glColor3f(1, 1, 1);
		
		super.render();
	}
}
