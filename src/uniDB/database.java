package uniDB;

import java.util.List;
import java.util.ArrayList;

public class database {
	public static List<student> studentTable;
	public static List<faculty> facultyTable;
	public static List<Major> majorTable;

	public database() {
		studentTable = new ArrayList<student>();
		facultyTable = new ArrayList<faculty>();
		majorTable = new ArrayList<Major>();
	}

	public void addStudent(student s) {
		studentTable.add(s);
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
	
	public faculty findFaculty(String username)
	{
		for(faculty f: facultyTable)
		{
			if(f.getUsername().equals(username))
				return f;
		}
		return null;
	}
	
	public student findStudent(String username)
	{
		for(student s: studentTable)
		{
			if(s.getUsername().equals(username))
				return s;
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
