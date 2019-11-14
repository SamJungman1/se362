package uniDB;

import java.util.List;
import java.util.ArrayList;

public class Lot {
    String ID;    //ST for stadium, G for gimnasium, A for auditorium, D for dorm parking, f for faculty parking, S for student parking
    private int spaces;
    List<user> aproved;

    Lot(String id){
        this.ID = id;
        this.spaces = 0;
    }
    Lot(String id, int spaces){
        super();
        this.spaces = spaces;
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
    public boolean makeLot(String id, int spaces){
        if(database.findLot(id) != null){
            Lot newlot = new Lot(id, spaces);
            database.addLot(newlot);
            return true;
        }
        return false;
    }
    public boolean removeLot(String id){
        Lot delete = database.findLot(id);
        if(delete != null){
            database.removeLot(delete);
            return true;
        }
        return false;
    }
} // end Lot