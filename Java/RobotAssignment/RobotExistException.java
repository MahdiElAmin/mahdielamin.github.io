package Exercise2;

/**
 * This program creates the Exception RobotExistException
 * @author mae93
 * @since July 16 2017
 */
public class RobotExistException extends Exception{
	/**
	 * RobotExistException constructor
	 * This Exception is thrown when a Robot with a similar name already exists
	 * @param name the name of the Robot
	 */
	public RobotExistException(String name){ super("Robot " + name + " already exists"); }
}
