package uniDB;
import java.util.ArrayList;
public class IntramuralSport {
	public ArrayList<Team> teams; 
	public String name;
	
	public IntramuralSport(String name)
	{
		this.name = name;
		teams = new ArrayList<Team>();
	}
	
	/**
	 * 
	 * @param name
	 * Sets the name of this sport
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name of this sport
	 * @return String
	 */
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * 
	 * @param name
	 * Adds a team of name to the list of teams
	 */
	public void addTeam(String name)
	{
		Team n = new Team(name);
		teams.add(n);
	}
	
	/**
	 * 
	 * @param name
	 * Finds the team of a particular name and returns it
	 * @return Team
	 */
	public Team getTeam(String name)
	{
		for(Team t: teams)
			if(t.getName().equals(name))
				return t;
		return null;
	}
	
	/**
	 * Formats a string containing all teams, their names, records, and players
	 * @return String
	 */
	public String printTeams()
	{
		String s = this.name + "\n-------------------------\n";
		for(Team t: teams)
			s += t.toString();
		s += "-------------------------";
		return s;
	}
	
	/**
	 * Adds a win to a team of a particular name
	 * @param name
	 */
	public void addWin(String name)
	{
		getTeam(name).addWin();
	}
	
	/**
	 * Adds a loss to a team of a particular name
	 * @param name
	 */
	public void addLoss(String name)
	{
		getTeam(name).addLoss();
	}
	
	/**
	 * Adds to a team of a particular name
	 * @param name
	 */
	public void addTie(String name)
	{
		getTeam(name).addTie();
	}
	
	/**
	 * 
	 * @param team
	 * @param name
	 * 
	 * Adds a player name to a team of a particular name
	 */
	public void addPlayer(String team, String name)
	{
		getTeam(team).addPlayer(name);
	}
	
	/**
	 * 
	 * Helper class that contains all the info and functionality of an intramural team
	 *
	 */
	private class Team{
		ArrayList<String> players;
		String name;
		int[] record; 
		
		Team (String name)
		{
			this.name = name;
			players = new ArrayList<String>();
			record = new int[] {0,0,0};
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public String getName() 
		{
			return this.name;
		}
		
		public String listPlayers()
		{
			String s = "\n";
			for(String yeet: players)
				s += yeet + "\n";
			return s;
		}
		
		public void addPlayer(String name)
		{
			players.add(name);
		}
		
		public void addWin()
		{
			record[0]++;
		}
		
		public void addLoss()
		{
			record[1]++;
		}
		
		public void addTie()
		{
			record[2]++;
		}
		
		public String getRecord()
		{
			return "Win: " + record[0] + "| Loss: " + record[1] + "| Tie: " + record[2];
		}
		
		public String toString()
		{
			String s = "";
			s += "Name: " + this.name + "\n";
			s += "Players: ";
			for(String n: players)
				s+= n + ",";
			s += "\n";
			s += this.getRecord();
			s += "\n";
			return s;
		}
	}
}
