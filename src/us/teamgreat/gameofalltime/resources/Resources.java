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
