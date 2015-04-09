package us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import us.teamgreat.gameofalltime.gameobject.entity.mapobject.MapObject;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Generic ground for collision.
 * @author Timothy Bennett
 *
 */
public abstract class Ground extends MapObject
{
	private Color color;
	
	/**
	 * Creates a ground.
	 * @param x
	 * @param y
	 * @param z
	 * @param color
	 */
	public Ground(int x, int y, int z, Color color)
	{
		super(x, y, z);
		this.color = color;
	}
	
	@Override
	public void update() {}
	
	@Override
	public void render()
	{
		// Render rectangle
		color.bind();
		
		// Apply transformations
		GL11.glScalef(Resources.BLOCK_SIZE, Resources.BLOCK_SIZE, Resources.BLOCK_SIZE);
		GL11.glTranslated(x, y, z);
		{
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			GL11.glBegin(GL11.GL_QUADS);
			{
				// Top
				GL11.glVertex3f(0, 0, 0);
				GL11.glVertex3f(1, 0, 0);
				GL11.glVertex3f(1, 0, -1);
				GL11.glVertex3f(0, 0, -1);
				
				// Left
				GL11.glVertex3f(0, 0, -1);
				GL11.glVertex3f(1, 0, -1);
				GL11.glVertex3f(1, -1, -1);
				GL11.glVertex3f(0, -1, -1);
				
				// Right
				GL11.glVertex3f(1, 0, -1);
				GL11.glVertex3f(1, 0, 0);
				GL11.glVertex3f(1, -1, 0);
				GL11.glVertex3f(1, -1, -1);
			}
			GL11.glEnd();
		}
		GL11.glTranslated(-x, -y, -z);
		GL11.glScalef(1f / Resources.BLOCK_SIZE, 1f / Resources.BLOCK_SIZE, 1f / Resources.BLOCK_SIZE);
	}
}
