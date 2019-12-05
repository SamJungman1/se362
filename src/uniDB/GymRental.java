package uniDB;

import java.util.ArrayList;
import java.util.List;

/**
 * @ author Christian Mains
 * creates an instance of an item or items rented out to a university user.
 */
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

    /**
     * Helper Method, called by rent item, returns user of username, if they exist
     * @param username
     * @return
     */
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

    /**
     * Rents out item with rent id to user with renter username if both exists.
     * @param renter username of person renting out item.
     * @param rent id if item to be rented
     * @return String identifying success or type of failure
     */
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

    /**
     * checks in item from user who it is checked out to
     * @param id of item to be cheched in.
     * @return String identifying successs or type of failure
     */
    public String checkinitem(String id){
        GymEquipment eq = database.findGymEquipment(id);
        GymRental check = database.findGymRent(id); if (check == null) { return " invalid id or item not checked out";}
        check.removeitem(eq);
        if (check.rent.size() == 0){
            database.removeGymRent(check);
        }
        return "succefful";
    }

    /**
     * Displays all item schiched out to this particular user.
     * @param username of person who has items checked out.
     * @return String of items checked out or statement of user has no items checked out.
     */
    public String userrentlist(String username){
        GymRental gr = database.findGymRent(username); if (gr == null) {return "user rental is clear";}
        List<GymEquipment> ge = gr.getRent();  String answer = "";
        for (GymEquipment item: ge){
            answer += item.toString();
        }
        return answer;
    }
}
