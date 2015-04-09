package us.teamgreat.gameofalltime.gameobject.room;

import us.teamgreat.gameofalltime.Game;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.Player;
import us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground.Gnd_Regular;

/**
 * For use as beta stuff.
 * @author Timothy Bennett
 *
 */
public class BetaRoom extends Room
{
	public BetaRoom(Game game)
	{
		super(game);
		
		// Grounds
		grounds.add(new Gnd_Regular(0, 1, 0, game));
		
		// Puppets
		puppets.add(new Player(-32, 0, 32, grounds, game));
		
		// Entities
	}
}
