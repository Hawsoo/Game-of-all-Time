package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Animation;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;
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

	/**
	 * Creates a basic guard.
	 * @param x
	 * @param y
	 * @param z
	 * @param collisions
	 * @param game
	 */
	public Guard(int x, int y, int z, int direction, ArrayList<Collision> collisions, Game game)
	{
		super(x, y, z, 5, 0.35, direction, collisions, game);
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
		// TODO rewrite path-finding mechanic
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
		// Get correct guard animation
		Animation ani = null;
		switch (direction)
		{
		case DIR_N:
			ani = Resources.guard_ani_n;
			break;
			
		case DIR_NE:
			ani = Resources.guard_ani_ne;
			break;
			
		case DIR_E:
			ani = Resources.guard_ani_e;
			break;
			
		case DIR_SE:
			ani = Resources.guard_ani_se;
			break;
			
		case DIR_S:
			ani = Resources.guard_ani_s;
			break;
			
		case DIR_SW:
			ani = Resources.guard_ani_sw;
			break;
			
		case DIR_W:
			ani = Resources.guard_ani_w;
			break;
			
		case DIR_NW:
			ani = Resources.guard_ani_nw;
			break;
		}
		
		// Render
		GL11.glColor3f(1, 1, 1);
		ani.render((int)x, (int)(z * Resources.Z_RATIO + y));
	}
}
