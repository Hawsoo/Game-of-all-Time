package us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground;

import org.lwjgl.opengl.GL11;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.MapObject;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Generic ground for collision.
 * @author Timothy Bennett
 *
 */
public abstract class Ground extends MapObject
{
	private Sprite sprite;
	
	/**
	 * Creates a ground.
	 * @param x
	 * @param y
	 * @param z
	 * @param color
	 */
	public Ground(double x, double y, double z, Sprite sprite, Game game)
	{
		super(x, y, z, game);
		this.sprite = sprite;
	}
	
	@Override
	public void update() {}
	
	@Override
	public void render()
	{
		// Render
		GL11.glColor3f(1, 1, 1);
		sprite.render((int)x, (int)(z * Resources.Z_RATIO + y));
		
		// Disable textures
		/*GL11.glDisable(GL11.GL_TEXTURE_2D);
		{
			// Render rectangle
//			sprite.getTexture().bind();
			
			// Apply transformations
			/*GL11.glTranslated(x, y, z);
			{
				/*GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
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
				GL11.glEnd();* /
				
				sprite.render(0, 0);
			}
			GL11.glTranslated(-x, -y, -z);* /
			
			
		}
		// Re-enable textures
		GL11.glEnable(GL11.GL_TEXTURE_2D);*/
	}
}
