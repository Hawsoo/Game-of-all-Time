package us.teamgreat.isoleveleditor.resources;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import us.teamgreat.MainClass;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.isoleveleditor.engine.entity.Entities;
import us.teamgreat.isoleveleditor.engine.entity.EntityTypes;

/**
 * Holds the resources for the app.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class Resources
{
	public static final String NAME = "Isometric Level Editor - for \"Game of all Time\"";
	public static final String RESOURCE_DIR = "/us/teamgreat/isoleveleditor/resources/";
	
	public static final float Z_RATIO = 0.65f;
	public static final float GRID_WIDTH = 48;
	public static final float GRID_HEIGHT = GRID_WIDTH * Z_RATIO;
	
	public static final WindowAdapter DEFAULT_LISTENER = new WindowAdapter()
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			// Exit the application
			MainClass.exit();
		}
	};
	
	public static boolean showYdepth = false;
	
	public static boolean showAsthetics = true;
	public static boolean showWalls = true;
	public static boolean showEvents = true;
	
	public static Entities currentmodel;
	public static float yVal;
	
	public static EntityTypes currenttype = EntityTypes.ASTHETIC_ENTITY;

	public static int entityEnumCounter = 0;
	public static int entityTypeEnumCounter = 0;
	
	// Sprites
	public static Sprite GND_SILHOUETTE = new Sprite(24, 50, "block_silhouette.png");
	public static Sprite GND_SHADOW = new Sprite(24, 50, "block_backdrop.png");
	
	public static Sprite GND_REG = new Sprite(24, 50, "dirt_block.png");
	
	/**
	 * Initializes resources that are unable
	 * to be initialized during the beginning
	 * of the program.
	 * HERESTO add sprites, etc.
	 */
	public static void init()
	{
		// Import sprites
		Sprite.initSprites();
	}
}
