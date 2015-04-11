package us.teamgreat.gameofalltime.engine;

import java.io.IOException;
import java.util.ArrayList;

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
	private String name;
	
	private static ArrayList<Sprite> unfinishedSprites = new ArrayList<Sprite>();
	
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
		this.name = name;
		
		unfinishedSprites.add(this);
	}
	
	/**
	 * Loads the texture for the sprite.
	 * @return
	 */
	public static void initSprites()
	{
		// Load each unfinished sprite
		for (Sprite sprite : unfinishedSprites)
		{
			try
			{
				// Load texture
				sprite.texture = TextureLoader.getTexture("PNG", Sprite.class.getResourceAsStream(Resources.RESOURCES_DIR + sprite.name));
			} catch (IOException e) {}
		}
		
		// Clear list
		unfinishedSprites.clear();
	}
	
	/**
	 * Returns the texture of the sprite.
	 * @return
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	/**
	 * Gets the xoff.
	 * @return
	 */
	public int getXoff()
	{
		return xoff;
	}
	
	/**
	 * Gets the yoff.
	 * @return
	 */
	public int getYoff()
	{
		return yoff;
	}
	
	/**
	 * Gets the width.
	 * @return
	 */
	public int getWidth()
	{
		return texture.getImageWidth();
	}
	
	/**
	 * Gets the height.
	 * @return
	 */
	public int getHeight()
	{
		return texture.getImageHeight();
	}
	
	
	
	/**
	 * Render sprite.
	 * @param x
	 * @param y
	 */
	public void render(int x, int y)
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		texture.bind();
		
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
