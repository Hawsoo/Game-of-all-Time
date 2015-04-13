package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * A path object used for path-finding objects.
 * @author Timothy Bennett
 *
 */
public class PathNode extends MapObject
{
	private Sprite sprite;
	
	/**
	 * Creates a path node.
	 * @param x
	 * @param y
	 * @param z
	 * @param game
	 */
	public PathNode(double x, double y, double z, Game game)
	{
		super(x, y, z, game);
		sprite = Resources.path_node;
	}
	
	/**
	 * Return this position if lookingpos
	 * is in the right area.
	 * @param lookingpos
	 * @return
	 */
	public Vector3f getPathNode(Vector3f lookingpos)
	{
		// TODO write path-finding mechanic
		return null;
	}

	@Override
	public void update() {}

	@Override
	public void render()
	{
		// Render the sprite
		sprite.render((int)x, (int)(z + y));
	}
	
}
