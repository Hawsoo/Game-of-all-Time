package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;
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
	 * @param collisions
	 * @param game
	 */
	public Player(int x, int y, int z, ArrayList<Collision> collisions, Game game)
	{
		super(x, y, z, 5, 0.35, collisions, game);
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
		/*Sprite spr = null;
		if (hspeed < 0 && vspeed < 0)
		{
			spr = Resources.player_sw;
		}
		else if (hspeed > 0 && vspeed < 0)
		{
			spr = Resources.player_se;
		}
		else if (hspeed > 0 && vspeed > 0)
		{
			spr = Resources.player_ne;
		}
		else if (hspeed < 0 && vspeed > 0)
		{
			spr = Resources.player_nw;
		}
		else if (hspeed < 0)
		{
			spr = Resources.player_w;
		}
		else if (hspeed > 0)
		{
			spr = Resources.player_e;
		}
		else if (vspeed < 0)
		{
			spr = Resources.player_s;
		}
		else if (vspeed > 0)
		{
			spr = Resources.player_n;
		}
		else
		{
			spr = Resources.player_s;
		}*/
		
		// Render
		GL11.glColor3f(1, 1, 1);
		Resources.player_beta.render((int)x, (int)(z * Resources.Z_RATIO + y));
	}
}
