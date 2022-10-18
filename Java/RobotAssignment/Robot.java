package RobotAssignment;

import java.util.*;
/**
 * This program create the class Robot which represents a single Robot
 * @author mae93
 * @since July 16 2017
 */
public class Robot {
	protected int x;
	protected int y;
	protected String name;
	
	/**
	 * Robot constructor
	 * @param x the x-coordinate of the Robot
	 * @param y the y-coordinate of the Robot
	 * @param name the name of the Robt
	 */
	public Robot(int x, int y, String name){
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	/**
	 * This method returns a String representation of the Robot
	 */
	public String toString(){
		return name;
	}
	
	/**
	 * This method examines the roots the Robot can take to arrive at a point
	 * @param destX the x-coordinate of the point
	 * @param destY the y-coordinate of the point
	 * @return a List of all the roots
	 */
	public ArrayList<String> explore(int destX, int destY){
		int size = (destX - x) + (destY - y) + ((destX - x) * (destY - y));
		return explore(destX, destY, new ArrayList<>(), "", x, y, size);
	}
	
	/**
	 * This method examines the roots the Robot can take to arrive at a point via Recursion and starting with a random movement (N/E/NE)
	 * @param destX the x-coordinate of the point
	 * @param destY the y-coordinate of the point
	 * @param solutions the List we need to add solutions to
	 * @param s a String to fill with directions
	 * @param tempX the initial x-coordinate of the Robot
	 * @param tempY the initial y-coordinate of the Robot
	 * @param size the size of the List we need to return
	 * @return a List of all the roots
	 */
	public ArrayList<String> explore(int destX, int destY, ArrayList<String> solutions, String s, int tempX, int tempY, int size){
		while(solutions.size() < size){
			if(tempX == destX && tempY ==  destY){
				if(!solutions.contains(s))
					solutions.add(s);
				s = "";
				tempX = x;
				tempY = y;
			}
			else{
				int temp = (int) (1 + Math.random() * 6); //rolling a dice
				
				if(temp == 1 || temp == 2){
					if(tempX + 1 <= destX && tempY + 1 <= destY){
						s += " NE";
						tempX++;
						tempY++;
					}
					else if(tempX + 1 <= destX){
						s += " E";
						tempX++;
					}
					else if(tempY + 1 <= destY){
						s += " N";
						tempY++;
					}
				}
				
				if(temp == 3 || temp == 4){
					if(tempX + 1 <= destX){
						s += " E";
						tempX++;
					}
					else if(tempX + 1 <= destX && tempY + 1 <= destY){
						s += " NE";
						tempX++;
						tempY++;
					}
					else if(tempY + 1 <= destY){
						s += " N";
						tempY++;
					}
				}
				
				if(temp == 5 || temp == 6){
					if(tempY + 1 <= destY){
						s += " N";
						tempY++;
					}
					else if(tempX + 1 <= destX && tempY + 1 <= destY){
						s += " NE";
						tempX++;
						tempY++;
					}
					else if(tempX + 1 <= destX){
						s += " E";
						tempX++;
					}
				}
				
				explore(destX, destY, solutions, s, tempX, tempY, size);
			}
		}
		
		return solutions;
	}
	
}
