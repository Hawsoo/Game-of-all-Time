package us.teamgreat.gameofalltime.gameobject.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Camera;
import us.teamgreat.gameofalltime.engine.MathUtil;
import us.teamgreat.gameofalltime.gameobject.GameObject;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.MapObject;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.MapObjectContainer;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.PathNode;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.eventcollider.EventCollider;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.wall.Wall;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet.Player;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet.Puppet;
import us.teamgreat.gameofalltime.resources.Resources;
import us.teamgreat.isoleveleditor.engine.entity.LE_Entities;
import us.teamgreat.isoleveleditor.engine.entity.LE_EntityTypes;

/**
 * Basic room for objects.
 * @author Timothy Bennett
 *
 */
public class Room implements GameObject
{
	public Player player;
	
	protected Game game;
	public ArrayList<MapObject> objects;
	public ArrayList<Puppet> puppets;
	public ArrayList<Collision> collisions;
	
	private Camera camera;
	private Puppet followobj;
	
	/**
	 * Initializes room.
	 */
	private Room(String filename, Game game)
	{
		this.game = game;
		objects = new ArrayList<MapObject>();
		puppets = new ArrayList<Puppet>();
		collisions = new ArrayList<Collision>();
		
		player = new Player(0, 0, 0, Puppet.DIR_S, this, game);
		camera = new Camera(new Vector2f(0, 0), new Vector2f(0, 0), game);
	}
	
	/**
	 * Loads the room and returns it.
	 * @param name
	 * @param game
	 * @throws IOException
	 * @return
	 */
	public static Room loadRoom(String name, Game game, Runnable roomscript) throws IOException
	{
		// Check if file exists
		InputStream in = Room.class.getResourceAsStream(Resources.RESOURCES_DIR + "levels/" + name);
		if (in != null)
		{
			// Create initial room
			Room room = new Room(name, game);
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(in));
			{
				// Read lines of file
				String line;
				while ((line = br.readLine()) != null)
				{
					// Parse contents (by tabs "\t")
					String[] parts = line.split("\t");
					
					// Interpret part 0 (entity ID)
					LE_Entities model = LE_Entities.getEntityModel(Integer.parseInt(parts[0]));
					
					// Interpret part 1 (x,y,z) by commas
					String[] coords = parts[1].split(",");
					Vector3f pos = new Vector3f(Float.parseFloat(coords[0]), Float.parseFloat(coords[1]), Float.parseFloat(coords[2]));
					
					// Interpret part 2 (entity type ID)
					LE_EntityTypes type = LE_EntityTypes.getEntityType(Integer.parseInt(parts[2]));
					
					// Interpret part 3 (entity direction)
					int dir = Puppet.DIR_S;
					if (parts.length >= 4)
						dir = Integer.parseInt(parts[3]);
					
					// Create object
					if (type == LE_EntityTypes.ASTHETIC_ENTITY)
					{
						// Sort if entity is a regular entity or a puppet
						if (model.getEntityType() == LE_Entities.PUPPET_ENTITY)
						{
							room.puppets.add(model.instantiatePuppet((int)pos.x, (int)pos.y, (int)pos.z, dir, room, game));
						}
						else if (model.getEntityType() == LE_Entities.PATH_NODE_ENTITY)
						{
							room.objects.add(new PathNode(pos.x, pos.y, pos.z, game));
						}
						else if (model.getEntityType() == LE_Entities.REGULAR_ENTITY)
						{
							room.objects.add(new MapObjectContainer(pos.x, pos.y, pos.z, model.getSprite(), game));
						}
					}
					else if (type == LE_EntityTypes.WALL_ENTITY)
					{
						// Add a wall to the collisions
						room.collisions.add(new Wall(pos.x, pos.y, pos.z, model.getSprite(), game));
					}
					else if (type == LE_EntityTypes.EVENT_ENTITY)
					{
						// Add an event collider to the collisions
						room.collisions.add(new EventCollider(pos.x, pos.y, pos.z, game, roomscript));
					}
				}
			}
			br.close();
			
			// Return room
			return room;
		}
		// Throw error of failure
		else
		{
			throw new IOException();
		}
	}
	
	/**
	 * Gets the first possessed puppet.
	 * @return
	 */
	private Puppet getPossessedPuppet()
	{
		// Cycle thru puppets
		for (int i = 0; i < puppets.size(); i++)
		{
			if (puppets.get(i).isPossessed)
				return puppets.get(i);
		}
		
		player.isPossessed = true;
		return player;
	}
	
	/**
	 * Updates the camera.
	 */
	private void updateCamera()
	{
		int tx = (int)followobj.x;
		int ty = (int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(followobj.z + followobj.y + Resources.drawYoff);
		double distance = MathUtil.getDistance((int)camera.pos.x, (int)camera.pos.y, tx, ty) / 10;
		double angle = MathUtil.getAngle((int)camera.pos.x, (int)camera.pos.y, tx, ty);
		
		int dx = (int) (distance * Math.cos(Math.toRadians(angle)));
		int dy = (int) (distance * Math.sin(Math.toRadians(angle)));
		
		camera.pos = new Vector2f(camera.pos.x + dx, camera.pos.y + dy);
	}
	
	@Override
	public void update()
	{
		// Update player
		player.update();
		
		// Update each entity
		for (MapObject object : objects)
			object.update();
		
		// Update each puppet
		for (Puppet puppet : puppets)
			puppet.update();
		
		// Update each collision
		for (Collision collision : collisions)
			collision.update();
		
		// Update camera
		if (followobj == null || !followobj.isPossessed)
			followobj = getPossessedPuppet();
		updateCamera();
	}
	
	@Override
	public void render()
	{
		// Transform camera
		camera.translateView();
		
		// Combine everything
		MapObject[] allObjs = new MapObject[objects.size() + puppets.size() + collisions.size() + 1];
		int i = 0;
		
		// Add objects
		for (MapObject object : objects)
		{
			allObjs[i] = object;
			i++;
		}
		
		// Add objects
		for (Puppet puppet : puppets)
		{
			allObjs[i] = puppet;
			i++;
		}
		
		// Add collisions
		for (Collision collision : collisions)
		{
			allObjs[i] = collision;
			i++;
		}
		
		// Add player
		allObjs[i] = player;
		
		// Render
		renderInOrderByZ(allObjs);
	}
	
	/**
	 * Renders everything in order.
	 * @param objs
	 */
	private void renderInOrderByZ(MapObject[] objs)
	{
		int j;				// The number of items sorted so far
		MapObject key;		// The item to be inserted
		int i;
		
		// Execute insertion sort
		for (j = 1; j < objs.length; j++)			// Start with 1 (not 0)
		{
			key = objs[j];
			for (i = j - 1; (i >= 0) && (objs[i].z < key.z); i--)			// Smaller values are moving up
			{
				objs[i + 1] = objs[i];
			}
			objs[i + 1] = key;							// Put the key in its proper location
		}
		
		// Render ordered list backwards
		for (MapObject object : objs)
		{
			object.render();
		}
	}
}
