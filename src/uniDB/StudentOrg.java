package uniDB;

import java.util.ArrayList;

/**
 * @author Tyler Lund
 * group of students that form together to create a club with one faculty member serving as the advisor
 */
public class StudentOrg {
    private String orgName;
    private student _president;
    private student _treasurer;
    private faculty _advisor;
    private ArrayList<student> orgMembers;

    public StudentOrg(String name,faculty advisor, student president){
        _advisor = advisor;
        _president = president;
        orgName = name;
        orgMembers = new ArrayList<student>();
    }

    /**
     * method returns true is student object is in ArrayList<student> orgMembers
     * @param member
     * @return
     */
    public boolean isMember(student member){
        if(orgMembers.contains(member)){
            return true;
        }
        return false;
    }

    /**
     * method removes a student object from orgMembers list
     * @param member
     */
    public void removeMember(student member){
        orgMembers.remove(member);
    }

    /**
     * method adds a student obect to orgMemebers list
     * @param member
     */
    public void addMember(student member){
        orgMembers.add(member);
    }

    /**
     * method returns org name
     * @return
     */
    public String getOrgName(){
        return orgName;
    }

    /**
     * method returns all students in orgMember list
     * @return
     */
    public ArrayList<student> getOrgMembers(){
        return orgMembers;
    }

    /**
     * method returns student assigned to president
     * @return
     */
    public student get_president(){
        return _president;
    }

    @Override
    public String toString(){
        String temp =
                "Organization:" + getOrgName() + "\n" +
                "President:" + get_president().getFullname() + "\n" +
                        "Advisor:" + this._advisor.getFullname() + "\n"+
                "Members:";

        for(student member: orgMembers){
            temp += member.fullname + "\n";
        }
        return temp;
    }
}
