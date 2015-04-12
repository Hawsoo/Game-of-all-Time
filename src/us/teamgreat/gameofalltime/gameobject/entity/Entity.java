package us.teamgreat.gameofalltime.gameobject.entity;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.GameObject;

/**
 * Basic form of an entity.
 * @author Timothy Bennett
 *
 */
public abstract class Entity implements GameObject
{
	protected Game game;
	public double x, y;
	
	/**
	 * Creates entity.
	 * @param x
	 * @param y
	 */
	public Entity(double x, double y, Game game)
	{
		this.x = x;
		this.y = y;
		this.game = game;
		/*
		 * your mama so fat that she fell down the stairs.
		 * It wasn't funny, but the stairs were cracking up.
		 */
	}
}
