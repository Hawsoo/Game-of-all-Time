package us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground;

import org.newdawn.slick.Color;

import us.teamgreat.gameofalltime.Game;

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
	public Gnd_Regular(double x, double y, double z, Game game) { super(x, y, z, Color.green, game); }
}
