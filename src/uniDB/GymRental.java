package uniDB;

import java.util.ArrayList;
import java.util.List;

public class GymRental {
    public user renter;
    private List<GymEquipment> rent;

    public GymRental(user renter){
        this.renter = renter;
        this.rent = new ArrayList<GymEquipment>();
    }
    public GymRental(user renter, GymEquipment item){
        super();
        rent.add(item);
    }

    public user findUser(String username){
        user answer;
        student stu = database.findStudent(username);
        answer = stu;
        faculty fac = database.findFaculty(username);
        answer = fac;
        return answer;
    }
    public void additem(GymEquipment item){
        this.rent.add(item);
    }
    public void removeitem(GymEquipment item){
        this.rent.remove(item);
    }

    public user getRenter(){
        return this.renter;
    }
    public List<GymEquipment> getRent(){
        return this.rent;
    }
    public String rentitem(String renter, String rent){
        user name = findUser(renter); if (name == null){return "invalid user name";}
        GymEquipment equip = database.findGymEquipment(rent); if (equip == null) { return "invalid equipment id";}
        GymRental current = database.findGymRent(renter);
        if (current == null){
            GymRental newrent = new GymRental(name, equip);
            database.addGymRent(newrent);
        }
        current.additem(equip);
        return "item success cheched out to "+ name.getUsername();
    }
    public String checkinitem(String id){
        GymEquipment eq = database.findGymEquipment(id);
        GymRental check = database.findGymRent(id); if (check == null) { return " invalid id or item not checked out";}
        check.removeitem(eq);
        if (check.rent.size() == 0){
            database.removeGymRent(check);
        }
        return "succefful";
    }
    public String userreintlist(String username){
        GymRental gr = database.findGymRent(username); if (gr == null) {return "user rental is clear";}
        List<GymEquipment> ge = gr.getRent();  String answer = "";
        for (GymEquipment item: ge){
            answer += item.toString();
        }
        return answer;
    }
}
