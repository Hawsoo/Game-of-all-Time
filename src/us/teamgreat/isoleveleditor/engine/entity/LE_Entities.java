package us.teamgreat.isoleveleditor.engine.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.engine.Sprite;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet.Guard;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet.Puppet;
import us.teamgreat.gameofalltime.gameobject.room.Room;
import us.teamgreat.gameofalltime.resources.Resources;
import us.teamgreat.isoleveleditor.resources.LE_Resources;

/**
 * The list of entities used within.
 * @author Noah Brown, Timothy Bennett
 *
 */
public enum LE_Entities
{
	// HERESTO add Entities
	GND_REG("Ground Regular", Resources.block_beta, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 1),
	GND_REG_V2("Ground Regular v2", Resources.block_beta, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 1),
	WALL("Wall", Resources.block_beta, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 1),
	WALL_V2("Wall v2", Resources.block_beta, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 1),
	WALL_V3("Wall v3", Resources.block_beta, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 1),
	WALL_INV("Wall Invisible", Resources.block_beta, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 1),
	PATH_NODE("Path Node", Resources.path_node, LE_Resources.GND_SILHOUETTE, LE_Resources.GND_SHADOW, 3),
	PUPPET_GUARD("Guard Puppet", Resources.guard_icon, LE_Resources.GUARD_SILHOUETTE, Resources.guard_beta, Guard.class);
	
	public static final int REGULAR_ENTITY = 1;
	public static final int PUPPET_ENTITY = 2;
	public static final int PATH_NODE_ENTITY = 3;
	
	private int id;
	private String name;
	private Sprite sprite;
	private Sprite silhouette;
	private Sprite shadow;
	private int entitytype;
	
	private Class<? extends Puppet> puppetClass;
	
	/**
	 * Creates a regular entity model.
	 */
	private LE_Entities(String name, Sprite sprite, Sprite silhouette, Sprite shadow, int entitytype)
	{
		this.name = name;
		this.sprite = sprite;
		this.silhouette = silhouette;
		this.shadow = shadow;
		this.entitytype = entitytype;
		
		id = LE_Resources.entityEnumCounter;
		LE_Resources.entityEnumCounter++;
	}
	
	/**
	 * Creates a puppet entity model.
	 */
	private LE_Entities(String name, Sprite sprite, Sprite silhouette, Sprite shadow, Class<? extends Puppet> puppetClass)
	{
		this.name = name;
		this.sprite = sprite;
		this.silhouette = silhouette;
		this.shadow = shadow;
		this.entitytype = PUPPET_ENTITY;
		
		this.puppetClass = puppetClass;
		
		id = LE_Resources.entityEnumCounter;
		LE_Resources.entityEnumCounter++;
	}
	
	/**
	 * Gets entity model from id.
	 * @param id
	 * @return
	 */
	public static LE_Entities getEntityModel(int id)
	{
		// Find matching ID number
		EnumSet<LE_Entities> entities = EnumSet.allOf(LE_Entities.class);
		for (LE_Entities entity : entities)
		{
			if (entity.getID() == id)
				return entity;
		}
		
		return null;
	}
	
	/**
	 * Creates a puppet from a delegated constructor.
	 * @param x
	 * @param y
	 * @param z
	 * @param dir
	 * @param room
	 * @param game
	 * @return
	 */
	public Puppet instantiatePuppet(int x, int y, int z, int dir, Room room, Game game)
	{
		try
		{
			// Create new instance of a puppet
			return puppetClass.getDeclaredConstructor(new Class[] {int.class, int.class, int.class, int.class, Room.class, Game.class}).newInstance(x, y, z, dir, room, game);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
		
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
	 * Gets the model type.
	 * @return
	 */
	public int getEntityType()
	{
		return entitytype;
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
