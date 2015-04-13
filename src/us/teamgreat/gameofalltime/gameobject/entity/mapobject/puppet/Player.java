package us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Animation;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.PathNode;
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
	public Player(int x, int y, int z, int direction, ArrayList<PathNode> pathnodes, ArrayList<Collision> collisions, Game game)
	{
		super(x, y, z, 5, 0.35, direction, null, collisions, game);
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
		// Get correct player animation
		Animation ani = null;
		switch (direction)
		{
		case DIR_N:
			ani = Resources.player_ani_n;
			break;
			
		case DIR_NE:
			ani = Resources.player_ani_ne;
			break;
			
		case DIR_E:
			ani = Resources.player_ani_e;
			break;
			
		case DIR_SE:
			ani = Resources.player_ani_se;
			break;
			
		case DIR_S:
			ani = Resources.player_ani_s;
			break;
			
		case DIR_SW:
			ani = Resources.player_ani_sw;
			break;
			
		case DIR_W:
			ani = Resources.player_ani_w;
			break;
			
		case DIR_NW:
			ani = Resources.player_ani_nw;
			break;
		}
		
		// Render
		GL11.glColor3f(1, 1, 1);
		ani.render((int)x, (int)(z/* * Resources.Z_RATIO*/ + y + Resources.drawYoff));
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(x - (basesize / 2), z + (basesize / 2));
			GL11.glVertex2d(x + (basesize / 2), z + (basesize / 2));
			GL11.glVertex2d(x + (basesize / 2), z - (basesize / 2));
			GL11.glVertex2d(x - (basesize / 2), z - (basesize / 2));
		}
		GL11.glEnd();
		
		// Render Grounds
		for (Collision collision : collisions)
		{
			GL11.glColor3f(1, 0, 0);
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex2d(collision.x - (basesize / 2), collision.z + (basesize / 2));
				GL11.glVertex2d(collision.x + (basesize / 2), collision.z + (basesize / 2));
				GL11.glVertex2d(collision.x + (basesize / 2), collision.z - (basesize / 2));
				GL11.glVertex2d(collision.x - (basesize / 2), collision.z - (basesize / 2));
			}
			GL11.glEnd();
		}
	}
}
