package us.teamgreat.isoleveleditor.engine.entity;

import java.util.EnumSet;

import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.isoleveleditor.resources.Resources;

/**
 * The list of entities used within.
 * @author Noah Brown, Timothy Bennett
 *
 */
public enum Entities
{
	// HERESTO add Entities
	GND_REG("Ground Regular", Resources.GND_REG, Resources.GND_SILHOUETTE, Resources.GND_SHADOW);
	
	private int id;
	private String name;
	private Sprite sprite;
	private Sprite silhouette;
	private Sprite shadow;
	
	/**
	 * Creates an entity model.
	 */
	private Entities(String name, Sprite sprite, Sprite silhouette, Sprite shadow)
	{
		this.name = name;
		this.sprite = sprite;
		this.silhouette = silhouette;
		this.shadow = shadow;
		
		id = Resources.entityEnumCounter;
		Resources.entityEnumCounter++;
	}
	
	/**
	 * Gets entity model from id.
	 * @param id
	 * @return
	 */
	public static Entities getEntityModel(int id)
	{
		// Find matching ID number
		EnumSet<Entities> entities = EnumSet.allOf(Entities.class);
		for (Entities entity : entities)
		{
			if (entity.getID() == id)
				return entity;
		}
		
		return null;
	}
	
	/**
	 * Gets the model id.
	 * @return
	 */
	public int getID()
	{
		return id;
	}
	
	/**
	 * Gets the sprite.
	 * @return
	 */
	public Sprite getSprite()
	{
		return sprite;
	}
	
	/**
	 * Gets the selection silhouette.
	 * @return
	 */
	public Sprite getSilhouette()
	{
		return silhouette;
	}
	
	/**
	 * Gets the sprite shadow.
	 * @return
	 */
	public Sprite getShadow()
	{
		return shadow;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
