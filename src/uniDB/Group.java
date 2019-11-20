package uniDB;

import java.util.List;

public class Group {
    private List<student> _group;
    private String groupName;

    public Group(String name, List<student> group){
        _group = group;
        groupName = name;
    }

    public List<student> getStudents(){
        return _group;
    }

    public String getGroupName(){
        return groupName;
    }

    public void addToGroup(student s){
        _group.add(s);
    }
    
    public String toFile()
    {
    	String s = "Groupname:" + this.groupName + ":";
    	for(student e: _group)
    		s += e.getUsername() + ":";
    	return s;
    }
}
