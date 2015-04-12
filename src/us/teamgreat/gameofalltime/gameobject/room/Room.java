package us.teamgreat.gameofalltime.gameobject.room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Camera;
import us.teamgreat.gameofalltime.engine.MathUtil;
import us.teamgreat.gameofalltime.gameobject.GameObject;
import us.teamgreat.gameofalltime.gameobject.entity.Entity;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.Puppet;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.Collision;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.eventcollider.EventCollider;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.collision.wall.Wall;
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
	protected Game game;
	protected ArrayList<Entity> entities;
	private ArrayList<Puppet> puppets;
	ArrayList<Collision> collisions;
	
	private Camera camera;
	private Puppet followobj;
	
	/**
	 * Initializes room.
	 */
	private Room(String filename, Game game)
	{
		this.game = game;
		entities = new ArrayList<Entity>();
		puppets = new ArrayList<Puppet>();
		collisions = new ArrayList<Collision>();
		
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
		// FIXME make file reader read from within jar file
		// Check if file exists
		File file;
		if ((file = new File(Resources.RESOURCES_DIR + "levels/" + name)).exists())
		{
			// Create initial room
			Room room = new Room(name, game);
			
			// Read file
			BufferedReader br = new BufferedReader(
					new FileReader(file));
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
					
					// Create object
					if (type == LE_EntityTypes.ASTHETIC_ENTITY)
					{
						// Sort if entity is a regular entity or a puppet
						if (model.getEntityType() == LE_Entities.PUPPET_ENTITY)
						{
							room.puppets.add(model.instantiatePuppet((int)pos.x, (int)pos.y, (int)pos.z, room.collisions, game));
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
		
		puppets.get(0).isPossessed = true;
		return puppets.get(0);
	}
	
	/**
	 * Updates the camera.
	 */
	private void updateCamera()
	{
		int tx = (int)followobj.x;
		int ty = (int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(int)(followobj.z * Resources.Z_RATIO + followobj.y);
		double distance = MathUtil.getDistance((int)camera.pos.x, (int)camera.pos.y, tx, ty) / 10;
		double angle = MathUtil.getAngle((int)camera.pos.x, (int)camera.pos.y, tx, ty);
		
		int dx = (int) (distance * Math.cos(Math.toRadians(angle)));
		int dy = (int) (distance * Math.sin(Math.toRadians(angle)));
		
		camera.pos = new Vector2f(camera.pos.x + dx, camera.pos.y + dy);
	}
	
	@Override
	public void update()
	{
		// Update each entity
		for (Entity entity : entities)
			entity.update();
		
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
		
		// Render each entity
		for (Entity entity : entities)
			entity.render();
		
		// Render each puppet
		for (Puppet puppet : puppets)
			puppet.render();
		
		// Render each ground
		for (Collision collision : collisions)
			collision.render();
	}
}
