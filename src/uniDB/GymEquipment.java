package uniDB;

public class GymEquipment {
    public String id;
    public String name;
    private String condition;
    private String description;
    public GymEquipment(String id, String name, String condition){
        this.id = id;
        this.name = name;
        this.condition = condition;
        this.description = "";
    }
    public GymEquipment(String id, String name, String cond, String desc){
        super();
        this.description = desc;
    }

    public String toString(){
        String answer = this.id + "\n";
        answer += this.name + "\n";
        answer += this.condition +"\n";
        answer += this.description + "\n";
        return answer;
    }

    public static boolean makeGymEquipment(String id, String name, String condition){
        if (database.findGymEquipment(id) == null){
            GymEquipment newgym = new GymEquipment(id, name, condition);
            database.addGymEquipment(newgym);
            return true;
        }
        return false;
    }
    public void setGymId(String id){ this.id = id;  }
    public void setGymCondition(String condition) {this.condition = condition;}
    public void setGymName(String name) {this.name = name;}
    public void setGymDescription(String desc){this.description = desc;}

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public static void setupgym(){
        String[] ids = {"AS1", "TS1", "BaS1", "BS1", "BoS1", "Ky1", "SB1", "BK1"};
        String[] names = {"Archery Set", "Tennis Set", "Basketball Set", "Baseball Set", "Boxing Set", "Kayak", "Skateboard", "Bike"};
        String[] desc = {"Bow, 12 arrows, target, quiver, arm gruard", "2 rachets, one ball",
                "Basketball", "Bat, baseball, 10 gloves","2 pairs of boxing gloves and boxing helmets",
                "kayak with paddle","Skateboard with helmet","Bicycle with helmet"};
        for (int i =0; i < 8; i++){
            GymEquipment newitem = new GymEquipment(ids[i], names[i], "Good", desc[i]);
            database.addGymEquipment(newitem);
        }
    }
}
