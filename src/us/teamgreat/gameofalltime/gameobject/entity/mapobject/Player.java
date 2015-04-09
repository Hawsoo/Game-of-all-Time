package us.teamgreat.gameofalltime.gameobject.entity.mapobject;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.resources.Resources;

public class Player extends MapObject
{
	/**
	 * Creates player.
	 * @param x
	 * @param y
	 * @param z
	 * @param game
	 */
	public Player(int x, int y, int z, Game game)
	{
		super(x, y, z, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update()
	{
		// Get input
		hspeed += Resources.joy_position.x;
		vspeed -= Resources.joy_position.y;
		
		// Move
		x += hspeed;
		z += vspeed;
	}
	
	@Override
	public void render()
	{
		// Draw sanic
		Resources.sanic_beta.render((int)x, (int)z);
	}
	
}
