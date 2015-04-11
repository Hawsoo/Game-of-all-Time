package us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Standard ground for the game.
 * @author Timothy Bennett
 *
 */
public class Gnd_Regular extends Ground
{
	/**
	 * Creates regular ground.
	 */
	public Gnd_Regular(double x, double y, double z, Game game) { super(x, y, z, Resources.block_beta, game); }
}
