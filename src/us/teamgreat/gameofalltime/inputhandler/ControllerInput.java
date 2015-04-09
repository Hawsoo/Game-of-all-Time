package us.teamgreat.gameofalltime.inputhandler;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Interprets controller input.
 * @author Timothy Bennett
 *
 */
public class ControllerInput implements InputHandler
{
	// Bindings
	private Controller joystick;
	private static final float DEADZONE = 0.5f;		// 50% radius deadzone
	
	/**
	 * Create controller input.
	 */
	public ControllerInput()
	{
		// Find controller
		String controllerName = "";
		boolean controllerfound = false;
		for (net.java.games.input.Controller controller : net.java.games.input.ControllerEnvironment.getDefaultEnvironment().getControllers())
		{
			// If controller is a gamepad...
			if (controller.getType() == net.java.games.input.Controller.Type.GAMEPAD)
			{
				// Add controller
				System.out.println("> Controller connected:\n\t" + (controllerName = controller.getName()));
				
				// Break
				controllerfound = true;
				break;
			}
		}
		
		// Find the lwjgl register of the controller
		if (controllerfound)
		{
			// Connect controller thru lwjgl controller
			for (int i = 0; i < Controllers.getControllerCount(); i++)
			{
				Controller c = Controllers.getController(i);
				if (c.getName().equals(controllerName))
				{
					joystick = c;
					break;
				}
			}
		}
	}
	
	@Override
	public void pumpInput()
	{
		joystick.poll();
		Resources.joy_position = new Vector2f();
		
		// Analog joysticks (first two) input, if any
		for (int i = 0; i < Math.min(joystick.getAxisCount(), 2); i++)
		{
			// If even number axis, then it is vertical
			if (i % 2 == 0)
			{
				Resources.joy_position.y = joystick.getAxisValue(i);
			}
			// If odd number axis, then it is horizontal
			else
			{
				Resources.joy_position.x = joystick.getAxisValue(i);
			}
		}
		
		// D-pad input
		if (Math.abs(joystick.getPovX()) >= DEADZONE)
		{
			Resources.joy_position.x = joystick.getPovX();
		}
		
		if (Math.abs(joystick.getPovY()) >= DEADZONE)
		{
			Resources.joy_position.y = joystick.getPovY();
		}
		
		// Start handling input if joystick is out of initial buggy value
//		if (xAxis != -1 && yAxis != -1) handleInput = true;
//		
//		if (handleInput)
//		{
//			// Update axes inputs
//			left = leftInput;
//			right = rightInput;
//			up = upInput;
//			down = downInput;
//		}
		
		// Update button inputs
		Resources.action = joystick.isButtonPressed(0);			// 'A' button
		Resources.item1 = joystick.isButtonPressed(1);			// 'B' button
		Resources.item2 = joystick.isButtonPressed(2);			// 'X' button
		Resources.item3 = joystick.isButtonPressed(3);			// 'Y' button
	}
}
