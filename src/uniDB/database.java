package uniDB;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class database {
	public static List<student> studentTable;
	public static List<faculty> facultyTable;
	public static List<Group> groupTable;
	public static List<Major> majorTable;
	public static List<Dorm> dormTable;
	public static List<Lot> parkingLots;

	public database() {
		studentTable = new ArrayList<student>();
		facultyTable = new ArrayList<faculty>();
		groupTable = new ArrayList<Group>();
		majorTable = new ArrayList<Major>();
		dormTable = new ArrayList<Dorm>();
		parkingLots = new ArrayList<Lot>();
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

	public static void addLot(Lot l) {parkingLots.add(l);}

	public static Lot findLot(String id){
		for(Lot lot: parkingLots){
			if(lot.getID().equals(id)){
				return lot;
			}
		}
		return null;
	}

	public static void removeLot(Lot l){parkingLots.remove(l);}
	
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


}
