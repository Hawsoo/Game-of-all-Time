package us.teamgreat.isoleveleditor.engine.entity;

import java.util.EnumSet;

import us.teamgreat.isoleveleditor.resources.Resources;

/**
 * Types of entities.
 * @author Noah Brown, Timothy Bennett
 *
 */
public enum EntityTypes
{
	ASTHETIC_ENTITY, WALL_ENTITY, EVENT_ENTITY;
	
	private int id;
	
	/**
	 * Creates the entity type.
	 */
	private EntityTypes()
	{
		id = Resources.entityTypeEnumCounter;
		Resources.entityTypeEnumCounter++;
	}
	
	/**
	 * Gets the entity type from the enum.
	 * @param id
	 * @return
	 */
	public static EntityTypes getEntityType(int id)
	{
		// Find matching ID number
		EnumSet<EntityTypes> entitytypes = EnumSet.allOf(EntityTypes.class);
		for (EntityTypes type : entitytypes)
		{
			if (type.getID() == id)
				return type;
		}
		
		return null;
	}
	
	/**
	 * Gets the type id.
	 * @return
	 */
	public int getID()
	{
		return id;
	}
}
