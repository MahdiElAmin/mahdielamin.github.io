package Exercise2;

import java.util.*;
/**
 * This program creates the Iterable class Robots which symbolizes a List of Robot(other class)
 * @author mae93
 * @since July 26 2017
 */
public class Robots implements Iterable<Robot>{
	protected List<Robot> robots;
	
	/**
	 * Robots constructor
	 */
	public Robots(){
		robots = new ArrayList<>();
	}
	
	/**
	 * This method adds a Robot to the List of robots in this class
	 * @param x the x-coordinate of the Robot we want to add
	 * @param y the y-coordinate of the Robot we want to add
	 * @param name the name of the Robot we want to add
	 * @throws RobotExistException An exception to be thrown if the Robot already exists
	 */
	public void addRobot(int x, int y, String name) throws RobotExistException{
		for(int i = 0; i < robots.size(); i++){
			if(robots.get(i).name.equals(name))
				throw new RobotExistException(name);
		}
		robots.add(new Robot(x, y, name));
	}
	
	/**
	 * This method returns an iterator to read all the robots in this class
	 */
	@Override
	public Iterator<Robot> iterator() {
		return robots.iterator();
	}
	
	public static void main(String[] args) {
		Robots robots = new Robots();
		
		try {
			robots.addRobot(1, 2, "r2");
			robots.addRobot(2, 2, "r1");
			robots.addRobot(3, 2, "r2");
		}
		catch(RobotExistException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		for(Robot r: robots) {
			System.out.println("Robot " + r + " starts exploring:");
			System.out.println(r.explore(2, 3));
			System.out.println("----------------------------------");
		}
	}

}
