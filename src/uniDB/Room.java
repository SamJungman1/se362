package uniDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Room that is contained inside of a dorm
 * @author Evan Peterson
 *
 */
public class Room {
	
	/**
	 * Room number
	 */
	private int roomNumber;
	
	/**
	 * Amount of students able to occupy room
	 */
	private int capacity;
	
	/**
	 * List of students living in room
	 */
	private List<student> occupants;
	
	/**
	 * Construct room with a given room number, default capacity, and empty occupant list
	 * @param roomNumber 
	 */
	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
		capacity = 2; // Default
		occupants = new ArrayList<student>();
	}
	
	/**
	 * Construct room with given room number and capacity and empty occupant list
	 * @param roomNumber 
	 * @param capacity amount of students able to occupy room
	 */
	public Room(int roomNumber, int capacity) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
	}
	
	/**
	 * Change the capacity of the room
	 * @param newCapacity  
	 */
	public void changeCapacity(int newCapacity) {
		this.capacity = newCapacity;
	}
	
	/**
	 * Adds student to room
	 * @param student student to be added
	 */
	public void addStudent(student student) {
		occupants.add(student);
	}
	
	/**
	 * Gets room number
	 * @return roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	
	/**
	 * Gets room capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Gets room occupants
	 * @return occupants of room
	 */
	public List<student> getOccupants() {
		return occupants;
	}
	
}
