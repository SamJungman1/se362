package uniDB;

import java.util.List;
import java.util.ArrayList;

public class database {
	public static List<student> studentTable;
	public static List<faculty> facultyTable;

	public database() {
		studentTable = new ArrayList<student>();
		facultyTable = new ArrayList<faculty>();
	}

	public void addStudent(student s) {
		studentTable.add(s);
	}

	public void addFaculty(faculty f) {
		facultyTable.add(f);
	}

	public void removeStudent(student s) {
		studentTable.remove(studentTable.indexOf(s));
	}

	public void removeFaculty(faculty f) {
		facultyTable.remove(facultyTable.indexOf(f));
	}
	


}
