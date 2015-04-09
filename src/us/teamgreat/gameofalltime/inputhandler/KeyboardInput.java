package us.teamgreat.gameofalltime.inputhandler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Interprets keyboard input.
 * @author Timothy Bennett
 *
 */
public class KeyboardInput implements InputHandler
{
	// Keybindings
	public int left, right, up, down,
			action, item1, item2, item3;
	
	/**
	 * Creates keybindings.
	 */
	public KeyboardInput()
	{
		left = Keyboard.KEY_LEFT;
		right = Keyboard.KEY_RIGHT;
		up = Keyboard.KEY_UP;
		down = Keyboard.KEY_DOWN;
		
		action = Keyboard.KEY_SPACE;
		item1 = Keyboard.KEY_C;
		item2 = Keyboard.KEY_X;
		item3 = Keyboard.KEY_Z;
	}
	
	@Override
	public void pumpInput()
	{
		// Interpret keyboard input
		Resources.joy_position = new Vector2f();
		if (Keyboard.isKeyDown(left)) Resources.joy_position.x = -1;
		if (Keyboard.isKeyDown(right)) Resources.joy_position.x = 1;
		if (Keyboard.isKeyDown(up)) Resources.joy_position.y = -1;
		if (Keyboard.isKeyDown(down)) Resources.joy_position.y = 1;

		Resources.action = Keyboard.isKeyDown(action);
		Resources.item1 = Keyboard.isKeyDown(item1);
		Resources.item2 = Keyboard.isKeyDown(item2);
		Resources.item3 = Keyboard.isKeyDown(item3);
	}
}
