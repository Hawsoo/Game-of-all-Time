package us.teamgreat;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.isoleveleditor.resources.LE_Resources;
import us.teamgreat.isoleveleditor.ui.PropertiesWindow;
import us.teamgreat.isoleveleditor.ui.ViewerWindow;

/**
 * Driver class of game/app.
 * @author Timothy Bennett
 *
 */
public class MainClass
{
	public static final boolean LEVEL_EDITOR = false;
	public static final boolean DEBUG = true;
	
	/**
	 * Runs the program.
	 * @param argv
	 */
	public static void main(String[] argv)
	{
		if (LEVEL_EDITOR)
		{
			// Launch level editor
			ViewerWindow window = new ViewerWindow();
			
			PropertiesWindow frame = new PropertiesWindow();
			frame.pack();
			frame.setVisible(true);
			
			LE_Resources.init();
			
			window.run();
		}
		else
		{
			// Launch game
			new Game().run();
		}
	}
	
	/**
	 * Exits the app.
	 */
	public static void exit()
	{
		System.exit(0);
	}
}
