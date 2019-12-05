package uniDB;
import java.util.Calendar;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import uniDB.Major;

public class database {
	public static List<student> studentTable;
	public static List<faculty> facultyTable;
	public static List<Group> groupTable;
	public static List<Major> majorTable;
	public static List<Dorm> dormTable;
	public static List<StudentOrg> studentOrgsTable;
	public static List<Lot> parkingLotTable;
	public static List<Employer> employerTable;
	public static DiningCenter seasons;
	public static DiningCenter conversations;
	public static DiningCenter udcc;
	public static DiningCenter windows;
	public Calendar calendar;

	public database() throws FileNotFoundException {
		studentTable = new ArrayList<student>();
		facultyTable = new ArrayList<faculty>();
		groupTable = new ArrayList<Group>();
		majorTable = new ArrayList<Major>();
		dormTable = new ArrayList<Dorm>();
		studentOrgsTable = new ArrayList<StudentOrg>();
		parkingLotTable = new ArrayList<Lot>();
		employerTable = new ArrayList<Employer>();
		int[][] hours = {{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19},{8,10,12,14,17,19}};
		seasons = new DiningCenter(hours, "Seasons");
		conversations = new DiningCenter(hours, "Conversations");
	    udcc = new DiningCenter(hours, "UDCC");
		windows = new DiningCenter(hours, "Windows");
		calendar = Calendar.getInstance();
		this.load();
	}

	public static void addStudent(student s) {
		studentTable.add(s);
	}

	public static void addLot(Lot l){parkingLotTable.add(l);}
	public static Lot findLot(String id){
		for(Lot l: parkingLotTable){
			if (l.getID().equals(id)){
				return l;
			}
		}
		return null;
	}
	public static void removeLot(Lot l){parkingLotTable.remove(l);}

	public static void addGroup(Group g){
		groupTable.add(g);
	}

	public void addStudentOrg(StudentOrg studentOrg) { studentOrgsTable.add(studentOrg);}

	public void addStudentToOrg(StudentOrg studentOrg, student s){if(!studentOrg.isMember(s)){ studentOrg.addMember(s);}}

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

	public StudentOrg getStudentOrg(String name) {
		for(int i = 0; i < studentOrgsTable.size(); i++){
			if(studentOrgsTable.get(i).getOrgName().trim().equalsIgnoreCase(name.trim())){
				return studentOrgsTable.get(i);
			}
		}
		return null;
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
	
	public static Employer findEmployer(String username)
	{
		Employer temp = null;
		for(Employer s: employerTable)
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

	public static Major findMajor(String id){
		for(Major m: majorTable){
			if(m.getId().equals(id)){
				return m;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param username
	 * Gets all messages from a particular student
	 * @return String
	 */
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
	
	/**
	 * 
	 * @param username
	 * Gets all messages from a particular faculty
	 * @return String
	 */
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
	
	/**
	 * 
	 * @param username
	 * @param msg
	 * Sends a message to a Student of a unique username
	 */
	public void msgStudent(String username, String msg)
	{
		findStudent(username).addMessage(msg);
	}

	/**
	 * 
	 * @param username
	 * @param msg
	 * Sends a message to a Faculty of a unique username
	 */
	public void msgFacutly(String username, String msg)
	{
		findFaculty(username).addMessage(msg);
	}
	
	/**
	 * 
	 * @param e
	 * Adds an employer to the table
	 */
	public void addEmployer(Employer e)
	{
		employerTable.add(e);
	}
	/**
	 * 
	 * @param username
	 * @param type
	 * @param title
	 * @param wage
	 * Adds an offer to an employer's list of offers
	 */
	public void addJob(String username, String type, String title, double wage)
	{
		findEmployer(username).addOffer(type, title, wage);
	}
	
	/**
	 * Gets all current job offers
	 * @return String
	 */
	public String getJobs()
	{
		String s = "---------------------------------";
		for(Employer e: employerTable)
		{
			s += e.getOffers();
		}
		s += "\n---------------------------------";
		return s;
	}
	
	/**
	 * Gets all current job offers that have a minimum wage
	 * @return String
	 */
	public String getJobsWage(double wage)
	{
		String s = "---------------------------------";
		for(Employer e: employerTable)
		{
			s += e.getOffersWage(wage);
		}
		s += "\n---------------------------------";
		return s;
	}
	
	/**
	 * Gets all current job offers that have a minimum wage
	 * @return String
	 */
	public String getJobsTitle(String title)
	{
		String s = "---------------------------------";
		for(Employer e: employerTable)
		{
			s += e.getOffersTitle(title);
		}
		s += "\n---------------------------------";
		return s;
	}

	/**
	 * Gets all current job offers that have a minimum wage
	 * @return String
	 */
	public String getJobsType(String type)
	{
		String s = "---------------------------------";
		for(Employer e: employerTable)
		{
			s += e.getOffersType(type);
		}
		s += "\n---------------------------------";
		return s;
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
	
	/**
	 * Based on current time, gives nearest availability of a particular dining center
	 * @param name Of Dining Center
	 * @return String
	 */
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
	
	/**
	 * Fetches a string containing the meal of a particular dining center
	 * @param name Of Dining Center
	 * @return String
	 */
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
	
	/**
	 * Saves data from tables to text files, returns a success message
	 * @return String
	 * @throws IOException
	 */
	public String save() throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		for(student S: studentTable)
			lines.add(S.toFile());
		FileWriter f = new FileWriter(System.getProperty("user.dir") + "\\saves\\student.txt");
		for(String l: lines)
		{
			f.write(l + "\n");
		}
		f.flush();
		f.close();
		lines = new ArrayList<String>();
		for(faculty F: facultyTable)
			lines.add(F.toFile());
		f = new FileWriter(System.getProperty("user.dir") + "\\saves\\faculty.txt");
		for(String l: lines)
		{
			f.write(l + "\n");
		}
		f.flush();
		f.close();
		lines = new ArrayList<String>();
		for(Major C: majorTable)
			lines.add(C.toFile());
		f = new FileWriter(System.getProperty("user.dir") + "\\saves\\major.txt");
		for(String l: lines)
		{
			f.write(l + "\n");
		}
		f.flush();
		f.close();
		lines = new ArrayList<String>();
		for(Group G: groupTable)
			lines.add(G.toFile());
		f = new FileWriter(System.getProperty("user.dir") + "\\saves\\group.txt");
		for(String l: lines)
		{
			f.write(l + "\n");
		}
		f.flush();
		f.close();
		lines = new ArrayList<String>();
		for(Employer E: employerTable)
			lines.add(E.toFile());
		f = new FileWriter(System.getProperty("user.dir") + "\\saves\\employer.txt");
		for(String l: lines)
		{
			f.write(l + "\n");
		}
		f.flush();
		f.close();
		return "Saved!";
	}
	/**
	 * At initialization of database, this variable will load all persistent data into the tables
	 * @throws FileNotFoundException 
	 */
	public void load() throws FileNotFoundException
	{
		loadStudent();
		loadFaculty();
		loadGroup();
		loadMajor();
		loadEmployer();
	}
	
	/**
	 * Helper method for load, specifically handles the studentTable
	 * @throws FileNotFoundException 
	 */
	private void loadStudent() throws FileNotFoundException
	{
		FileReader f = new FileReader(System.getProperty("user.dir") + "\\saves\\student.txt");
		Scanner s = new Scanner(f);
		
		ArrayList<String> input = new ArrayList<String>();
		while(s.hasNextLine())
			input.add(s.nextLine());
		
		String[] attr;
		for(String n: input)
		{
			 attr = n.split(":");
			 student e = new student(attr[1], attr[3], attr[5]);
			 if(attr.length > 7)
			 {
				 e.setClassification(attr[7]);
				 if(attr.length > 9)
				 {
				 	e.setGPA(Double.valueOf(attr[9]));
				 	if(attr.length > 11)
					 {
				 		e.setMajor(attr[11]);
				 		if(attr.length > 13)
				 		{
				 			e.setSwipes(Integer.valueOf(attr[13]));
				 			if(attr.length > 14)
				 			{
				 				for(int i = 14; i < attr.length - 1; i+=2)
				 				{
				 					e.addAttribute(attr[i], attr[i++]);
				 				}
				 			}
				 		}
					 }
				 }
			 }
			studentTable.add(e);
		}
		s.close();
	}
	
	/**
	 * Helper method for load, specifically handles the facultyTable
	 * @throws FileNotFoundException 
	 */
	private void loadFaculty() throws FileNotFoundException
	{
		FileReader f = new FileReader(System.getProperty("user.dir") + "\\saves\\faculty.txt");
		Scanner s = new Scanner(f);
		ArrayList<String> input = new ArrayList<String>();
		while(s.hasNextLine())
			input.add(s.nextLine());
		
		String[] attr;
		for(String n: input)
		{
			 attr = n.split(":");
			 faculty e = new faculty(attr[1], attr[3], attr[5]);
			 if(attr.length > 7)
			 {
				 e.setSalary(Double.valueOf(attr[7]));
				 if(attr.length > 9)
				 {
					 e.setTitle(attr[9]);
				 }
			 }
			 facultyTable.add(e);
		}
		s.close();
	}
	
	/**
	 * Helper method for load, specifically handles the groupTable
	 * @throws FileNotFoundException 
	 */
	private void loadGroup() throws FileNotFoundException
	{
		FileReader f = new FileReader(System.getProperty("user.dir") + "\\saves\\group.txt");
		Scanner s = new Scanner(f);
		
		ArrayList<String> input = new ArrayList<String>();
		while(s.hasNextLine())
			input.add(s.nextLine());
		if(!input.isEmpty())
		{
			String[] attr;
			List<student> l = new ArrayList<student>();
			for(String n: input)
			{
				 attr = n.split(":|");
				 for(int i = 2; i < attr.length; i++)
					 l.add(findStudent(attr[i]));
				 Group e = new Group(attr[1], l);
				 groupTable.add(e);
			}
			s.close();
		}
	}
	
	/**
	 * Helper method for load, specifically handles the majorTable
	 * @throws FileNotFoundException 
	 */
	private void loadMajor() throws FileNotFoundException
	{
		FileReader f = new FileReader(System.getProperty("user.dir") + "\\saves\\major.txt");
		Scanner s = new Scanner(f);
		
		ArrayList<String> input = new ArrayList<String>();
		while(s.hasNextLine())
			input.add(s.nextLine());
		if(!input.isEmpty())
		{
			String[] attr;
			List<faculty> fal = new ArrayList<faculty>();
			List<Major.Class> cls = new ArrayList<Major.Class>();
			List<student> stu = new ArrayList<student>();
			for(String n: input)
			{
				 attr = n.split(":");
				 Major m = new Major(attr[1]);
				 int i = 3;
				 while(attr[i] != "END")
				 {
						fal.add(findFaculty(attr[i]));
						i++;
				 }
				 m.Advisers = fal;
				 i++;
				 while(i < attr.length)
				 {
						Major.Class c = new Major.Class(attr[i]);
						i++;
						c.setInstructor(findFaculty(attr[i]));
						i++;
						while(attr[i] == "END")
						{
							stu.add(findStudent(attr[i]));
							i++;
						}
						c.setAttendance(stu);
						i++;
						cls.add(c);
				 }
				 m.Classes = cls;
				 majorTable.add(m);
			}
			s.close();
		}
	}
	
	/**
	 * Helper method for load, specifically handles the employerTable
	 * @throws FileNotFoundException 
	 */
	private void loadEmployer() throws FileNotFoundException
	{
		FileReader f = new FileReader(System.getProperty("user.dir") + "\\saves\\employer.txt");
		Scanner s = new Scanner(f);
		
		ArrayList<String> input = new ArrayList<String>();
		while(s.hasNextLine())
			input.add(s.nextLine());
		
		String[] attr;
		for(String n: input)
		{
			 attr = n.split(":");
			 Employer e = new Employer(attr[0], attr[1], attr[2], attr[3], attr[4], attr[5]);
			 for(int i = 6; i < attr.length; i += 3)
				 e.addOffer(attr[i], attr[i+1], Double.valueOf(attr[i+2]));
			 employerTable.add(e);
		}
		s.close();
	}
}










