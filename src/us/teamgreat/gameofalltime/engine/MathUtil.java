package us.teamgreat.gameofalltime.engine;


/**
 * Contains basic math utilities.
 * @author Timothy Bennett
 *
 */
public class MathUtil
{
	/**
	 * Gets an angle from the two points.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getAngle(double x1, double y1, double x2, double y2)
	{
		return correctAngle(Math.toDegrees(Math.atan2(y2 - y1, x2 - x1)));
	}
	
	/**
	 * Flips the angle.
	 * @param angle
	 * @return
	 */
	public static double flipAngle(double angle)
	{
		return correctAngle(angle + 180);
	}
	
	/**
	 * Corrects angle into the range of 0-360.
	 * @param angle
	 * @return
	 */
	public static double correctAngle(double angle)
	{
		while(angle < 0) angle += 360;
		while(angle >= 360) angle -= 360;
		return angle;
	}
	
	/**
	 * Gets the distance between two points.
	 * @param from
	 * @param to
	 * @return
	 */
	public static double getDistance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
