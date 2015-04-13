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
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.openal.Audio;

import us.teamgreat.gameofalltime.engine.Animation;
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
	public static final String LEVELS_ABSOLUTE_DIR = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\src\\us\\teamgreat\\gameofalltime\\resources\\levels\\";
	
	public static final float Z_RATIO = 0.65f;
	public static final float GRID_WIDTH = 48;
	public static final float GRID_HEIGHT = GRID_WIDTH * Z_RATIO;
	
	// Music
	public static Audio test_audio = AudioUtil.getAudioAsStream("music/test.ogg");
	
	// Sprites
	public static Sprite testspr = new Sprite(32, 32,"images/Untitled.png");
	public static Sprite sanic_beta = new Sprite(16, 0,"images/sanic2.png");

	public static Sprite player_beta = new Sprite(24, 0, "images/playerBETA.png");
	public static Sprite guard_beta = new Sprite(24, 0, "images/guardBETA.png");
	
	public static Sprite block_beta = new Sprite(24, 50, "images/blocks/dirt_block.png");
	
	public static Sprite path_node = new Sprite(24, 50, "images/blocks/path_node.png");
	
	public static Sprite guard_icon = new Sprite(18, 0, "images/guard/gS.png");
	
	
	// Load up all player sprites
	private static final int frameSize = 10;
	
	public static Sprite player_e_l = new Sprite(13, 0, "images/player/pE_l.png");
	public static Sprite player_e_r = new Sprite(13, 0, "images/player/pE_r.png");
	public static Sprite player_e = new Sprite(13, 0, "images/player/pE.png");
	public static Animation player_ani_e = new Animation(frameSize, player_e, player_e_l, player_e, player_e_r);
	
	public static Sprite player_n_l = new Sprite(13, 0, "images/player/pN_l.png");
	public static Sprite player_n_r = new Sprite(13, 0, "images/player/pN_r.png");
	public static Sprite player_n = new Sprite(13, 0, "images/player/pN.png");
	public static Animation player_ani_n = new Animation(frameSize, player_n, player_n_l, player_n, player_n_r);
	
	public static Sprite player_ne_l = new Sprite(13, 0, "images/player/pNE_l.png");
	public static Sprite player_ne_r = new Sprite(13, 0, "images/player/pNE_r.png");
	public static Sprite player_ne = new Sprite(13, 0, "images/player/pNE.png");
	public static Animation player_ani_ne = new Animation(frameSize, player_ne, player_ne_l, player_ne, player_ne_r);
	
	public static Sprite player_nw_l = new Sprite(13, 0, "images/player/pNW_l.png");
	public static Sprite player_nw_r = new Sprite(13, 0, "images/player/pNW_r.png");
	public static Sprite player_nw = new Sprite(13, 0, "images/player/pNW.png");
	public static Animation player_ani_nw = new Animation(frameSize, player_nw, player_nw_l, player_nw, player_nw_r);
	
	public static Sprite player_s_l = new Sprite(13, 0, "images/player/pS_l.png");
	public static Sprite player_s_r = new Sprite(13, 0, "images/player/pS_r.png");
	public static Sprite player_s = new Sprite(13, 0, "images/player/pS.png");
	public static Animation player_ani_s = new Animation(frameSize, player_s, player_s_l, player_s, player_s_r);
	
	public static Sprite player_se_l = new Sprite(13, 0, "images/player/pSE_l.png");
	public static Sprite player_se_r = new Sprite(13, 0, "images/player/pSE_r.png");
	public static Sprite player_se = new Sprite(13, 0, "images/player/pSE.png");
	public static Animation player_ani_se = new Animation(frameSize, player_se, player_se_l, player_se, player_se_r);
	
	public static Sprite player_sw_l = new Sprite(13, 0, "images/player/pSW_l.png");
	public static Sprite player_sw_r = new Sprite(13, 0, "images/player/pSW_r.png");
	public static Sprite player_sw = new Sprite(13, 0, "images/player/pSW.png");
	public static Animation player_ani_sw = new Animation(frameSize, player_sw, player_sw_l, player_sw, player_sw_r);
	
	public static Sprite player_w_l = new Sprite(13, 0, "images/player/pW_l.png");
	public static Sprite player_w_r = new Sprite(13, 0, "images/player/pW_r.png");
	public static Sprite player_w = new Sprite(13, 0, "images/player/pW.png");
	public static Animation player_ani_w = new Animation(frameSize, player_w, player_w_l, player_w, player_w_r);
	
	// Load up all guard sprites
	public static Sprite guard_e_l = new Sprite(15, 0, "images/guard/gE_l.png");
	public static Sprite guard_e_r = new Sprite(15, 0, "images/guard/gE_r.png");
	public static Sprite guard_e = new Sprite(15, 0, "images/guard/gE.png");
	public static Animation guard_ani_e = new Animation(frameSize, guard_e, guard_e_l, guard_e, guard_e_r);

	public static Sprite guard_n_l = new Sprite(15, 0, "images/guard/gN_l.png");
	public static Sprite guard_n_r = new Sprite(15, 0, "images/guard/gN_r.png");
	public static Sprite guard_n = new Sprite(15, 0, "images/guard/gN.png");
	public static Animation guard_ani_n = new Animation(frameSize, guard_n, guard_n_l, guard_n, guard_n_r);

	public static Sprite guard_ne_l = new Sprite(15, 0, "images/guard/gNE_l.png");
	public static Sprite guard_ne_r = new Sprite(15, 0, "images/guard/gNE_r.png");
	public static Sprite guard_ne = new Sprite(15, 0, "images/guard/gNE.png");
	public static Animation guard_ani_ne = new Animation(frameSize, guard_ne, guard_ne_l, guard_ne, guard_ne_r);

	public static Sprite guard_nw_l = new Sprite(15, 0, "images/guard/gNW_l.png");
	public static Sprite guard_nw_r = new Sprite(15, 0, "images/guard/gNW_r.png");
	public static Sprite guard_nw = new Sprite(15, 0, "images/guard/gNW.png");
	public static Animation guard_ani_nw = new Animation(frameSize, guard_nw, guard_nw_l, guard_nw, guard_nw_r);

	public static Sprite guard_s_l = new Sprite(15, 0, "images/guard/gS_l.png");
	public static Sprite guard_s_r = new Sprite(15, 0, "images/guard/gS_r.png");
	public static Sprite guard_s = new Sprite(15, 0, "images/guard/gS.png");
	public static Animation guard_ani_s = new Animation(frameSize, guard_s, guard_s_l, guard_s, guard_s_r);

	public static Sprite guard_se_l = new Sprite(15, 0, "images/guard/gSE_l.png");
	public static Sprite guard_se_r = new Sprite(15, 0, "images/guard/gSE_r.png");
	public static Sprite guard_se = new Sprite(15, 0, "images/guard/gSE.png");
	public static Animation guard_ani_se = new Animation(frameSize, guard_se, guard_se_l, guard_se, guard_se_r);

	public static Sprite guard_sw_l = new Sprite(15, 0, "images/guard/gSW_l.png");
	public static Sprite guard_sw_r = new Sprite(15, 0, "images/guard/gSW_r.png");
	public static Sprite guard_sw = new Sprite(15, 0, "images/guard/gSW.png");
	public static Animation guard_ani_sw = new Animation(frameSize, guard_sw, guard_sw_l, guard_sw, guard_sw_r);

	public static Sprite guard_w_l = new Sprite(15, 0, "images/guard/gW_l.png");
	public static Sprite guard_w_r = new Sprite(15, 0, "images/guard/gW_r.png");
	public static Sprite guard_w = new Sprite(15, 0, "images/guard/gW.png");
	public static Animation guard_ani_w = new Animation(frameSize, guard_w, guard_w_l, guard_w, guard_w_r);
	
	// Key input
	public static Vector2f joy_position = new Vector2f();
	public static boolean action = false, item1 = false, item2 = false, item3 = false;
	
	/**
	 * Loads the textures.
	 */
	public static void loadTextures()
	{
		Sprite.initSprites();
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
