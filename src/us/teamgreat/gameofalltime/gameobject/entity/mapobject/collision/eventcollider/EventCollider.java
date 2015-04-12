package us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.eventcollider;

import org.lwjgl.opengl.GL11;

import us.teamgreat.MainClass;
import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Generic wall for events.
 * @author Timothy Bennett
 *
 */
public abstract class EventCollider extends Collision
{
	private Runnable eventscript;
	
	/**
	 * Creates an event collider.
	 * @param x
	 * @param y
	 * @param z
	 * @param game
	 * @param eventscript
	 */
	public EventCollider(double x, double y, double z, Game game, Runnable eventscript)
	{
		super(x, y, z, Resources.block_beta, Collision.TYPE_EVENT_COLLIDER, game);
		this.eventscript = eventscript;
	}
	
	/**
	 * Runs the event script.
	 */
	public void triggerScript()
	{
		eventscript.run();
	}
	
	@Override
	public void render()
	{
		// Render
		if (MainClass.DEBUG)
		{
			GL11.glColor3f(1, 0.47f, 0);
			super.render();
		}
	}
}
