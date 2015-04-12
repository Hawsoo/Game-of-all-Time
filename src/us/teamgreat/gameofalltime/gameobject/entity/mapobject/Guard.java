package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground.Ground;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * The guard that follows a set path, but
 * can get possessed.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class Guard extends Puppet
{
	
	/**
	 * Creates guard.
	 * @param x
	 * @param y
	 * @param z
	 * @param game
	 */
	
	// Path of points
	ArrayList<Vector2f> points = new ArrayList<Vector2f>();

	
	public Guard(int x, int y, int z, ArrayList<Ground> grounds, Game game)
	{
		super(x, y, z, 5, 0.35, grounds, game);
		this.isPossessed = true;
	}
	
	/**
	 * Adds a path point.
	 * @param x
	 * @param z
	 */
	public void addPathPoint(double x, double z)
	{
		Vector2f point = new Vector2f((float) x,(float) z);
		points.add(point);
	}
	
	@Override
	public void normalUpdate()
	{
		for (Vector2f point : points)
		{
			double dx = x - point.getX();
			double dz = z - point.getY();
			boolean run = true;
			while ((dx != 0 && dz != 0) || run)
			{
				dx = x - point.getX();
				dz = z - point.getY();
				
				double dtotal = Math.sqrt(dx * dx + dz * dz);
				hspeed = dx / dtotal;	// Times a constant
				vspeed = dz / dtotal;	// Times a constant
				move();
				
				run = false;
			}
		}
	}

	@Override
	public void render()
	{
		// Draw guard
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
		
		// Deine muti ist so fett, dass wenn sie sits um die house, sie sits um die house
		
		// Render
		GL11.glColor3f(1, 1, 1);
		Resources.guard_beta.render((int)x, (int)(z * Resources.Z_RATIO + y));
	}
}
