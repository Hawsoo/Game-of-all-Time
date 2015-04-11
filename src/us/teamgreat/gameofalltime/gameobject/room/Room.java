package us.teamgreat.gameofalltime.gameobject.room;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Camera;
import us.teamgreat.gameofalltime.engine.MathUtil;
import us.teamgreat.gameofalltime.gameobject.GameObject;
import us.teamgreat.gameofalltime.gameobject.entity.Entity;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.Puppet;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground.Ground;
import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Basic room for objects.
 * @author Timothy Bennett
 *
 */
public abstract class Room implements GameObject
{
	protected Game game;
	protected ArrayList<Entity> entities;
	protected ArrayList<Puppet> puppets;
	protected ArrayList<Ground> grounds;
	
	private Camera camera;
	private Puppet followobj;
	
	/**
	 * Initializes room.
	 */
	public Room(Game game)
	{
		this.game = game;
		entities = new ArrayList<Entity>();
		puppets = new ArrayList<Puppet>();
		grounds = new ArrayList<Ground>();
		
		camera = new Camera(new Vector2f(0, 0), new Vector2f(0, 0), game);
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
		
		// Update each ground
		for (Ground ground : grounds)
			ground.update();
		
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
		for (Ground ground : grounds)
			ground.render();
	}
}
