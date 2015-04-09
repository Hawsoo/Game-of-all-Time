package us.teamgreat.gameofalltime.engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.Game;

/**
 * Controls transformations.
 * @author Timothy Bennett
 *
 */
public class Camera
{
	public Vector2f pos;
	public Vector2f angle;
	
	private Game game;
	
	/**
	 * Creates the camera.
	 */
	public Camera(Game game)
	{
		this.game = game;
		
		pos = new Vector2f(0, 0);
		angle = new Vector2f(35, -45);
	}
	
	/**
	 * Translate the view.
	 */
	public void translateView()
	{
		GL11.glTranslatef(-pos.x + (game.contextsize.width / 2), -pos.y + (game.contextsize.height / 2), 0);
	}
	
	/**
	 * Rotate the view.
	 */
	public void rotateView()
	{
		GL11.glRotatef(-angle.x, 1, 0, 0);
		GL11.glRotatef(-angle.y, 0, 1, 0);
	}
	
	/**
	 * Undoes the translation.
	 */
	public void undoTranslation()
	{
		GL11.glTranslatef(-(-pos.x + (game.contextsize.width / 2)), -(-pos.y + (game.contextsize.height / 2)), 0);
	}
	
	/**
	 * Undoes the rotation.
	 */
	public void undoRotation()
	{
		GL11.glRotatef(angle.y, 0, 1, 0);
		GL11.glRotatef(angle.x, 1, 0, 0);
	}
}
