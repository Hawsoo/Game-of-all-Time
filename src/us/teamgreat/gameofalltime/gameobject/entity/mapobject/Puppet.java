package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import java.awt.Rectangle;
import java.util.ArrayList;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.eventcollider.EventCollider;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Implementation of a puppet:
 * to be able to be controlled.
 * 
 * Assumed that puppets are rect. prisms.
 * @author Timothy Bennett
 *
 */
public abstract class Puppet extends MapObject
{
	public static final int DIR_N = 8;
	public static final int DIR_NE = 9;
	public static final int DIR_E = 6;
	public static final int DIR_SE = 3;
	public static final int DIR_S = 2;
	public static final int DIR_SW = 1;
	public static final int DIR_W = 4;
	public static final int DIR_NW = 7;
	
	public boolean isPossessed = false;
	public int direction;
	
	protected ArrayList<Collision> collisions;
	
	protected double maxspeed = 5;
	protected double multiplier = 0.35;
	
	protected double basesize = 32;
	
	private int climbheight = 3;
	
	/**
	 * Creates a puppet.
	 * @param x
	 * @param y
	 * @param z
	 * @param game
	 */
	public Puppet(int x, int y, int z, double maxspeed, double accelerationMult, int direction, ArrayList<Collision> collisions, Game game)
	{
		super(x, y, z, game);
		
		this.maxspeed = maxspeed;
		this.multiplier = accelerationMult;
		this.direction = direction;
		this.collisions = collisions;
	}

	/**
	 * Does puppet updating.
	 */
	public void update()
	{
		// Checkout if not possessed
		if (!isPossessed)
		{
			normalUpdate();
			return;
		}
		
		// Get input
		double dx = Resources.joy_position.x * multiplier;
		double dy = -Resources.joy_position.y * multiplier;
		
		// Apply friction
		if (dx == 0)
		{
			// Decrement hspeed
			if (hspeed > 0)
			{
				hspeed -= multiplier;
				if (hspeed < 0) hspeed = 0;
			}
			// Increment hspeed
			else if (hspeed < 0)
			{
				hspeed += multiplier;
				if (hspeed > 0) hspeed = 0;
			}
		}
		
		if (dy == 0)
		{
			// Decrement hspeed
			if (vspeed > 0)
			{
				vspeed -= multiplier;
				if (vspeed < 0) vspeed = 0;
			}
			// Increment hspeed
			else if (vspeed < 0)
			{
				vspeed += multiplier;
				if (vspeed > 0) vspeed = 0;
			}
		}
		
		// Apply to moving vars
		hspeed += dx;
		vspeed += dy;
		
		// Restrict speed
		if (Math.abs(hspeed) > maxspeed) hspeed = maxspeed * Math.signum(hspeed);
		if (Math.abs(vspeed) > maxspeed) vspeed = maxspeed * Math.signum(vspeed);
		
		// Move
		move();
	}
	
	/**
	 * Moves the puppet object.
	 */
	public void move()
	{
		// Update facing direction
		if (hspeed < 0 && vspeed < 0)
			direction = DIR_SW;
		else if (hspeed > 0 && vspeed < 0)
			direction = DIR_SE;
		else if (hspeed > 0 && vspeed > 0)
			direction = DIR_NE;
		else if (hspeed < 0 && vspeed > 0)
			direction = DIR_NW;
		else if (hspeed < 0)
			direction = DIR_W;
		else if (hspeed > 0)
			direction = DIR_E;
		else if (vspeed < 0)
			direction = DIR_S;
		else if (vspeed > 0)
			direction = DIR_N;
		
		// Move
		for (int i = 0; i < Math.ceil(Math.abs(hspeed)); i++)
		{
			int moveamount = (int) Math.signum(hspeed);
			
			// Check for collision
			if (isColliding((int) x + moveamount, (int) y, (int) z))
			{
				boolean solved = false;
				for (int j = 1; j <= climbheight; j++)
				{
					// Check if there is no collision up or down
					if (!isColliding((int) x + moveamount, (int) y, (int) z - j))
					{
						// Move down
						x += moveamount;
						z -= j;
						
						solved = true;
						break;
					}
					else if (!isColliding((int) x + moveamount, (int) y, (int) z + j))
					{
						// Move up
						x += moveamount;
						z += j;
						
						solved = true;
						break;
					}
				}
				
				if (!solved)
				// Stop for it is a wall
					hspeed = 0;
			}
			else
				// Move along
				x += moveamount;
		}
		
		for (int i = 0; i < Math.ceil(Math.abs(vspeed)); i++)
		{
			int moveamount = (int) Math.signum(vspeed);
			
			// Check for collision
			if (isColliding((int) x, (int) y, (int) z + moveamount))
			{
				boolean solved = false;
				for (int j = 1; j <= climbheight; j++)
				{
					// Check if there is no collision up or down
					if (!isColliding((int) x - j, (int) y, (int) z + moveamount))
					{
						// Move left
						x -= j;
						z += moveamount;
						
						solved = true;
						break;
					}
					else if (!isColliding((int) x + j, (int) y, (int) z + moveamount))
					{
						// Move right
						x += j;
						z += moveamount;
						
						solved = true;
						break;
					}
				}
				
				if (!solved)
				// Stop for it is a wall
					vspeed = 0;
			}
			else
				// Move along
				z += moveamount;
		}
	}
	
	/**
	 * Gets collision with puppet and
	 * blocks of other objects.
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean isColliding(int x, int y, int z)
	{
		// Create puppet bounds
		Rectangle bounds = new Rectangle((int)(x - (basesize / 2)), (int)(z - (basesize / 2)), (int)basesize, (int)basesize);
		
		// Check for collisions
		for (Collision collision : collisions)
		{
			if (checkCollision(x, y, z, bounds, collision))
			{
				// Return a solid collision
				if (collision.getType() == Collision.TYPE_WALL)
				{
					return true;
				}
				// Execute event script
				else if (collision.getType() == Collision.TYPE_EVENT_COLLIDER)
				{
					((EventCollider)collision).triggerScript();
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks the collision for a single Ground.
	 * @param x
	 * @param y
	 * @param z
	 * @param bounds
	 * @param ground
	 * @return
	 */
	private boolean checkCollision(int x, int y, int z, Rectangle bounds, Collision collision)
	{
		// Check out (if not within y bounds)
		/*if (!(ground.y - Resources.BLOCK_SIZE < y + height && ground.y > y)) return false;
		
		// Get width and height of block
		double width = Resources.BLOCK_SIZE * Math.cos(Math.toRadians(45));
		double height = Resources.BLOCK_SIZE * Math.sin(Math.toRadians(45));

		// Check out (if not within basic box bounds) HERESTO play around with the collision values
		if (!new Rectangle((int)(ground.x - (width / 2)), (int)(ground.z - height), (int)(width), (int)(height / 2)).intersects(bounds)) return false;
		
		// Create collision geometry
		Line2D.Double lineNE = new Line2D.Double(ground.x, ground.z - (height / 2), ground.x + (width / 2), ground.z - (height * 3 / 4));
		Line2D.Double lineNW = new Line2D.Double(ground.x, ground.z - (height / 2), ground.x - (width / 2), ground.z - (height * 3 / 4));
		Line2D.Double lineSE = new Line2D.Double(ground.x, ground.z - height, ground.x + (width / 2), ground.z - (height * 3 / 4));
		Line2D.Double lineSW = new Line2D.Double(ground.x, ground.z - height, ground.x - (width / 2), ground.z - (height * 3 / 4));
		
		// Test if collides
		if (lineNE.intersects(bounds)) return true;
		if (lineNW.intersects(bounds)) return true;
		if (lineSE.intersects(bounds)) return true;
		if (lineSW.intersects(bounds)) return true;*/
		
		return false;
	}
	
	/**
	 * Executed for a non-possessed puppet.
	 */
	public abstract void normalUpdate();
}
