package uniDB;

import java.util.ArrayList;
import java.util.List;

public class Major {

    private String ID;              // exp:  "ComS "
    private List<faculty> Advisers;
    private List<Class> Classes;

    Major(String id){
        this.ID = id;
        this.Advisers = new ArrayList<faculty>();
        this.Classes = new ArrayList<Class>();
    }
    public class Class {                   // Class that is added to Major

        private String ID;          // exp:  "362"
        private faculty Instructor;
        private List<student> Attendance;

        Class(String ID){
            this.ID = ID;
            this.Instructor = null;
            this.Attendance = new ArrayList<student>();
        }
        public String getID() {
            return this.ID;
        }

        public student getStudent(String username){
            for(student stu: this.Attendance){
                if(stu.getUsername().equals(username)){
                    return stu;
                }
            }
            return null;
        }
    }// end class Class                              end class Class

    public String getId() {
        return this.ID;
    }
    public void changeID(String newid) {
        this.ID = newid;
    }
    public List<faculty> getAdviserList(){
        return this.Advisers;
    }
    public faculty findAdviser(String username) {
        for(faculty adv: this.Advisers) {
            if(adv.getUsername().equals(username)) {
                return adv;
            }
        }
        return null;
    } //  end isAdviser
    public boolean addAdviser(String username) {
        faculty adv = database.findFaculty(username);
        if(adv != null) {
            this.Advisers.add(adv);
            return true;
        }
        return false;
    }
    public boolean removeAdvisor(String username) {
        for(faculty adv: this.Advisers) {
            if(adv.getUsername().equals(username)) {
                this.Advisers.remove(adv);
                return true;
            }
        }
        return false;
    }
    public List<Class> getClassList(){
        return this.Classes;
    }
    public Class findClass(String ID) {
        for(Class cl: this.Classes) {
            if(cl.ID.equals(ID)) {
                return cl;
            }
        }
        return null;
    }
    public boolean makeClass(String id) {               //exmple of id = "362"
        if(this.findClass(id) == null) {
            Class newclass = new Class(this.ID + id);
            this.Classes.add(newclass);
            return true;
        }
        return false;
    }
    public boolean removeClass(String id) {
        Class delete = findClass(id);
        if(delete != null) {
            this.Classes.remove(delete);
            return true;
        }
        return false;
    }
    public faculty getClassInstructor(String id) {
        Class cl = this.findClass(id);
        if(cl != null) {return cl.Instructor;}
        return null;
    }
    public boolean addInstructor(String id, String username) {
        Class cl = this.findClass(id);
        faculty fl = database.findFaculty(username);
        if(cl != null && fl != null) {
            cl.Instructor = fl;
            return true;
        }
        return false;
    }
    public boolean removeInstructor(String id) {
        Class cl = this.findClass(id);
        if(cl != null) {cl.Instructor = null; return true;}
        else return false;
    }
    public List<student> getClassAttendance(String id) {
        Class cl = this.findClass(id);
        if(cl != null) {return cl.Attendance;}
        return null;
    }
    public student getClassStudent(String id, String username) {
        Class cl = this.findClass(id);
        if(cl != null) {
            for(student st: cl.Attendance) {
                if(st.username.equals(username)) {return st;}
            }
        }
        return null;
    }
    public boolean addStudent(String id, String username) {
        Class cl = this.findClass(id);
        student st = database.findStudent(username);
        if(cl != null && st != null) {
            cl.Attendance.add(st);
            return true;
        }
        return false;
    }
    public boolean removeStudent(String id, String username) {
        Class cl = this.findClass(id);
        student st = cl.getStudent(username);
        if(st != null){
            cl.Attendance.remove(st);
            return true;
        }
        return false;
    }
}
