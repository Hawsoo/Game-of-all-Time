package us.teamgreat.gameofalltime.engine;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

import us.teamgreat.gameofalltime.resources.Resources;

/**
 * Basic class for loading and creating
 * sound & music files.
 * @author Timothy Bennett
 *
 */
public class AudioUtil
{
	/**
	 * Loads a piece of audio within the
	 * resource directory.
	 * @param name
	 * @return 
	 */
	public static Audio getAudio(String name)
	{
		try
		{
			// Gets audio
			return AudioLoader.getAudio("OGG", AudioUtil.class.getResourceAsStream(Resources.RESOURCES_DIR + name));
		} catch (IOException e) {}
		
		return null;
	}
	
	/**
	 * Streams a piece of audio within the resource
	 * directory and loads it as a streaming file.
	 * @param name
	 * @return
	 */
	public static Audio getAudioAsStream(String name)
	{
		try
		{
			// Gets audio
			return AudioLoader.getStreamingAudio("OGG", AudioUtil.class.getResource(Resources.RESOURCES_DIR + name));
		} catch (IOException e) {}
		
		return null;
	}
}
