package uniDB;
import java.util.Random;

public class DiningCenter {
	private String[] entrees = {"Pizza","Chicken","Steak","Shrimp Scampi","Burger","Tacos","Lasagna", "Porkchops", "Salmon", "Ribs"};
	private String[] sides = {"Onion Rings","Hawaiian Style Rice","Pasta Salad","Grilled Peppers and Onions","Grilled Asparagus","Tater Tots","Fresh Fruit", "Baked Potato", "Side Salad", "Fries"};
	private int[][] weeklyTimes;
	Random r;
	
	public DiningCenter(int x[][])
	{
		weeklyTimes = new int[7][6];
		for(int i = 0; i < 7; i++)
			for(int j = 0; j < 6; j++)
				weeklyTimes[i][j] = x[i][j];
		r = new Random();
	}
	
	public String getMeal()
	{
		return "Serving: " + entrees[r.nextInt(10)] + " and " + sides[r.nextInt(10)];
	}
	
	public boolean checkAvailibility(int day, int time)
	{
		if(time > weeklyTimes[day][0] && time < weeklyTimes[day][1])
			return true;
		if(time > weeklyTimes[day][2] && time < weeklyTimes[day][3])
			return true;		
		if(time > weeklyTimes[day][4] && time < weeklyTimes[day][5])
			return true;
		return false;
	}
}
