package uniDB;
import java.util.Calendar;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class database {
	public static List<student> studentTable;
	public static List<faculty> facultyTable;
	public static List<Group> groupTable;
	public static List<Major> majorTable;
	public static List<Dorm> dormTable;
	public static DiningCenter seasons;
	public static DiningCenter conversations;
	public static DiningCenter udcc;
	public static DiningCenter windows;
	public Calendar calendar;

	public database() {
		studentTable = new ArrayList<student>();
		facultyTable = new ArrayList<faculty>();
		groupTable = new ArrayList<Group>();
		majorTable = new ArrayList<Major>();
		dormTable = new ArrayList<Dorm>();
		int[][] hours = {{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19}};
		seasons = new DiningCenter(hours, "Seasons");
		conversations = new DiningCenter(hours, "Conversations");
	    udcc = new DiningCenter(hours, "UDCC");
		windows = new DiningCenter(hours, "Windows");
		
		calendar = Calendar.getInstance();
	}

	public static void addStudent(student s) {
		studentTable.add(s);
	}

	public static void addGroup(Group g){
		groupTable.add(g);
	}

	public void addFaculty(faculty f) {
		facultyTable.add(f);
	}

	public void addMajor(Major m){majorTable.add(m);}
	
	public void addDorm(Dorm d) {
		dormTable.add(d);
	}
	
	public void listDorms() {
		for (int i = 0; i < dormTable.size(); i++) {
			System.out.println(dormTable.get(i).getName() + " " + dormTable.get(i).getSize() + " rooms");
		}
	}
	
	public Dorm getDorm(String name) {
		Dorm dorm = null;
		
		for (int i = 0; i < dormTable.size(); i++) {
			if(dormTable.get(i).getName().equals(name)) {
				dorm = dormTable.get(i);
			}
		}
		
		return dorm;
	}
	
	public void removeStudent(String username) {
		studentTable.remove(studentTable.indexOf(findStudent(username)));
}

	public void removeFaculty(String username) {
		facultyTable.remove(facultyTable.indexOf(findFaculty(username)));		
	}

	public void removeMajor(String id){majorTable.remove(majorTable.indexOf(findMajor(id)));}
	
	public static faculty findFaculty(String username)
	{
		for(faculty f: facultyTable)
		{
			if(f.getUsername().trim().equals(username.trim()))
				return f;
		}
		return null;
	}
	
	public static student findStudent(String username)
	{
		student temp = null;
		for(student s: studentTable)
		{
			if(s.getUsername().trim().equals(username.trim())) {
				temp = s;
				break;
				}
			}
		return temp;
		}

	public static Group findGroup(String id)
	{
		for(Group g: groupTable)
		{
			if(g.getGroupName().equals(id))
				return g;
		}
		return null;
	}
	public Major findMajor(String id){
		for(Major m: majorTable){
			if(m.getId().equals(id)){
				return m;
			}
		}
		return null;
	}

	public String getMsgsStudent(String username)
	{
		String s = new String();
		String[] arr = findStudent(username).flushInbox();
		for(String a: arr)
		{
			s += a;
			s += "\n";
		}
		return s;
	}
	
	public String getMsgsFaculty(String username)
	{
		String s = new String();
		String[] arr = findFaculty(username).flushInbox();
		for(String a: arr)
		{
			s += a;
			s += "\n";
		}
		return s;
	}

	public void msgStudent(String username, String msg)
	{
		findStudent(username).addMessage(msg);
	}

	public void msgFacutly(String username, String msg)
	{
		findFaculty(username).addMessage(msg);
	}

	/**
	 * Returns the current number of swipes a student has remaining
	 * @param username of Current Student
	 * @return String
	 */
	public String getMealSwipes(String username)
	{
		return username + " has " + findStudent(username).getSwipes() + "remaining!";
	}
	/**
	 * Returns a message about the success or failure of the usage of a meal swipe by a student.
	 * @param username of Current Student
	 * @return String
	 */
	public String useSwipe(String username)
	{
		if(findStudent(username).useSwipe())
		{
			return "Success\n" + getMealSwipes(username);
		}
		else
			return username + " is out of swipes!";
		
	}
	
	public String checkDiningCenter(String name)
	{
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int time = calendar.get(Calendar.HOUR_OF_DAY);
		if(name == "Conversations")
			return conversations.checkAvailibility(day, time);
		if(name == "Seasons")
			return seasons.checkAvailibility(day, time);
		if(name == "UDCC")
			return udcc.checkAvailibility(day, time);
		if(name == "Windows")
			return windows.checkAvailibility(day, time);
		return "No dining center by that name";
	}
	
	public String getMeal(String name)
	{
		if(name == "Conversations")
			return conversations.checkMenu();
		if(name == "Seasons")
			return seasons.checkMenu();
		if(name == "UDCC")
			return udcc.checkMenu();
		if(name == "Windows")
			return windows.checkMenu();
		return "No dining center by that name";
	}
	

}
