package us.teamgreat.gameofalltime.engine;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import us.teamgreat.gameofalltime.resources.Resources;

/**
 * A texture with abilities.
 * @author Timothy Bennett
 *
 */
public class Sprite
{
	private Texture texture;
	private int xoff, yoff;
	
	/**
	 * Automatically loads from resource file.
	 * @param xoff
	 * @param yoff
	 * @param name
	 */
	public Sprite(int xoff, int yoff, String name)
	{
		this.xoff = xoff;
		this.yoff = yoff;
		
		try
		{
			// Load texture
			texture = TextureLoader.getTexture("PNG", getClass().getResourceAsStream(Resources.RESOURCES_DIR + name));
		} catch (IOException e) {}
	}
	
	/**
	 * Render sprite.
	 * @param x
	 * @param y
	 */
	public void render(int x, int y)
	{
		texture.bind();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(1, 1, 1);
		
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
		
		// Apply transformations
		GL11.glTranslatef(x - xoff, y - yoff, 0);
		{
			// Draw texture
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2f(0, texture.getImageHeight());
				
				GL11.glTexCoord2d(texture.getWidth(), 0);
				GL11.glVertex2f(texture.getImageWidth(), texture.getImageHeight());
				
				GL11.glTexCoord2d(texture.getWidth(), texture.getHeight());
				GL11.glVertex2f(texture.getImageWidth(), 0);
				
				GL11.glTexCoord2d(0, texture.getHeight());
				GL11.glVertex2f(0, 0);
			}
			GL11.glEnd();
		}
		// Undo transformations
		GL11.glTranslatef(-(x - xoff), -(y - yoff), 0);
	}
}
