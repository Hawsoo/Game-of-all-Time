package us.teamgreat.gameofalltime;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.engine.Camera;
import us.teamgreat.gameofalltime.gameobject.room.BetaRoom;
import us.teamgreat.gameofalltime.gameobject.room.Room;
import us.teamgreat.gameofalltime.inputhandler.InputHandler;
import us.teamgreat.gameofalltime.inputhandler.KeyboardInput;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Runs the main game loop.
 * @author Timothy Bennett
 *
 */
public class Game
{
	public static String title;
	public static ByteBuffer[] icons;
	public static boolean isFull;
	public static boolean isVsync;
	
	public float fps = 60;
	public Dimension contextsize = new Dimension();
	
	public Camera tiltxcamera;
	public Camera tiltycamera;
	public InputHandler input;
	public Room room;
	
	// Flags
	private boolean running = true;
	private Dimension size = new Dimension();
	
	/**
	 * Initializes game.
	 */
	public Game()
	{
		// Setup variables
		title = "The Game of All Time -vAlpha";
		isFull = false;
		isVsync = true;
		
		try
		{
			// Create Window Icons
			icons = new ByteBuffer[]
					{
					Resources.importImageToByteBuffer(ImageIO.read(Game.class.getResource(Resources.RESOURCES_DIR + "icon_16x16.png"))),
					Resources.importImageToByteBuffer(ImageIO.read(Game.class.getResource(Resources.RESOURCES_DIR + "icon_32x32.png"))),
					Resources.importImageToByteBuffer(ImageIO.read(Game.class.getResource(Resources.RESOURCES_DIR + "icon_48x48.png"))),
					Resources.importImageToByteBuffer(ImageIO.read(Game.class.getResource(Resources.RESOURCES_DIR + "icon_64x64.png"))),
					Resources.importImageToByteBuffer(ImageIO.read(Game.class.getResource(Resources.RESOURCES_DIR + "icon_128x128.png"))),
					Resources.importImageToByteBuffer(ImageIO.read(Game.class.getResource(Resources.RESOURCES_DIR + "icon_256x256.png")))
					};
			
		} catch (IOException e) {}
		
		// Setup Display
		setupDisplay(title, icons, isFull, isVsync);
		
		try
		{
			Display.create();
			Mouse.create();
			Keyboard.create();
			Controllers.create();
		} catch (LWJGLException e) {}
		
		// Setup OpenGL context
		setupOpenGLContext();
		
		// Setup game
		tiltxcamera = new Camera(new Vector2f(0, 0), new Vector2f(35, 0), this);
		tiltycamera = new Camera(new Vector2f(0, 0), new Vector2f(0, -45), this);
		input = new KeyboardInput();
//		input = new ControllerInput();
		room = new BetaRoom(this);
		
		Resources.loadTextures();
	}
	
	/**
	 * Sets up the display for opengl
	 * to sit in.
	 * @param title
	 * @param icons
	 * @param isFull
	 * @param isVsync
	 */
	private void setupDisplay(String title, ByteBuffer[] icons, boolean isFull, boolean isVsync)
	{
		try
		{
			// Set size of window based on isFullscreen
			if (isFull)
			{
				Display.setDisplayMode(Resources.FULLSCREEN_SIZE);
				size.width = Resources.FULLSCREEN_SIZE.getWidth();
				size.height = Resources.FULLSCREEN_SIZE.getHeight();
				Display.setFullscreen(true);
			}
			else
			{
				Display.setDisplayMode(Resources.DEFAULT_SIZE);
				size.width = Resources.DEFAULT_SIZE.getWidth();
				size.height = Resources.DEFAULT_SIZE.getHeight();
				Display.setFullscreen(false);
			}
		} catch (LWJGLException e) {}
		
		// Set other window settings
		Display.setTitle(title);
		Display.setIcon(icons);
		Display.setResizable(true);
		Display.setVSyncEnabled(isVsync);
		Display.setInitialBackground(0, 0, 0);
	}
	
	/**
	 * Creates opengl context.
	 */
	private void setupOpenGLContext()
	{
		// Setup OpenGL for 2D pixel matrices
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		float heightRatio = Resources.DEFAULT_SIZE.getHeight() / (float)Display.getHeight();
		contextsize.width = (int)(Display.getWidth() * heightRatio);
		contextsize.height = (int)(Display.getHeight() * heightRatio);
		
		glOrtho(0, contextsize.width, 0, contextsize.height, -500, 500);
		glMatrixMode(GL_MODELVIEW);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		// Update flags
		size.width = Display.getWidth();
		size.height = Display.getHeight();
	}
	
	/**
	 * Runs main game loop.
	 */
	public void run()
	{
		// Game Loop
		while (running)
		{
			{
				///////////////
				// GET INPUT //
				///////////////
				
				Mouse.poll();
				input.pumpInput();
				
				////////////
				// UPDATE //
				////////////
				
				room.update();
				
				////////////
				// RENDER //
				////////////
				
				// Clear Buffers
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				glLoadIdentity();
				
				// Enter renderpass
				glPushMatrix();
				{
					// Render
					room.render();
				}
				// Exit renderpass
				glPopMatrix();
			}
			
			// Check if close is requested
			if (Display.isCloseRequested()) running = false;
			
			// Reiterate OpenGL (if changed)
			if (size.width != Display.getWidth() || size.height != Display.getHeight()) setupOpenGLContext();
			
			// Update Display
			Display.update();
			Display.sync((int) fps);
		}
		
		// Dispose and Cleanup
		Display.destroy();
		Mouse.destroy();
		Keyboard.destroy();
		Controllers.destroy();
	}
	
	/**
	 * Shuts down the game.
	 */
	public void exit()
	{
		running = false;
	}
}
