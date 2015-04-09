package us.teamgreat.gameofalltime.gameobject.room;

import us.teamgreat.gameofalltime.gameobject.entity.mapobject.ground.Gnd_Regular;

/**
 * For use as beta stuff.
 * @author Timothy Bennett
 *
 */
public class BetaRoom extends Room
{
	public BetaRoom()
	{
		entities.add(new Gnd_Regular(0, 0, 0));
	}
}
