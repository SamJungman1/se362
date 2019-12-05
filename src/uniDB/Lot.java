package uniDB;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Christian Mains
 * management of physical parking lots on campus to ensure all assigned persons have a parking spot.
 */
public class Lot {
    String ID;    //ST for stadium, G for gimnasium, A for auditorium, D for dorm parking, f for faculty parking, S for student parking
    private int spaces;
    List<user> parkers;

    Lot(String id){
        this.ID = id;
        this.spaces = 0;
        this.parkers = new ArrayList<user>();
    }
    Lot(String id, int spaces){
        super();
        this.spaces = spaces;
    }
    public void changLotId(String id){
        this.ID = id;
    }

    /**
     * adds designated person to parking lot
     * @param id username of person to be added as designated parker.
     * @return true if person was added to parking designation, false other wise.
     */
    public boolean addparker(String id){
        user looser = database.findFaculty(id);
        if(looser != null){ this.parkers.add(looser); return true;}
        looser = database.findStudent(id);
        if(looser != null){this.parkers.add(looser); return true;}
        return false;
    }

    /**
     * removes designated person from parking permission list.
     * @param id username of person to be removed.
     * @return true if person is removed, false otherwise
     */
    public boolean removeparker(String id){
        for(user looser: this.parkers){
            if(looser.getId().equals(id)){
                this.parkers.remove(looser);
                return true;
            }
        }
        return false;
    }
    public void generatelots(){
        int i = 0;
        String ids = "";
        String[] id = {"ST", "G", "A", "D", "F", "S"};
        int stadium = 500;
        for(i = 0; i < 6; i++){
            ids = id[0]+i;
            Lot newlot = new Lot(ids, stadium);
            database.addLot(newlot);
        }
        int gym = 200;
        for(i = 0; i < 6; i++){
            ids = id[1]+i;
            Lot newlot = new Lot(ids, gym);
            database.addLot(newlot);
        }
        int aut = 150;
        for(i = 0; i < 6; i++){
            ids = id[2]+i;
            Lot newlot = new Lot(ids, aut);
            database.addLot(newlot);
        }
        int dorms = 300;
        for(i = 0; i < 6; i++){
            ids = id[3]+i;
            Lot newlot = new Lot(ids, dorms);
            database.addLot(newlot);
        }
        int faculty = 50;
        for(i = 0; i < 6; i++){
            ids = id[4]+i;
            Lot newlot = new Lot(ids, faculty);
            database.addLot(newlot);
        }
        int student = 600;
        for(i = 0; i < 6; i++){
            ids = id[5]+i;
            Lot newlot = new Lot(ids, student);
            database.addLot(newlot);
        }
    }
    public String getID(){
        return this.ID;
    }

    /**
     * adds spaces to parking lot number of spaces
     * @param s number of spaces to be added
     */
    public void addLotSpace(int s){
        this.spaces = spaces + s;
    }

    /**
     * removes spaces from parking lot number of spaces
     * @param s number of spaces to be removed
     */
    public void removeLotspace(int s){
        this.spaces = spaces - s;
    }

    /**
     * creates lot with designated id
     * @param id to be assigned to new parking lot
     * @return true if lot is created, false otherwise
     */
    public static boolean makeLot(String id){
        if(database.findLot(id) == null){
            Lot newlot = new Lot(id);
            database.addLot(newlot);
            return true;
        }
        return false;
    }

    /**
     * deletes lot from data base and all spaces there in.
     * @param id of lot to be deleted
     * @return true of lot is deleted, false otherwise.
     */
    public boolean removeLot(String id){
        Lot delete = database.findLot(id);
        if(delete != null){
            database.removeLot(delete);
            return true;
        }
        return false;
    }
} // end Lot