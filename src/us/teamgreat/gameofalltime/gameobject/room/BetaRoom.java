package us.teamgreat.gameofalltime.gameobject.room;

import us.teamgreat.gameofalltime.Game;

/**
 * For use as beta stuff.
 * @author Timothy Bennett
 *
 */
@Deprecated
public class BetaRoom extends Room
{

	public BetaRoom(String filename, Game game)
	{
		super(filename, game);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
//	public BetaRoom(Game game)
//	{
//		super(game);
//		
//		// Grounds
//		grounds.add(new Wall_Regular(0, 0, 0, game));
//		grounds.add(new Wall_Regular(50, 1, 1, game));
//		grounds.add(new Wall_Regular(2, 1, 2, game));
//		
//		// Puppets
//		puppets.add(new Player(-32, 0, 32, grounds, game));
//		
//		// Entities
//	}
}
