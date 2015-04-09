package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground.Ground;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * The player (starts out as being
 * lucid, AKA "Possessed").
 * @author Timothy Bennett
 *
 */
public class Player extends Puppet
{
	/**
	 * Creates player.
	 * @param x
	 * @param y
	 * @param z
	 * @param game
	 */
	public Player(int x, int y, int z, ArrayList<Ground> grounds, Game game)
	{
		super(x, y, z, 5, 0.35, grounds, game);
		this.isPossessed = true;
	}
	
	@Override
	public void normalUpdate()
	{
		// Stand and do nothing
		hspeed = vspeed = 0;
	}

	@Override
	public void render()
	{
		// Draw sanic
		Resources.sanic_beta.render((int)x, (int)(z), new Vector2f());
	}
}
