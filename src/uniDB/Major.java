package uniDB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Mains
 * Group of courses required to earny a degree in this Major. Contains String Identification, List<faculty> Advisers,
 * List<Class> Classes.
 */
public class Major {

	private String ID; // exp: "ComS "
	public List<faculty> Advisers;  //  faculty assigned to advise students in this Major.
	public List<Class> Classes;  //  Classes available to attend in this Major.

	Major(String id) {
		this.ID = id;
		this.Advisers = new ArrayList<faculty>();
		this.Classes = new ArrayList<Class>();
	}
	
	public String toFile()
	{
		String s = "ID:" + this.ID + "|" + "Faculty:";
		for(faculty f: Advisers)
			s += f.username + ":";
		s += "END|";
		for(Class g: Classes)
		{
			s += g.getID() + ":";
			s += g.getInstructor().getUsername() + ":";
			for(student h: g.getAttendance())
			{
				s += h.getUsername() + ":";
			}
			s += "END|";
		}
		
		return s;
	}

	/**
	 * Classes available in a particular Major. Contains String Identification, faculty Instructor,
	 * List<student> Attendance.
	 */
	public static class Class { // Class that is added to Major

		private String ID; // exp: "362"
		private faculty Instructor; // faculty assigned to teach this class.
		private List<student> Attendance;  //  Students assigned to attend course.

		public Class(String ID) {
			this.ID = ID;
			this.Instructor = null;
			this.Attendance = new ArrayList<student>();
		}
		public boolean addstudenttoclass(student stu){
			this.Attendance.add(stu);
			return true;
		}
		public boolean changeclassinstructor(faculty ins){
			this.Instructor = ins;
			return true;
		}
		public faculty getInstructor()
		{
			return this.Instructor;
		}
		public void setInstructor(faculty f)
		{
			this.Instructor = f;
		}
		public List<student> getAttendance()
		{
			return this.Attendance;
		}
		public void setAttendance(List<student> s)
		{
			this.Attendance = s;
		}
		/**
		 * Method return the String Identification for this Class.
		 * @return The String ID of this Class
		 */
		public String getID() {
			return this.ID;
		}

		/**
		 * Method will return student with username who attends this class, null otherwise.
		 * @param username username of student you are looking for
		 * @return student if attends this class
		 */
		public student findStudent(String username){
			for(student st: this.Attendance){
				if(st.username.equals(username)){
					return st;
				}
			}
			return null;
		}
		public String classToString(){
			String answer = this.ID+"\n";
			answer += this.Instructor.getUsername()+"\n";
			for(student stu: this.Attendance){
				answer += stu.getUsername()+" ";
			}
			return answer +="\n";
		}
		public boolean changeClassId(String newid){
			this.ID = newid;
			return true;
		}
	} // end class Class end class Class

	public String majorToString(){
		String answer = this.ID+"\n";
		for(faculty adv: this.Advisers){
			answer += adv.getUsername()+" ";
		}
		answer += "\n";
		for(Class cl: this.Classes){
			answer += cl.getID()+" ";
		}
		return answer+"\n";
	}
	/**
	 * Method returns the String Identification for this Major.
	 * @return The String ID for this Major.
	 */
	public String getId() {
		return this.ID;
	}

	/**
	 * This method will assign a new Identification to this Major.
	 * @param newid The new id that will be assigned to this Major.
	 */
	public void changeID(String newid) {
		this.ID = newid;
	}

	/**
	 * Method  will return a list of faculty assigned to advise in this Major.
	 * @return The list of Advisers for this Major.
	 */
	public List<faculty> getAdviserList() {
		return this.Advisers;
	}

	/**
	 * If faculty with username is assigned as adviser, return faculty else return null.
	 * @param username username of Adviser you are looking for.
	 * @return The Adviser assigned to this Major with provided username.
	 */
	public faculty findAdviser(String username) {
		for (faculty adv : this.Advisers) {
			if (adv.getUsername().equals(username)) {
				return adv;
			}
		}
		return null;
	} // end isAdviser

	/**
	 * Method will add faculty with username, if faculty dose not exist return false.
	 * @param username username of faculty to be added as adviser to Major
	 * @return true if faculty is added as adviser, false otherwise
	 */
	public boolean addAdviser(String username) {
		faculty adv = database.findFaculty(username);
		if (adv != null) {
			this.Advisers.add(adv);
			return true;
		}
		return false;
	}

	/**
	 * Method removes adviser from list with specified username, if adviser does not exist return false.
	 * @param username username of adviser to be removed
	 * @return true if adviser is removed from adviser list, false otherwise
	 */
	public boolean removeAdvisor(String username) {
		for (faculty adv : this.Advisers) {
			if (adv.getUsername().trim().equals(username.trim())) {
				this.Advisers.remove(adv);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method returns list of classes for this Major.
	 * @return The List of classes offered with this Major.
	 */
	public List<Class> getClassList() {
		return this.Classes;
	}

	/**
	 * Method returns class with identification or null if not existent.
	 * @param ID identification of class you are looking for
	 * @return The class you want form this Major or null
	 */
	public Class findClass(String ID) {
		for (Class cl : this.Classes) {
			if (cl.ID.equals(ID)) {
				return cl;
			}
		}
		return null;
	}

	/**
	 * Method will create a new class with specified id or return false if already exists.
	 * @param id identification for new class
	 * @return true if class is created false otherwise
	 */
	public boolean makeClass(String id) { // exmple of id = "362"
		if (this.findClass(id) == null) {
			Class newclass = new Class(id);
			this.Classes.add(newclass);
			return true;
		}
		return false;
	}

	/**
	 * Method will delete class with specified id, will return false if class does not exist.
	 * @param id identification of class to be removed from Major.
	 * @return true of class is removed, false otherwise
	 */
	public boolean removeClass(String id) {
		Class delete = findClass(id);
		if (delete != null) {
			this.Classes.remove(delete);
			return true;
		}
		return false;
	}

	/**
	 * method will return instructor assigned to specified class or null if class does not exist.
	 * @param id identification of class instructor
	 * @return instructor of specified class id, null of class does not exist.
	 */
	public faculty getClassInstructor(String id) {
		Class cl = this.findClass(id);
		if (cl != null) {
			return cl.Instructor;
		}
		return null;
	}

	/**
	 * Will assign instructor to teach class or return false if class or faculty does not exist.
	 * @param id of class to get a new instructor
	 * @param username of instructor to be added
	 * @return true of instructor is assigned to class
	 */
	public boolean addInstructor(String id, String username) {
		Class cl = this.findClass(id);
		faculty fl = database.findFaculty(username);
		if (cl != null && fl != null) {
			cl.Instructor = fl;
			return true;
		}
		return false;
	}

	/**
	 * Method returns true of instructor is removed or false if class does not exist.
	 * @param id of class to loose instructor
	 * @return true of instructor is removed, false otherwise
	 */
	public boolean removeInstructor(String id) {
		Class cl = this.findClass(id);
		if (cl != null) {
			cl.Instructor = null;
			return true;
		} else
			return false;
	}

	/**
	 * Method returns list of students assigned to specified class or null if class does not exist.
	 * @param id identification of the class your want Attendance
	 * @return list of students assigned to this class
	 */
	public List<student> getClassAttendance(String id) {
		Class cl = this.findClass(id);
		if (cl != null) {
			return cl.Attendance;
		}
		return null;
	}

	/**
	 * method returns username student if attends id class, returns null otherwise.
	 * @param id of class student is attending
	 * @param username of student in this class
	 * @return student if attending specified class or null otherwise
	 */
	public student getClassStudent(String id, String username) {
		Class cl = this.findClass(id);
		if (cl != null) {
			for (student st : cl.Attendance) {
				if (st.username.equals(username)) {
					return st;
				}
			}
		}
		return null;
	}

	/**
	 * Method assigns specified student to specified class, returns false of class or student does not exist.
	 * @param id of class to get new student
	 * @param username of student to be assigned to class
	 * @return true of student is assigned to class, false otherwise
	 */
	public boolean addStudent(String id, String username) {
		Class cl = this.findClass(id);
		student st = database.findStudent(username);
		if (cl != null && st != null) {
			cl.Attendance.add(st);
			return true;
		}
		return false;
	}

	/**
	 * Method removes specified student from specified class or returns false if student or class does not exist.
	 * @param id identification of class to loose student
	 * @param username username of student to be removed
	 * @return true of student is removed from class, false otherwise
	 */
	public boolean removeStudent(String id, String username) {

        Class cl = this.findClass(id);
        student st = cl.findStudent(username);
        if(st != null){
            cl.Attendance.remove(st);
            return true;
        }
        return false;
    }
}
