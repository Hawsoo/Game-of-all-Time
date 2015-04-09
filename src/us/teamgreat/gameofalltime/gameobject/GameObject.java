package us.teamgreat.gameofalltime.gameobject;

/**
 * Basic game object.
 * @author Timothy Bennett
 *
 */
public interface GameObject
{
	/**
	 * Updates the gameobject.
	 */
	public void update();
	
	/**
	 * Renders the gameobject.
	 */
	public void render();
}
