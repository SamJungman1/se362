package uniDB;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class database {
	public static List<student> studentTable;
	public static List<faculty> facultyTable;
	public static List<Group> groupTable;
	public static List<Major> majorTable;

	public database() {
		studentTable = new ArrayList<student>();
		facultyTable = new ArrayList<faculty>();
		groupTable = new ArrayList<Group>();
		majorTable = new ArrayList<Major>();
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
			if(f.getUsername().equals(username))
				return f;
		}
		return null;
	}
	
	public static student findStudent(String id)
	{
		student temp = null;
		for(student s: studentTable)
		{
			System.out.println(s.getId());
			if(s.getId().equalsIgnoreCase(id))
				temp = s;
			break;
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
}
