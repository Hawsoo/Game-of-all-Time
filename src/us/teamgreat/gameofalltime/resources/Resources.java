package us.teamgreat.gameofalltime.resources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.openal.Audio;

import us.teamgreat.gameofalltime.engine.AudioUtil;
import us.teamgreat.gameofalltime.engine.Sprite;

/**
 * Packages all resources in a
 * registry.
 * @author Timothy Bennett
 *
 */
public class Resources
{
	public static final DisplayMode FULLSCREEN_SIZE = Display.getDesktopDisplayMode();
	public static final DisplayMode DEFAULT_SIZE = new DisplayMode(1280, 720);
	
	public static final String RESOURCES_DIR = "/us/teamgreat/gameofalltime/resources/";
	
	public static final int BLOCK_SIZE = 32;
	
	// Music
	public static Audio test_audio = AudioUtil.getAudioAsStream("music/test.ogg");
	
	// Sprites
	public static Sprite testspr;
	public static Sprite sanic_beta;
	
	public static Sprite player_e;
	public static Sprite player_n;
	public static Sprite player_ne;
	public static Sprite player_nw;
	public static Sprite player_s;
	public static Sprite player_se;
	public static Sprite player_sw;
	public static Sprite player_w;
	
	// Key input
	public static Vector2f joy_position = new Vector2f();
	public static boolean action = false, item1 = false, item2 = false, item3 = false;
	
	/**
	 * Loads the textures.
	 */
	public static void loadTextures()
	{
		testspr = new Sprite(32, 32,"images/Untitled.png");
		sanic_beta = new Sprite(16, 0,"images/sanic2.png");
		
		player_e = new Sprite(13, 0,"images/player/player_e.png");
		player_n = new Sprite(20, 0,"images/player/player_n.png");
		player_ne = new Sprite(17, 0,"images/player/player_ne.png");
		player_nw = new Sprite(17, 0,"images/player/player_nw.png");
		player_s = new Sprite(22, 0,"images/player/player_s.png");
		player_se = new Sprite(15, 0,"images/player/player_se.png");
		player_sw = new Sprite(15, 0,"images/player/player_sw.png");
		player_w = new Sprite(13, 0,"images/player/player_w.png");
	}
	
	/**
	 * Creates a bytebuffer image.
	 * @param image
	 * @return
	 */
	public static ByteBuffer importImageToByteBuffer(BufferedImage image)
	{
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;
		
		// Create colormap of the bufferedimage
		ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8, 8, 8, 8}, true, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
		
		raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, image.getWidth(), image.getHeight(), 4, null);
		texImage = new BufferedImage(glAlphaColorModel, raster, true, new Hashtable<Object, Object>());
		
		// Copy the source image into the produced image
		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, 0f));
		g.fillRect(0, 0, 256, 256);
		g.drawImage(image, 0, 0, null);
		
		// Build a byte buffer from the temporary image to be used by OpenGL to produce a texture
		byte[] data = ((DataBufferByte)texImage.getRaster().getDataBuffer()).getData();
		
		// Prepare byte buffer
		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();
		
		return imageBuffer;
	}
}
