package uniDB;

import java.util.ArrayList;
import java.util.List;
/**
 * Grouping of rooms that make up a dorm on campus
 * 
 * @author Evan Peterson
 *
 */
public class Dorm {
	
	/**
	 * Name of the dorm
	 */
	private String name;
	
	/**
	 * List of rooms in the dorm
	 */
	private List<Room> rooms;
	
	/**
	 * Create a dorm with a name and empty list of rooms
	 * @param name name of dorm
	 */
	public Dorm(String name) {
		rooms = new ArrayList<Room>();
		this.name = name;
	}
	
	/**
	 * Create a dorm with name and list of rooms
	 * @param rooms list of rooms in dorm
	 * @param name name of dorm
	 */
	public Dorm(List<Room> rooms, String name) {
		this.rooms = rooms;
		this.name = name;
	}
	
	/**
	 * Returns name of dorm
	 * @return name of dorm
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets amount of dorms
	 * @return amount of dorms
	 */
	public int getSize() {
		return rooms.size();
	}
	
	public Room getRoomById(int id) {
		Room room = null;
		
		for (int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).getRoomNumber() == id) {
				room = rooms.get(i);
				break;
			}
		}
		
		return room;
	}
	
	/**
	 * Create certain amount of singles, doubles, and triple rooms
	 * @param numSingles number of single to add
	 * @param numDoubles number of doubles to add
	 * @param numTriples numberof triples to add
	 */
	public void createRooms(int numSingles, int numDoubles, int numTriples) {
		
		// Singles
		for (int i = 1; i <= numSingles; i++) {
			rooms.add(new Room(i, 1));			
		}
		
		// Doubles
		for(int i = 1;i <= numDoubles;i++) {
			rooms.add(new Room(i, 2));
		}
		
		// Triples
		for(int i = 1;i <= numTriples;i++) {
			rooms.add(new Room(i, 3));
		}
	}
	
	/**
	 * Prints out open rooms
	 */
	public void listOpenRooms() {
		for (int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			if(room.getCapacity() > room.getOccupants().size()) {
				System.out.println(room.getRoomNumber() + " Capacity: " + room.getCapacity());
			}
		}
	}
	
}
