package us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Animation;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.MapObject;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.PathNode;
import us.teamgreat.gameofalltime.gameobject.room.Room;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * The guard that follows a set path, but
 * can get possessed.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class Guard extends Puppet
{
	private static final int COMPENSATION = 4;
	
	private static final int MOVEMENT_FRAMES = 15;
	private int framesWaited = 0;
	
	private ArrayList<PathNode> pathnodes;
	private Vector2f nextLocation;

	/**
	 * Creates a basic guard.
	 * @param x
	 * @param y
	 * @param z
	 * @param collisions
	 * @param game
	 */
	public Guard(int x, int y, int z, int direction, Room room, Game game)
	{
		super(x, y, z, 5, 0.35, direction, room, game);
		this.isPossessed = false;
		
		// Find all path nodes
		pathnodes = new ArrayList<PathNode>();
		for (MapObject object : room.objects)
		{
			if (object instanceof PathNode)
				pathnodes.add((PathNode) object);
		}
		
		// Find first location
		nextLocation = findNextLocation();
		calibrateMovement();
	}
	
	/**
	 * Sets up the movement.
	 */
	private void calibrateMovement()
	{
		// Goto point
		double dx = nextLocation.x - x;
		double dz = nextLocation.y - z;
		
//		double dIncrement = Math.sqrt(dx * dx + dz * dz) / MOVEMENT_FRAMES;
		hspeed = dx / /*dIncrement*/ MOVEMENT_FRAMES;	// Times a constant
		vspeed = dz / /*dIncrement*/ MOVEMENT_FRAMES;	// Times a constant
		
		// Reset timer
		framesWaited = 0;
	}
	
	/**
	 * Finds next location based on 
	 * @return
	 */
	private Vector2f findNextLocation()
	{
		// Look in next area direction
		boolean found = false;
		while (!found)
		{
			// Fix cardinal directions
			switch (direction)
			{
			case DIR_N:
				direction = DIR_NE;
				break;
				
			case DIR_E:
				direction = DIR_SE;
				break;
				
			case DIR_S:
				direction = DIR_SW;
				break;
				
			case DIR_W:
				direction = DIR_NW;
				break;
			}
			
			// Get approx. point
			Vector2f nextLoc = new Vector2f();
			switch (direction)
			{
			case DIR_NE:
				nextLoc.x = (float) (x + Resources.GRID_WIDTH / 2) + COMPENSATION;
				nextLoc.y = (float) (y + Resources.GRID_HEIGHT / 2);
				break;

			case DIR_SE:
				nextLoc.x = (float) (x + Resources.GRID_WIDTH / 2) + COMPENSATION;
				nextLoc.y = (float) (y - Resources.GRID_HEIGHT / 2);
				break;

			case DIR_SW:
				nextLoc.x = (float) (x - Resources.GRID_WIDTH / 2) + COMPENSATION;
				nextLoc.y = (float) (y - Resources.GRID_HEIGHT / 2);
				break;

			case DIR_NW:
				nextLoc.x = (float) (x - Resources.GRID_WIDTH / 2) + COMPENSATION;
				nextLoc.y = (float) (y + Resources.GRID_HEIGHT / 2);
				break;
			}
			
			// Calculate where location was
			/*int count = 0;
			int increment = 0;
			if (nextLoc.y >= 0)
			{
				while (count < nextLoc.y)
				{
					count += (Resources.GRID_HEIGHT / 2);
					increment++;
				}
			}
			else
			{
				while (count - (Resources.GRID_HEIGHT / 2) > nextLoc.y)
				{
					count -= (Resources.GRID_HEIGHT / 2);
					increment++;
				}
			}*/
			
			// Snap to iso grid
//			nextLoc.y = count;
//			while (nextLoc.x % Resources.GRID_WIDTH != 0) nextLoc.x--;
//			if (increment % 2 == 0) nextLoc.x += (Resources.GRID_WIDTH / 2);
			
			// If there is a pathnode here
			for (PathNode pathNode : pathnodes)
			{
				if (pathNode.x == nextLoc.x && pathNode.z == nextLoc.y)
				{
					// This is the right one
					return nextLoc;
				}
			}
			
			// Guess not...
			// Turn clockwise
			switch (direction)
			{
			case DIR_NE:
				direction = DIR_SE;
				break;

			case DIR_SE:
				direction = DIR_SW;
				break;

			case DIR_SW:
				direction = DIR_NW;
				break;

			case DIR_NW:
				direction = DIR_NE;
				break;
			}
		}
		
		return null;
	}
	
	@Override
	public void normalUpdate()
	{
		// Move
		move();
		
		// Increment timer
		framesWaited++;
		
		// Test if done
		if (framesWaited >= MOVEMENT_FRAMES)
		{
			// Set self to location
			x = nextLocation.x;
			z = nextLocation.y;
			
			// Find first location
			nextLocation = findNextLocation();
			calibrateMovement();
		}
	}

	@Override
	public void render()
	{
		// Get correct guard animation
		Animation ani = null;
		switch (direction)
		{
		case DIR_N:
			ani = Resources.guard_ani_n;
			break;
			
		case DIR_NE:
			ani = Resources.guard_ani_ne;
			break;
			
		case DIR_E:
			ani = Resources.guard_ani_e;
			break;
			
		case DIR_SE:
			ani = Resources.guard_ani_se;
			break;
			
		case DIR_S:
			ani = Resources.guard_ani_s;
			break;
			
		case DIR_SW:
			ani = Resources.guard_ani_sw;
			break;
			
		case DIR_W:
			ani = Resources.guard_ani_w;
			break;
			
		case DIR_NW:
			ani = Resources.guard_ani_nw;
			break;
		}
		
		// Render
		GL11.glColor3f(1, 1, 1);
		ani.render((int)x, (int)(z + y));
	}
}
