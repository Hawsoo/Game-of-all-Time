package us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.wall;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Standard ground for the game.
 * @author Timothy Bennett
 *
 */
public class Wall_Regular extends Wall
{
	/**
	 * Creates regular ground.
	 */
	public Wall_Regular(double x, double y, double z, Game game) { super(x, y, z, Resources.block_beta, game); }

	@Override
	public void update() {}
}
