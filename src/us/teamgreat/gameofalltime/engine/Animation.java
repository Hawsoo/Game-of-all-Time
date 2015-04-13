package us.teamgreat.gameofalltime.engine;


/**
 * Creates an animation from multiple sprites.
 * @author Timothy Bennett
 *
 */
public class Animation
{
	private Sprite[] sprites;
	private int frame;
	private int subframe;
	
	private int frameSize;
	
	/**
	 * Creates the animation.
	 * @param sprites
	 */
	public Animation(int frameSize, Sprite... sprites)
	{
		this.frameSize = frameSize;
		this.sprites = sprites;
		resetAni();
	}
	
	/**
	 * Resets the animation.
	 */
	public void resetAni()
	{
		frame = 0;
		subframe = 0;
	}
	
	/**
	 * Renders, then moves forward a frame.
	 * @param x
	 * @param y
	 */
	public void render(int x, int y)
	{
		sprites[frame].render(x, y);
		
		// Update frames
		subframe++;
		if (subframe >= frameSize)
		{
			subframe = 0;
			frame++;
		}
		
		// Loop if too large
		if (frame >= sprites.length)
			resetAni();
	}
}
