package us.teamgreat.isoleveleditor.engine.entity;

import java.awt.Point;

import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.gameofalltime.gameobject.entity.mapobject.Puppet;
import us.teamgreat.isoleveleditor.resources.LE_Resources;

/**
 * Basic shell of an entity for use in the
 * enum 'Entities'.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class LE_Entity
{
	private LE_Entities model;
	private Vector3f pos;
	
	public LE_EntityTypes type;
	
	public int direction = Puppet.DIR_S;		// Uses Puppet.class implementation
	
	/**
	 * Creates an entity from a model from entities.
	 * @param model
	 * @param pos
	 */
	public LE_Entity(LE_Entities model, Vector3f pos)
	{
		this.model = model;
		this.pos = pos;
		
		type = LE_Resources.currenttype;
	}
	
	/**
	 * Creates an entity from a model from entities, with
	 * specification on type of entity (i.e. which layer).
	 * @param model
	 * @param pos
	 * @param type
	 */
	public LE_Entity(LE_Entities model, Vector3f pos, LE_EntityTypes type)
	{
		this.model = model;
		this.pos = pos;
		this.type = type;
	}
	
	/**
	 * Creates a projection synthesization.
	 * @return
	 */
	public Point getXYZ2D(boolean includeY)
	{
		if (includeY)
			return new Point((int)pos.x, (int)(pos.z + pos.y));
		else
			return new Point((int)pos.x, (int)(pos.z));
	}
	
	/**
	 * Gets the model of the entity.
	 * @return
	 */
	public LE_Entities getModel()
	{
		return model;
	}
	
	/**
	 * Gets the position of the entity.
	 * @return
	 */
	public Vector3f getPosition()
	{
		return pos;
	}
	
	/**
	 * Sets the y.
	 * @param y
	 */
	public void setY(float y)
	{
		pos.y = y;
	}
	
	/**
	 * Checks if the point is colliding.
	 * @param mousepos
	 * @return
	 */
	public boolean isColliding(Point mousepos)
	{
		return mousepos.equals(getXYZ2D(false));
	}
	
	/**
	 * Renders the sprite at the specified
	 * coordinates.
	 */
	public void render(boolean includeY)
	{
		// Render at projected point
		Point pos = getXYZ2D(includeY);
		model.getSprite().render(pos.x, pos.y);
	}
}
