package uniDB;

import java.util.ArrayList;

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

    public boolean isMember(student member){
        if(orgMembers.contains(member)){
            return true;
        }
        return false;
    }

    public void removeMember(student member){
        orgMembers.remove(member);
    }

    public void addMember(student member){
        orgMembers.add(member);
    }

    public String getOrgName(){
        return orgName;
    }

    public ArrayList<student> getOrgMembers(){
        return orgMembers;
    }

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
