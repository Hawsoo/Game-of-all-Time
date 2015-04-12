package us.teamgreat.isoleveleditor.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.MainClass;
import us.teamgreat.isoleveleditor.engine.entity.LE_Entity;
import us.teamgreat.isoleveleditor.engine.entity.LE_EntityTypes;
import us.teamgreat.isoleveleditor.resources.LE_Resources;

/**
 * Manages rendering of blocks in their certain order.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class ViewerWindow
{
	public static Point windowpos = new Point();
	public static Vector2f offset = new Vector2f();
	
	public static boolean saveAsRequired = true;
	public static String savePath = null;
	
	public static ArrayList<LE_Entity> entities;
	public static ArrayList<LE_Entity> selectedEntities;
	
	// Flags
	public static boolean sliderchanged = false;
	public static boolean layerchanged = false;
	private boolean running = true;
	private Dimension size = new Dimension();
	
	private int prevMouseX, prevMouseY;
	
	/**
	 * Creates and sets up OpenGL display.
	 */
	public ViewerWindow()
	{
		try
		{
			// Setup Display
			Display.setDisplayMode(new DisplayMode(1024, 576));
			Display.setTitle(LE_Resources.NAME);
			Display.setResizable(true);
			Display.setVSyncEnabled(true);
			Display.setInitialBackground(0, 0.5f, 0.75f);
			
			Display.create();
			Mouse.create();
		} catch (LWJGLException e) {}
		
		// Setup OpenGL context
		setupOpenGLContext();

		entities = new ArrayList<LE_Entity>();
		selectedEntities = new ArrayList<LE_Entity>();
		
		// Init flags
		windowpos.x = Display.getX();
		windowpos.y = Display.getY();
		
		prevMouseX = Mouse.getX();
		prevMouseY = Mouse.getY();
	}
	
	/**
	 * Clears entities.
	 */
	public static void clearEntities()
	{
		entities.clear();
		selectedEntities.clear();
	}
	
	/**
	 * Runs the game loop.
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
				Keyboard.poll();
				
				////////////
				// UPDATE //
				////////////
				
				// Select/Create object (left mouse)
				if (Mouse.isButtonDown(0))
				{
					int x = (int)(Mouse.getX() - (size.width / 2) + offset.x);
					int z = (int)(Mouse.getY() - (size.height / 2) + offset.y);
					
					// Calculate where click was
					int count = 0;
					int increment = 0;
					if (z >= 0)
					{
						while (count < z)
						{
							count += (LE_Resources.GRID_HEIGHT / 2);
							increment++;
						}
					}
					else
					{
						while (count - (LE_Resources.GRID_HEIGHT / 2) > z)
						{
							count -= (LE_Resources.GRID_HEIGHT / 2);
							increment++;
						}
					}
					
					// Snap to iso grid
					z = count;
					while (x % LE_Resources.GRID_WIDTH != 0) x--;
					if (increment % 2 == 0) x += (LE_Resources.GRID_WIDTH / 2);
					
					// Check if collision
					boolean colliding = false;
					int i = entities.size() - 1;
					for (; i >= 0; i--)
					{
						if (entities.get(i).isColliding(new Point(x, z)))
						{
							colliding = true;
							break;
						}
					}
					
					// Select object
					if (colliding)
					{
						if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
							// Clear list
							selectedEntities.clear();
						
						// Add selected object
						selectedEntities.add(entities.get(i));
					}
					// Create object
					else
					{
						// Create object
						entities.add(new LE_Entity(LE_Resources.currentmodel, new Vector3f(x, LE_Resources.yVal, z)));
					}
				}
				
				// Drag screen (right mouse)
				if (Mouse.isButtonDown(1))
				{
					// Transform
					offset.x -= Mouse.getX() - prevMouseX;
					offset.y -= Mouse.getY() - prevMouseY;
				}
				
				// Delete (DEL button)
				if (Keyboard.isKeyDown(Keyboard.KEY_DELETE))
				{
					for (int i = entities.size() - 1; i >= 0; i--)
					{
						LE_Entity entity = entities.get(i);
						
						// Find if it is in selected entities
						for (int i2 = selectedEntities.size() - 1; i2 >= 0; i2--)
						{
							LE_Entity entity2 = selectedEntities.get(i2);
							if (entity == entity2)
							{
								// Delete both objects
								entities.remove(i);
								selectedEntities.remove(i2);
								break;
							}
						}
					}
				}
				
				// Update lagging
				prevMouseX = Mouse.getX();
				prevMouseY = Mouse.getY();
				
				// Update selected objects
				if (sliderchanged)
				{
					sliderchanged = false;
					
					// Change selected objects
					for (LE_Entity entity : selectedEntities)
					{
						entity.setY(LE_Resources.yVal);
					}
				}
				
				if (layerchanged)
				{
					layerchanged = false;
					
					// Change selected objects
					for (LE_Entity entity : selectedEntities)
					{
						entity.type = LE_Resources.currenttype;
					}
				}
				
				////////////
				// RENDER //
				////////////
				
				// Clear Buffers
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glLoadIdentity();
				
				// Enter renderpass
				GL11.glPushMatrix();
				{
					// Apply transformations
					GL11.glTranslatef((size.width / 2) - offset.x, (size.height / 2) - offset.y, 0);
					
					// Render
					render(entities);
				}
				// Exit renderpass
				GL11.glPopMatrix();
			}
			
			// Check if close is requested
			if (Display.isCloseRequested()) running = false;
			
			// Reiterate OpenGL (if changed)
			if (size.width != Display.getWidth() ||
					size.height != Display.getHeight())
				setupOpenGLContext();
			
			// Update Display
			Display.update();
			Display.sync(30);
		}
		
		// Dispose and Cleanup
		Display.destroy();
		Mouse.destroy();
		
		// Exit
		MainClass.exit();
	}
	
	
	/**
	 * Creates opengl context.
	 */
	private void setupOpenGLContext()
	{
		// Setup OpenGL for 2D pixel matrices
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		// Update flags
		size.width = Display.getWidth();
		size.height = Display.getHeight();
	}
	
	
	/**
	 * Renders the entities in correct z-order.
	 */
	private void render(ArrayList<LE_Entity> objects)
	{
		int j;			// The number of items sorted so far
		LE_Entity key;		// The item to be inserted
		int i;
		
		LE_Entity[] entities = objects.toArray(new LE_Entity[objects.size()]);
		
		// Execute insertion sort
		for (j = 1; j < entities.length; j++)			// Start with 1 (not 0)
		{
			key = entities[j];
			for (i = j - 1; (i >= 0) && (entities[i].getPosition().z < key.getPosition().z); i--)			// Smaller values are moving up
			{
				entities[i + 1] = entities[i];
			}
			entities[i + 1] = key;						// Put the key in its proper location
		}
		
		// Re-input array
		ViewerWindow.entities = new ArrayList<LE_Entity>(Arrays.asList(entities));
		
		// Render the ordered list
		for (LE_Entity entity : ViewerWindow.entities)
		{
			boolean render = false;
			LE_EntityTypes type = entity.type;
			
			// Check if type is wanted to be rendered
			if (type == LE_EntityTypes.ASTHETIC_ENTITY)
			{
				if (LE_Resources.showAsthetics)
					render = true;
			}
			else if (type == LE_EntityTypes.WALL_ENTITY)
			{
				if (LE_Resources.showWalls)
					render = true;
			}
			else if (type == LE_EntityTypes.EVENT_ENTITY)
			{
				if (LE_Resources.showEvents)
					render = true;
			}
			
			// Render if allowed
			if (render)
			{
				// Differentiate entities
				if (entity.type == LE_EntityTypes.ASTHETIC_ENTITY)
					// Full color
					GL11.glColor3f(1, 1, 1);
				else if (entity.type == LE_EntityTypes.WALL_ENTITY)
					// Dark color
					GL11.glColor3f(0.5f, 0.5f, 0.5f);
				else if (entity.type == LE_EntityTypes.EVENT_ENTITY)
					// Orange Hue
					GL11.glColor3f(1, 0.47f, 0);
				
				// Render shadow
				Point shadowpos = entity.getXYZ2D(false);
				entity.getModel().getShadow().render(shadowpos.x, shadowpos.y);
				
				// Render entity
				entity.render(LE_Resources.showYdepth);
				
				// Check if selected
				for (LE_Entity entity2 : selectedEntities)
				{
					if (entity == entity2)
					{
						Point pos = entity.getXYZ2D(LE_Resources.showYdepth);
						
						// Render silhouette
						GL11.glColor3f(1, 1, 1);
						entity.getModel().getSilhouette().render(pos.x, pos.y);
					}
				}
			}
		}
	}
}
