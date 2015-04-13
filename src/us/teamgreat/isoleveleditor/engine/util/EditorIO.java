package us.teamgreat.isoleveleditor.engine.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.lwjgl.util.vector.Vector3f;

import us.teamgreat.gameofalltime.gameobject.entity.mapobject.puppet.Puppet;
import us.teamgreat.gameofalltime.resources.Resources;
import us.teamgreat.isoleveleditor.engine.entity.LE_Entities;
import us.teamgreat.isoleveleditor.engine.entity.LE_Entity;
import us.teamgreat.isoleveleditor.engine.entity.LE_EntityTypes;
import us.teamgreat.isoleveleditor.ui.ViewerWindow;

/**
 * Handles the input and output
 * for the program.
 * @author Noah Brown, Timothy Bennett
 *
 */
public class EditorIO
{
	/**
	 * Opens a file for stuff.
	 * @return if the file was successfully opened.
	 */
	public static void openFile()
	{
		// Open dialog to load a level
		JFileChooser fc = new JFileChooser(new File(Resources.LEVELS_ABSOLUTE_DIR));
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION && fc.getSelectedFile().exists())
		{
			ViewerWindow.clearEntities();
			ViewerWindow.saveAsRequired = false;
			ViewerWindow.savePath = fc.getSelectedFile().getAbsolutePath();
			
			// Load file
			try
			{
				BufferedReader br = new BufferedReader(
						new FileReader(fc.getSelectedFile()));
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
						LE_Entity obj = new LE_Entity(model, pos, type);
						obj.direction = dir;
						ViewerWindow.entities.add(obj);
					}
				}
				br.close();
			} catch (IOException e) {}
		}
	}
	
	/**
	 * Saves the file.
	 * @param forceSaveAs
	 */
	public static void saveFile(boolean forceSaveAs)
	{
		// Save within a dialog if required
		if (forceSaveAs || ViewerWindow.saveAsRequired)
		{
			// Open dialog to save the level
			JFileChooser fc = new JFileChooser(new File(Resources.LEVELS_ABSOLUTE_DIR));
			int returnVal = fc.showSaveDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				ViewerWindow.saveAsRequired = false;
				ViewerWindow.savePath = fc.getSelectedFile().getAbsolutePath();
			}
			else
				// Force abortion of saving
				return;
		}
		
		// Save the Level
		try
		{
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(ViewerWindow.savePath)));
			{
				// Write entity entries
				for (LE_Entity entity : ViewerWindow.entities)
				{
					// Write values
					Vector3f pos = entity.getPosition();
					bw.write(entity.getModel().getID() + "\t" +
							pos.x + "," + pos.y + "," + pos.z + "\t" +
							entity.type.getID() + "\t" +
							entity.direction);
					bw.newLine();
				}
			}
			// Close writer
			bw.close();
		} catch (IOException e) {}
	}
}
