package us.teamgreat.gameofalltime.gameobject.room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.resources.Resources;
import us.teamgreat.isoleveleditor.engine.entity.LE_Entities;
import us.teamgreat.isoleveleditor.engine.entity.LE_EntityTypes;

/**
 * Loads a room from a specified file.
 * @author Timothy Bennett
 *
 */
public class RoomLoader
{
	public static void loadRoom(String name, Game game) throws IOException
	{
		// Check if file exists
		File file;
		if ((file = new File(Resources.RESOURCES_DIR + name)).exists())
		{
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
						
					}
				}
			}
			br.close();
		}
		// Throw error of failure
		else
		{
			throw new IOException();
		}
	}
}
