package uniDB;
import java.util.Random;
/**
 * 
 * @author Samuel Jungman
 *
 */
public class DiningCenter {
	private String[] entrees = {"Pizza","Chicken","Steak","Shrimp Scampi","Burger","Tacos","Lasagna", "Porkchops", "Salmon", "Ribs"};
	private String[] sides = {"Onion Rings","Hawaiian Style Rice","Pasta Salad","Grilled Peppers and Onions","Grilled Asparagus","Tater Tots","Fresh Fruit", "Baked Potato", "Side Salad", "Fries"};
	private int[][] weeklyTimes;
	private String Name;
	Random r;
	
	public DiningCenter(int x[][], String name)
	{
		weeklyTimes = new int[7][6];
		for(int i = 0; i < 7; i++)
			for(int j = 0; j < 6; j++)
				weeklyTimes[i][j] = x[i][j];
		r = new Random();
		this.Name = name;
	}
	
	/**
	 * Prints menu item combination
	 * @return String
	 */
	public String checkMenu()
	{
		return Name + "| Serving: " + entrees[r.nextInt(10)] + " and " + sides[r.nextInt(10)];
	}
	
	/**
	 * Returns a message about the availability of this dining center
	 * @param day integer of 0-6 representing Mon-Sun
	 * @param time integer representing the current hour
	 * @return String 
	 */
	public String checkAvailibility(int day, int time)
	{
		if(time > weeklyTimes[day][0] && time < weeklyTimes[day][1])
			return "Currently Serving from " + weeklyTimes[day][0] + ":00 to " + weeklyTimes[day][1] + ":00";
		if(time > weeklyTimes[day][2] && time < weeklyTimes[day][3])
			return "Currently Serving from " + weeklyTimes[day][2] + ":00 to " + weeklyTimes[day][3] + ":00";	
		if(time > weeklyTimes[day][4] && time < weeklyTimes[day][5])
			return "Currently Serving from " + weeklyTimes[day][4] + ":00 to " + weeklyTimes[day][5] + ":00";
		if(time < weeklyTimes[day][0])
			return "Next availible time from " + weeklyTimes[day][0] + ":00 to " + weeklyTimes[day][1] + ":00";
		if(time < weeklyTimes[day][2])
			return "Next availible time from " + weeklyTimes[day][2] + ":00 to " + weeklyTimes[day][3] + ":00";
		if(time < weeklyTimes[day][4])
			return "Next availible time from " + weeklyTimes[day][4] + ":00 to " + weeklyTimes[day][5] + ":00";		
		if(day == 6)
			return "Next availible time from " + weeklyTimes[0][0] + ":00 to " + weeklyTimes[0][1] + ":00";	
		return "Next availible time from " + weeklyTimes[day + 1][0] + ":00 to " + weeklyTimes[day + 1][1] + ":00";
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public void changeName(String name) 
	{
		this.Name = name;
	}
}
