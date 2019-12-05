package uniDB;

import java.util.List;

public class Group {
    private List<student> _group;
    private String groupName;

    public Group(String name, List<student> group){
        _group = group;
        groupName = name;
    }

    /**
     * Gets list of students in group
     * @return List<Student>
     */
    public List<student> getStudents(){
        return _group;
    }

    /**
     * Gets group name
     * @return String
     */
    public String getGroupName(){
        return groupName;
    }

    /**
     * Adds student to group
     * @param s
     */
    public void addToGroup(student s){
        _group.add(s);
    }
    
    /**
     * Creates a string to be able to be written to file
     * @return String
     */
    public String toFile()
    {
    	String s = "Groupname:" + this.groupName + ":";
    	for(student e: _group)
    		s += e.getUsername() + ":";
    	return s;
    }
}
