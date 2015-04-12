package us.teamgreat.isoleveleditor.engine.entity;

import java.util.EnumSet;

import us.teamgreat.isoleveleditor.resources.LE_Resources;

/**
 * Types of entities.
 * @author Noah Brown, Timothy Bennett
 *
 */
public enum LE_EntityTypes
{
	ASTHETIC_ENTITY, WALL_ENTITY, EVENT_ENTITY;
	
	private int id;
	
	/**
	 * Creates the entity type.
	 */
	private LE_EntityTypes()
	{
		id = LE_Resources.entityTypeEnumCounter;
		LE_Resources.entityTypeEnumCounter++;
	}
	
	/**
	 * Gets the entity type from the enum.
	 * @param id
	 * @return
	 */
	public static LE_EntityTypes getEntityType(int id)
	{
		// Find matching ID number
		EnumSet<LE_EntityTypes> entitytypes = EnumSet.allOf(LE_EntityTypes.class);
		for (LE_EntityTypes type : entitytypes)
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
