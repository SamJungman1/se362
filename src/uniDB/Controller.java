package uniDB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tyler Lund
 *
 * Controller is the controller responsible for reading and parsing the user input
 */

public class Controller {

    /**
     * stored instance of available commands
     */
    private List<String> commands;
    public static database db;
    public String user;
    private Library library;

    public Controller() throws FileNotFoundException{
        db = new database();

        library = new Library();
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(11));
        rooms.add(new Room(12));
        db.addDorm(new Dorm(rooms, "Friley"));
        db.majorTable.add(new Major("ComS"));
//        db.getDorm("Friley").createRooms(0, 10, 0);

        commands = new ArrayList<>();
        commands.add("get student");
        commands.add("remove student");
        commands.add("add student");
        commands.add("create group");
        commands.add("edit group");
        commands.add("edit student");
        commands.add("pay tuition");
        commands.add("make major");
        commands.add("add adviser");
        commands.add("make class");
        commands.add("change major id");
        commands.add("remove adviser");
        commands.add("change class id");
        commands.add("change class instructor");
        commands.add("add student to class");
        commands.add("add faculty");
        commands.add("remove faculty");
        commands.add("get faculty");
        commands.add("msg student");
        commands.add("msg faculty");
        commands.add("getMsg student");
        commands.add("getMsg faculty");
        commands.add("create dorm");
        commands.add("list dorms");
        commands.add("select housing");
        commands.add("add book");
        commands.add("remove book");
        commands.add("list books");
        commands.add("get overdue books");
        commands.add("check in book");
        commands.add("check out book");
        commands.add("make lot");
        commands.add("add lot space");
        commands.add("remove lot space");
        commands.add("add parker");
        commands.add("remove parker");
        commands.add("change lot id");
        commands.add("list major");
        commands.add("list class");
        commands.add("get swipes");
        commands.add("use swipe");
        commands.add("check times");
        commands.add("check meal");
        commands.add("save");
        commands.add("apply");
        commands.add("review app");
        commands.add("add employer");
        commands.add("get employer");
        commands.add("add offer");
        commands.add("get offers");
        commands.add("get offers wage");
        commands.add("get offers title");
        commands.add("get offers type");
        commands.add("create student org");
        commands.add("add student to org");
        commands.add("msg Org");
        commands.add("show org");
        commands.add("add bus");
        commands.add("remove bus");
        commands.add("deploy bus");
        commands.add("add route");
        commands.add("remove route");
        commands.add("add stop");
        commands.add("remove stop");
        commands.add("display bus");
        commands.add("display route");
        commands.add("create fair");
        commands.add("register company");
        commands.add("remove fair");
        commands.add("display fair");
        commands.add("add sport");
        commands.add("find sport");
        commands.add("add team to sport");
        commands.add("add player to team");
        commands.add("add win to team");
        commands.add("add loss to team");
        commands.add("add tie to team");
        commands.add("make gym equipment");
        commands.add("delete gym equipment");
        commands.add("set up gym");
        commands.add("list gym");
        commands.add("change gym id");
        commands.add("change gym name");
        commands.add("change gym condition");
        commands.add("change gym description");
        commands.add("check out gym");
        commands.add("check in gym");
        commands.add("user rent list");
    }


    /**
     * This method is used to verify that given a username and password there exists a
     * instance of a user that has the corresponding username and password
     * @param username
     * @param password
     * @return
     */
    protected boolean login(String username, String password) {

        //user user = null;
        student stu = db.findStudent(username);
        faculty fac = db.findFaculty(username);
        Employer emp = db.findEmployer(username);
        boolean login = false;

        if(stu != null) {
        	user = username;
        	login = stu.login(username, password);
        	if(login) {
        		return login;
        	} else {
        		System.out.println("Login Failed, invalid credentials");
        		return false;
        	}
        } else if(fac != null) {
        	user = username;
        	login = fac.login(username, password);
        	if(login) {
        		return login;
        	} else {
        		System.out.println("Login Failed, invalid credentials");
        		return false;
        	}
        } else if(emp != null) {
        	user = username;
        	login = emp.login(username, password);
        	if(login) {
        		return login;
        	} else {
        		System.out.println("Login Failed, invalid credentials");
        		return false;
        	}
        } else {
        	System.out.println("Login Failed, invalid credentials");
        	return false;
        }
//        if(user != null) {
//            return user.login(username, password);
//        } else {
//        	System.out.println("Login Failed, invalid credentials");
//            return false;
//        }
    }

    /**
     * This method is responsible for parsing commands given through the cmdView and invoking the correct methods
     * @param command
     * @return object string of executed command or error
     */

    public Object parseCommand(String command){
        Pattern pattern = Pattern.compile("^" + createRegexFromList(commands, false) + ":");
        Matcher match = pattern.matcher(command);
        if(match.find()){
            MatchResult result = match.toMatchResult();
            try {
            switch(match.group(0)) {
                case "get student:":
                    String studentsFound = "";
                    for(student x: findStudents(command))
                    {
                        if(x == null){
                            studentsFound += "failed to find student";
                        }
                        else {
                            studentsFound += x.toString() + "\n";
                        }
                    }
                    if(studentsFound != null) {
                        return studentsFound;
                    }
                    else{
                        return "Error finding one or more students with given id's";
                    }
                case "display fair:":
                    return displayFair(command);
                case "register company:":
                    return registerCompany(command);
                case "create fair:":
                    return createFair(command);
                case "remove fair:":
                    return removeFair(command);
                case "display route:":
                    return displayRoute(command);
                case "remove route:":
                    return removeRoute(command);
                case "display bus:":
                    return displayBus(command);
                case "add stop:":
                    return addStop(command);
                case "remove stop:":
                    return removeStop(command);
                case "add route:":
                    return addRoute(command);
                case "add bus:":
                    return addBus(command);
                case "deploy bus:":
                    return deployBus(command);
                case "add book:":
                    return addBook(command);
                case "remove book:":
                    return removeBook(command);
                case "check in book:":
                    return checkIn(command);
                case "check out book:":
                    return checkOut(command);
                case"get overdue books:":
                    return overDueBooks(command);
                case "list books:":
                    return listBooks(command);
                case "get faculty:":
                    return findFaculty(command);
                case "remove student:":
                    return removeStudent(command);
                case"add faculty:":
                    return createFaculty(command);
                case"remove faculty:":
                    return removeFaculty(command);
                case"add student:":
                    return createStudent(command);
                case"edit student:":
                        return editField(command);
                case"edit group:":
                    if(findGroup(command) != null) {
                        //edit group:groupName field fieldValue
                        editField(findGroup(command), command);
                        return "updated values for group:" + findGroup(command).getGroupName();
                    }
                    else{
                        return "Could not find group with that name";
                    }
                case"create group:":
                    if(createGroup(command) != null) {
                        database.addGroup(createGroup(command));
                        return "successfully added group";
                    }
                    else {return "one or more invalid id's, please retry";}

                case "pay tuition:":
                	student stu = db.findStudent(user);
                	stu.payTuition(command.substring(13));
                    return "Tuition Payed";

                case "msg student:":
                	String name = null;
                    String com = command.replaceFirst("(.*?)\\:", "");
                    Pattern reg = Pattern.compile("([^\\s]+)");
                    Matcher matcher = reg.matcher(com);
                    if(matcher.find() && !matcher.group(0).trim().equals("")){
                        name = matcher.group(0);
                        com = com.replace(name, "");
                    }
                	db.msgStudent(name, user + ": " + com);
                	return "Sent!";

                case "msg faculty:":
                	String namer = null;
                    String comm = command.replaceFirst("(.*?)\\:", "");
                    Pattern rege = Pattern.compile("([^\\s]+)");
                    Matcher matchers = rege.matcher(comm);
                    if(matchers.find() && !matchers.group(0).trim().equals("")){
                        namer = match.group(0);
                        comm = comm.replace(namer, "");
                    }
                	db.msgFacutly(namer, user + ": " + comm);
                	return "Sent!";

                case "getMsg student:":
                	return db.getMsgsStudent(user);

                case "getMsg faculty:":
                	return db.getMsgsStudent(user);
                	
                case "add sport:":
                	IntramuralSport sport = new IntramuralSport(command.substring(10).trim());
                	db.addSport(sport);
                	return "Success";
                	
                case "find sport:":
                	if(command.length() < 13)
                		return "Invalid command";
                	return db.findSport(command.substring(11)).printTeams();
                	
                case "add team to sport:":
                	String sportCom = command.substring(18);
                	String[] sportSplit = sportCom.split(",");
                	if(sportSplit.length < 2)
                		return "Invalid command";
                	db.addTeamToSport(sportSplit[0], sportSplit[1]);
                	return "Success";
                	
                case "add player to team:":
                	String sportCom2 = command.substring(19);
                	String[] sportSplit2 = sportCom2.split(",");
                	if(sportSplit2.length < 3)
                		return "Invalid command";
                	db.addPlayerToTeamToSport(sportSplit2[0], sportSplit2[1], sportSplit2[2]);
                	return "Success";
                	
                case "add win to team:":
                	String winCom = command.substring(16);
                	String[] winSplit = winCom.split(",");
                	if(winSplit.length < 2)
                		return "Invalid command";
                	db.addWLTtoTeamToSport(winSplit[0], winSplit[1], 0);
                	return "Success";
                	
                case "add loss to team:":
                	String lossCom = command.substring(17);
                	String[] lossSplit = lossCom.split(",");
                	if(lossSplit.length < 2)
                		return "Invalid command";
                	db.addWLTtoTeamToSport(lossSplit[0], lossSplit[1], 1);
                	return "Success";
                	
                case "add tie to team:":
                	String tieCom = command.substring(16);
                	String[] tieSplit = tieCom.split(",");
                	if(tieSplit.length < 2)
                		return "Invalid command";
                	db.addWLTtoTeamToSport(tieSplit[0], tieSplit[1], 2);
                	return "Success";

                case "use swipe:":
                	return db.useSwipe(user);

                case "get swipes:":
                	return db.getMealSwipes(user);

                case "check times:":
                    return db.checkDiningCenter(command.substring(13));

                case "check meal:":
                    return db.getMeal(command.substring(12));
                case "save:":
				try {
					String s = db.save();
				} catch (IOException e) {
					e.printStackTrace();
					return "Save files not found";
				}
                	return "Saved!";
                
                case "add employer:":
                	String s = command.substring(13);
                	String[] cases = s.split(",");
                	if(cases.length < 6)
                		return "Invalid employer command";
                	Employer e = new Employer(cases[0],cases[1],cases[2],cases[3],cases[4],cases[5]);
                	db.addEmployer(e);
                	return "Success";
                	
                case "get employer:":
                	return db.findEmployer(command.substring(12)).toFile();
                	
                case "add offer:":
                	String of = command.substring(10);
                	String[] offerDetails = of.split(",");
                	if(offerDetails.length < 3)
                		return "Invalid command";
                	db.addJob(user, offerDetails[0], offerDetails[1], Double.valueOf(offerDetails[2]));
                	return "Success";
                	
                case "get offers:":
                	return db.getJobs();
                	
                case "get offers wage:": 
                	return db.getJobsWage(Double.valueOf(command.substring(16).trim()));
                	
                case "get offers title:": //not working
                	return db.getJobsTitle(command.substring(17).trim());
                
                case "get offers type:": //not working
                	return db.getJobsType(command.substring(16).trim());
                
                case "make major:":
                    String mc = command.replaceFirst("make major:", "").trim();
                    if(database.findMajor(mc) == null){
                        Major newmajor = new Major(mc);
                        db.addMajor(newmajor);
                        return "major "+newmajor.getId()+" has been made.";
                    }
                    return "major already exists!";
                case "add adviser:": // major = args[0]     faculty = args[1]
                    mc = command.replaceFirst("add adviser:", "");
                    String[] argtwo = mc.trim().split(" ");   // [0] Major   [1] addviser username
                    faculty adv = db.findFaculty(argtwo[1]); Major major1 = db.findMajor(argtwo[0]);
                    if(major1 == null){return argtwo[0]+" is an invalid major id. Please use a valid id.";}
                        if(adv == null){return argtwo[1]+" is an invalid username. Please use a valid username.";}
                            if(major1.addAdviser(argtwo[1])){return argtwo[1]+" has been added as adviser to "+argtwo[0]+" major.";}
                            else{return "addition of "+argtwo[1]+" to adviser list has failed.";}
                case "make class:":
                    mc = command.replaceFirst("make class:", "");
                    argtwo = mc.trim().split(" ");  // [0] major  [1] new class
                    Major ma = db.findMajor(argtwo[0]);
                    if (ma == null) {return"major entered is invalid";}
                    if(ma.makeClass(argtwo[1])){return "class "+argtwo[1]+" has been created";}
                    else{return "Failed to make class";}
                case "change major id:":
                    mc = command.replaceFirst("change major id:", "");
                    argtwo = mc.trim().split(" ");  //[0] major old id  [1] major new id
                    ma = db.findMajor(argtwo[0]); if(ma == null){return "major id is invalid";}
                    ma.changeID(argtwo[1]); return "major id has been changed to "+argtwo[1];
                case "remove adviser:":
                    mc = command.replaceFirst("remove adviser:", "");
                    argtwo = mc.trim().split(" "); //  [0] major  [1] username of adviser to be removed
                    ma = db.findMajor(argtwo[0]); if(ma == null){return "major id is invalid";}
                    adv = db.findFaculty(argtwo[1]); if(adv == null){return "username of adviser does not exist";}
                    if(ma.removeAdvisor(argtwo[1])){return "adviser "+argtwo[1]+" has been removed";}
                    else{return "failed to remove adviser";}
                case "change class id:":
                    mc = command.replaceFirst("change class id:", "");
                    String[] argthree = mc.trim().split(" "); // [0] major  [1] old class id [2] new class id
                    ma = db.findMajor(argthree[0]); if(ma == null){return "major id is invalid";}
                    Major.Class cl = ma.findClass(argthree[1]); if(cl == null){return "class id is invalid";}
                    if(cl.changeClassId(argthree[2])){return "class id has been changed to "+argthree[2];}
                case "change class instructor:":
                    mc = command.replaceFirst("change class instructor:", "");
                    argthree = mc.trim().split(" "); // [0] = major id [1] = class id [2] = faculty user name
                    ma = db.findMajor(argthree[0]); if(ma == null){return "major id is invalid";}
                    cl = ma.findClass(argthree[1]); if(cl == null){return "class id is invalid";}
                    adv = db.findFaculty(argthree[2]); if(adv == null){return "instructor username is invalid";}
                    if(cl.changeclassinstructor(adv)){return "class "+argthree[1]+" instructor has been changed to "+argthree[2];}
                case "add student to class:":
                    mc = command.replaceFirst("add student to class:", "");
                    argthree = mc.trim().split(" "); // [0] = major id [1] = class id [2] = student username
                    ma = db.findMajor(argthree[0]); if(ma == null){return "major id is invalid";}
                    cl = ma.findClass(argthree[1]); if(cl == null){return "class id is invalid";}
                    student st = db.findStudent(argthree[2]); if(st == null){return "student username is invalid";}
                    if(cl.addstudenttoclass(st)){ return "student "+argthree[2]+" successfully added to class "+argthree[1];}
                case"make lot:":
                    mc = command.replaceFirst("make lot:", "").trim();
                    if(Lot.makeLot(mc )) { return "lot "+mc+" has been created.";}
                    return "creation of lot "+mc+" has failed.";
                case"change lot id:":
                    mc = command.replaceFirst("change lot id:", "").trim();
                    argtwo = mc.split(" ");  // [0] old lot id    [1] new lot id
                    Lot lot = db.findLot(argtwo[0]); if(lot == null) {return "Lot id is invalid.";}
                    lot.changLotId(argtwo[1]);
                    return "Lot id has been changed to "+argtwo[1];
                case"remove lot space:":
                    mc = command.replaceFirst("remove lot space:", "").trim();
                    argtwo = mc.split(" ");  // [0] lot id   [1] number of spaces to be removed from lot spaces
                    lot = db.findLot(argtwo[0]); if(lot == null) {return "Lot id is invalid.";}
                    lot.removeLotspace(Integer.valueOf(argtwo[1]));
                    return "Lot spaces removed.";
                case"add lot space:":
                    mc = command.replaceFirst("add lot space:", "").trim();
                    argtwo = mc.split(" ");  // [0] lot id   [1] number of spaces to be add to lot spaces
                    lot = db.findLot(argtwo[0]); if(lot == null) {return "Lot id is invalid.";}
                    lot.addLotSpace(Integer.valueOf(argtwo[1]));
                    return "Lot spaces added.";
                case "add parker:" :
                    mc = command.replaceFirst("add parker:", "").trim();
                    argtwo = mc.split(" ");  // [0] lot id   [1] username of user to be added
                    lot = db.findLot(argtwo[0]); if(lot == null) {return "Lot id is invalid.";}
                    if(lot.addparker(argtwo[1])){return "Parker successfully added.";}
                    return "failed to add parker";
                case "remove parker:":
                    mc = command.replaceFirst("remove parker:", "").trim();
                    argtwo = mc.split(" ");  // [0] lot id   [1] username of user to be removed
                    lot = db.findLot(argtwo[0]); if(lot == null) {return "Lot id is invalid.";}
                    if(lot.removeparker(argtwo[1])){ return "Parker successfully removed.";}
                    return "failed to remove parker";
                case "list major:":
                    mc = command.replaceFirst("list major:", "").trim();
                    ma = db.findMajor(mc); if (ma == null){ return "major id is invalid";}
                    String str = ma.majorToString();
                    System.out.print(str);
                    return "end of Major list";
                case "list class:":
                    mc = command.replaceFirst("list class:", "");
                    argtwo = mc.trim().split(" ");  // [0] major  [1] new class //NOTE CHANGED AN MC TO A CMI
                    ma = db.findMajor(argtwo[0]); if(ma == null){return "major id is invalid";}
                    cl = ma.findClass(argtwo[1]); if(cl == null){return "class id is invalid";}
                    str = cl.classToString();
                    System.out.print(str);
                    return "end of Class list";
                case "make gym equipment:":
                    mc = command.replaceFirst("make gym equipment:","");
                    argthree = mc.trim().split(",");  //[0] id [1] name  [2] condition
                    if (GymEquipment.makeGymEquipment(argthree[0].trim(), argthree[1].trim(), argthree[2].trim())){
                        return "Gym equipment "+argthree[0]+" and name "+argthree[1]+ " has been created";
                    }
                    return "failed to create Gym equipment "+argthree[0];
                case "delete gym equipment:":
                    mc = command.replaceFirst("delete gym equipment:","").trim();
                    name = db.deleteGymItem(mc);
                    return name;
                case "set up gym:":
                    GymEquipment.setupgym();
                    return "Gym is set up.";
                case "list gym:":
                    mc = command.replaceFirst("list gym:", "").trim();
                    if (mc.equals("all")){
                        String answer = "";
                        for (GymEquipment gym: db.gymequip){
                            answer += gym.toString();
                        }
                        return answer += "end of gymEquipment list";
                    }
                    GymEquipment ge = db.findGymEquipment(mc); if (ge == null) {return "GymEquipment id is invalid"; }
                    return ge.toString();
                case "change gym id:":
                    mc = command.replaceFirst("change gym id:", "");
                    argtwo = mc.trim().split(","); // [0] old id  [1] new id
                    ge = db.findGymEquipment(argtwo[0]); if (ge == null) {return "Equipment id does not exist"; }
                    ge.setGymId(argtwo[1]);
                    return "GymEquipment id "+argtwo[0] + " has been changed to " + argtwo[1];
                case "change gym name:":
                    mc = command.replaceFirst("change gym name:", "");
                    argtwo = mc.trim().split(",");  // [0] gym id  [1] gym new name
                    ge = db.findGymEquipment(argtwo[0]); if (ge == null) {return "GymEquipment id is invalid"; }
                    ge.setGymName(argtwo[1]); return "GymEquipment "+argtwo[0]+" name has been changed to "+argtwo[1];
                case "change gym condition:":
                    mc = command.replaceFirst("change gym condition", "");
                    argtwo = mc.trim().split(",");  // [0] gym id  [1] gym new condition
                    ge = db.findGymEquipment(argtwo[0]); if (ge == null) {return "GymEquipment id is invalid"; }
                    ge.setGymCondition(argtwo[1]); return "GymEquipment "+argtwo[0]+" condition has been changed to "+argtwo[1];
                case "change gym description:":
                    mc = command.replaceFirst("change gym description:","");
                    argtwo = mc.trim().split(",");  // [0] gym id  [1] gym new description
                    ge = db.findGymEquipment(argtwo[0]); if (ge == null) {return "GymEquipment id is invalid"; }
                    ge.setGymDescription(argtwo[1]); return "GymEquipment "+argtwo[0]+" description has been changed to "+argtwo[1];
                case "check out gym:":
                    mc = command.replaceFirst("check out gym:","");
                    argtwo = mc.trim().split(" "); // [0] renter username  [1] rent id
                    return GymRental.rentitem(argtwo[0].trim(), argtwo[1].trim());
                case "check in gym:":
                    mc = command.replaceFirst("check in gym:","").trim();
                    return GymRental.checkinitem(mc);
                case "user rent list:":
                    mc = command.replaceFirst("user rent list:","").trim();
                    return GymRental.userrentlist(mc);
                case "create student org:":
                    return createStudentOrg(command);
                case "add student to org:":
                    return addStudentToOrg(command);
                case "msg Org:":
                   return msgOrg(command);
                case "show org:":
                       return findStudentOrg(command);
                case "create dorm:":
                	db.addDorm(new Dorm(command.substring(13)));
                	return "Dorm created";

                case "list dorms:":
                	db.listDorms();
                	return "done";
                case "select housing:":

                	String dormName = command.substring(16);
                	Dorm dorm = db.getDorm(dormName);
                	if(dorm != null) {
                		dorm.listOpenRooms();
                		Scanner scan = new Scanner(System.in);

                		while(true) {
                			System.out.print("Select room by id: ");
                			String id = scan.next();
                			Room room = dorm.getRoomById(Integer.parseInt(id));

                			if(room != null) {
                				room.changeCapacity(room.getCapacity() - 1);
                				return "Added to room " + id;
                			} else {
                				System.out.println("Room not found");
                			}
                		}
                	} else {
                		return "Dorm not found";
                	}

                case "apply:":
                	student stud = db.findStudent(user);

                	if(stud == null) {
                		return "Cannot Apply, not a student";
                	}

				try {
					stud.apply();
				} catch (FileNotFoundException excep) {
					// TODO Auto-generated catch block
					excep.printStackTrace();
				} catch (UnsupportedEncodingException excep) {
					// TODO Auto-generated catch block
					excep.printStackTrace();
				}
                	return "done";

                case "review app:":
                	faculty fac = db.findFaculty(user);

                	if(fac == null) {
                		return "Cannot review applications, not a faculty member";
                	}

				try {
					fac.reviewApp();
				} catch (FileNotFoundException excep) {
					// TODO Auto-generated catch block
					excep.printStackTrace();
				}


                	return "done";

            }
            
            }
            catch(NullPointerException ne)
            {
            	System.out.println("Invalid Command");
            }
        }
        return "invalid command";
    }

    /**
     * This method edits a students attributes by calling the findStudent method of the database object
     * and the editAttribute method of the student object
     * @param command
     */
    public String editField(String command){
        String name = null;
        String com = command.replaceFirst("(.*?)edit student\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        String[] args = com.split(" ");
        if(name != null && !findStudents(name).isEmpty()) {
            if(args[1].equalsIgnoreCase("Major")){
                if(db.findMajor(args[2]) != null) {
                    findStudents(name).get(0).setMajor(args[2]);
                }
                else{
                    return "Major does not exist";
                }
            }
            else if(args[1].equalsIgnoreCase("gpa")){
                findStudents(name).get(0).setGPA(Double.parseDouble(args[2]));
            }
            else if(args[1].equalsIgnoreCase("classification")){
                findStudents(name).get(0).setClassification(args[2]);
            }
            else {
                findStudents(name).get(0).editAttribute(args[1], args[2]);
            }
        }
        return "successfully updated student";
    }


    /**
     * This method edits multiple student attributes by calling the findGroup method of the database object
     * and then the findStudent object for each student object within the Group object and then finally the editAttribute method in the
     * student object for each instance of student
     * @param group
     * @param command
     */
    public void editField(Group group, String command){
        String name;
        String com = command.replaceFirst("(.*?)edit group\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        String[] args = com.split(" ");
        for(student s: group.getStudents()){
            s.editAttribute(args[1], args[2]);
        }
    }

    /**
     * This method is responsible for creating a list of students and adding a Group object to the database if all instances of students
     * were found within the database
     * @param command
     * @return Group object containing list of students given from command
     */
    public Group createGroup(String command){
        List<student> found = new ArrayList<>();
        Group group;
        String name = "";
        String com = command.replaceFirst("(.*?)create group\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        else{
           return null;
        }
        for (String studentId : com.split(",")) {
            if (database.findStudent(studentId.trim()) == null) {
                //student not found
                //group = null;
               // return group;
            }
            else{
                found.add(database.findStudent(studentId));
            }
        }
        group = new Group(name, found);
        return group;
    }
    
    /**
     * 
     * @param command
     * Helper method to parse and run the command that adds students to StudentOrgs and returns success information
     * @return String
     */
    public String addStudentToOrg(String command){
        String name = "";
        String com = command.replaceFirst("(.*?)\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        StudentOrg temp = findStudentOrg(name);
        if(temp != null){
            student tempStudent = db.findStudent(com);
            if(tempStudent != null){
                if(temp.isMember(tempStudent)){
                    return "student is already a member of org";
                }
                else {
                    temp.addMember(tempStudent);
                    return "added " + tempStudent.getUsername() + " to " + temp.getOrgName();
                }
            }
            else{
                return "no student with the username " + com;
            }
        }
        else{
            return "no org found with the name " + name;
        }
    }
    
    /**
     * 
     * @param command
     * Helper method to parse and run the command that creates StudentOrgs and returns success information
     * @return String
     */
    public String createStudentOrg(String command) {
        String[] args;
        String com = command.replaceFirst("(.*?)\\:", "");
        if (com.contains(",")) {
            args = com.split(",");
            StudentOrg temp = findStudentOrg(args[0]);
            if (temp != null) {
                return "student org with the name " + args[0] + " already exists";
            } else {
                if(database.findFaculty(args[1]) == null){
                    return "no faculty by that username";
                }
                else {
                    temp = new StudentOrg(args[0], database.findFaculty(args[1]), database.findStudent(user));
                    db.addStudentOrg(temp);
                    return "added student org";
                }
            }
        }
        return "invalid parameters. create student org:name,advisor";
    }
    /**
     * 
     * @param command
     * Helper method to parse and run the command that messages StudentOrgs and returns success information
     * @return String
     */
    public String msgOrg(String command){
        String name = "";
        String com = command.replaceFirst("(.*?)\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        StudentOrg temp = findStudentOrg(name);
        if(temp != null){
            if(!temp.get_president().getUsername().trim().equalsIgnoreCase(user.trim()) || !user.equalsIgnoreCase("admin")) {
                for (student x : temp.getOrgMembers()) {
                    db.msgStudent(x.username, user + ":" + com);
                }
                return "sucessfully messaged " + temp.getOrgName();
            }
            else{
                return "you are not allowed to mass message this group";
            }
        }
        return "no student org found by the name " + name;
    }

    /**
     * 
     * @param command
     * Helper method to parse and run the command that finds StudentOrgs and returns information on them
     * @return StudentOrg
     */
    public StudentOrg findStudentOrg(String command){
        StudentOrg studentOrg;
        String name = "";
        String com = command.replaceFirst("(.*?)\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        if(db.getStudentOrg(name) != null){
            return db.getStudentOrg(name);
        }
        return null;
    }

    /**
     * This method is responsible for retrieving/verifying Group objects from the database object
     * @param command
     * @return Group object
     */
    public Group findGroup(String command){
        //regex for splitting command to find name
        Group group;
        String name = "";
        String com = command.replaceFirst("(.*?)\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
       // String name = "group1";
        if(database.findGroup(name) != null){
            return database.findGroup(name);
        }
        return null;
    }

    /**
     * This method is responsible for retrieving a list of students objects by repeatedly calling the findStudent method from the database
     * object
     * @param command
     * @return List of student objects or null if instance of student not found
     */
    public List<student> findStudents(String command){
        //remove eveything before command colon
        List<student> found = new ArrayList<>();
        String com = command.replaceFirst("(.*?)\\:", "");
        if(com.contains(",")) {
            for (String studentId : com.split(",")) {
                if (database.findStudent(studentId) == null) {
                    //student not found
                    return null;
                }
                else{
                    found.add(database.findStudent(studentId));
                }
            }
        }
            else {
                    found.add(database.findStudent(com));
                    return found;
                }

        return found;
    }

    /**
     * Parses command and finds faculty based on command, returns success info
     * @param command
     * @return String
     */
    public List<faculty> findFaculty(String command){
        //remove eveything before command colon
        List<faculty> found = new ArrayList<>();
        String com = command.replaceFirst("(.*?)\\:", "");
        if(com.contains(",")) {
            for (String facultyId : com.split(",")) {
                if (database.findFaculty(facultyId) == null) {
                    //faculty not found
                    return null;
                }
                else{
                    found.add(database.findFaculty(facultyId));
                }
            }
        }
        else {
            found.add(database.findFaculty(com));
            return found;
        }

        return found;
    }

    /**
     * This method is responsible for taking a command and parsing its arguments to create a student object which is then added
     * to the database via the addStudent method of the database object
     * @param command
     * @return
     */
    public String createStudent(String command){
        String com = command.replaceFirst("(.*?)add student\\:", "");
        String[] args = com.split(",");
        student temp = new student(args[0], args[1], args[2]);
        db.addStudent(temp);
        return "added Student: Username:" + args[0] + " Password:" + args[1] + " Full Name:" + args[2] + " with id:" + temp.getId();
    }

    /**
     * Parses command and removes student based on command, returns success info
     * @param command
     * @return String
     */
    public String removeStudent(String command){
        String removed = "";
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        for(String x: args){
            if(findStudents(x) != null){
                db.removeStudent(x);
                removed += "removed student:" + x + "\n";
            }
            else{
                removed += "failed to remove student:" + x + "\n";
            }
        }
        return "Successfully removed students";

    }
    
    /**
     * Parses command and adds bus route based on command, returns success info
     * @param command
     * @return String
     */
    public String addRoute(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        if(db.getBusRoute(com) != null){
            return "route: " + com + " already exists";
        }
        else {
            BusRoute temp = new BusRoute(com);
            database.addBusRoute(temp);
            return "create new bus route: " + com;
        }
    }
    
    /**
     * Parses command and removes bus route based on command, returns success info
     * @param command
     * @return String
     */
    public String removeRoute(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        if(db.getBusRoute(com) != null){
            database.removeBusRoute(db.getBusRoute(com));
            return "removed bus route";
        }
        else{
            return "no bus route found";
        }
    }

    /**
     * Parses command and displays fair based on command, returns fair info
     * @param command
     * @return String
     */
    public String displayFair(String command){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. display fair:building,date(dd-mm-yyyy)";
        }
        try {
            Date tempDate = dateFormat.parse(args[1]);
            if (database.getFair(args[0], tempDate) != null) {
                return database.getFair(args[0],tempDate).toString();
            } else {
                return "there is no fair scheduled at that building on that day";
            }
        }
             catch(Exception e){
                if(e.getClass().equals(ParseException.class)) {
                    return "invalid date format. dd-MM-yyyy";
                }
                else{
                    return "error";
                }
            }
    }

    /**
     * Parses command and removes fair based on command, returns success info
     * @param command
     * @return String
     */
    public String removeFair(String command){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. create fair:building,date(dd-mm-yyyy)";
        }
        try{
            Date tempDate = dateFormat.parse(args[1]);
            if(database.getFair(args[0], tempDate) != null) {
                database.removeFair(database.getFair(args[0], tempDate));
                return "removed fair at " + args[0] + " on " + tempDate.toString();
            }
            else{
                return "there is no fair scheduled at that building on that day";
            }
        }
        catch(Exception e){
            if(e.getClass().equals(ParseException.class)) {
                return "invalid date format";
            }
            else{
                return "invalid capacity, please input integer";
            }
        }
    }

    /**
     * Parses command and registers company based on command, returns success info
     * @param command
     * @return String
     */
    public String registerCompany(String command){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 3){
            return "invalid parameters. register company:building,date,company";
        }
        try{
            Date tempDate = dateFormat.parse(args[1]);
            if(database.getFair(args[0], tempDate) != null) {
                Fair temp = database.getFair(args[0], tempDate);
                temp.registerCompany(args[2]);
                return "registered company for fair at " + args[0] + " on " + tempDate.toString();
            }
            else{
                return "there is no fair scheduled at that building on that day";
            }
        }
        catch(Exception e){
            if(e.getClass().equals(ParseException.class)) {
                return "invalid date format. dd-MM-yyyy";
            }
            else{
                 e.printStackTrace();
                 return"error";
            }
        }
    }

    /**
     * Parses command and creates fair based on command, returns success info
     * @param command
     * @return String
     */
    public String createFair(String command){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 3){
            return "invalid parameters. create fair:building,capacity,date(dd-mm-yyyy)";
        }
        try{
            Date tempDate = dateFormat.parse(args[2]);
            if(database.getFair(args[0], tempDate) == null) {
                Fair temp = new Fair(args[0], Integer.parseInt(args[1]), tempDate);
                database.addFair(temp);
                return "created new fair at " + args[0] + " on " + tempDate.toString();
            }
            else{
                return "there is already a fair scheduled at that building on that day";
            }
        }
        catch(Exception e){
            if(e.getClass().equals(ParseException.class)) {
                return "invalid date format. dd-MM-yyyy";
            }
            else{
                return "invalid capacity, please input integer";
            }
        }
    }

    /**
     * Parses command and adds bus based on command, returns success info
     * @param command
     * @return String
     */
    public String addBus(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. add bus:driver,bus number";
        }
        else{
            if(database.findFaculty(args[0]) == null){
                return "no faculty by that username";
            }
            else {
                Bus temp = new Bus(database.findFaculty(args[0]), Integer.parseInt(args[1]));
                database.addBus(temp);
                return "sucessfully added a new bus";
            }
        }
    }

    /**
     * Parses command and adds bus stop based on command, returns success info
     * @param command
     * @return String
     */
    public String addStop(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 3){
            return "invalid parameters. add stop:route, stop name, stop time";
        }
        if(db.getBusRoute(args[0]) == null){
            return "no route: " + args[1] + " found";
        }
        else{
            db.getBusRoute(args[0]).addStop(args[1], Integer.parseInt(args[2]));
            return "added stop: " + args[1] + " at " + args[2] + " to route: " + args[0];
        }
    }

    /**
     * Parses command and removes bus stop based on command, returns success info
     * @param command
     * @return String
     */
    public String removeStop(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 3){
            return "invalid parameters. remove stop:route, stop name, stop time";
        }
        if(db.getBusRoute(args[0]) == null){
            return "no route: " + args[1] + " found";
        }
        else{
            BusRoute temp = db.getBusRoute(args[0]);
            temp.removeStop(args[1],Integer.parseInt(args[2]));
            return "removed stop";
        }
    }

    /**
     * 
     * @param command
     * Parses command and displays bus based on command, returns bus info
     * @return String
     */
    public String displayBus(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        if(db.getBus(Integer.parseInt(com)) == null){
            return "no bus: " + com + " found";
        }
        else {
            return db.getBus(Integer.parseInt(com)).toString();
        }
    }

    /**
     * 
     * @param command
     * Parses command and checks out books based on command, returns success info
     * @return String
     */
    public String displayRoute(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        if(db.getBusRoute((com)) == null){
            return "no bus route: " + com + " found";
        }
        else {
            return db.getBusRoute(com).toString();
        }
    }

    /**
     * 
     * @param command
     * Parses command and deploys bus based on command, returns success info
     * @return String
     */
    public String deployBus(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. deploy bus:bus number,bus route";
        }
        if(db.getBusRoute(args[1]) == null){
            return "no route: " + args[1] + " found";
        }
        if(db.getBus(Integer.parseInt(args[0])) == null){
            return "no bus: " + args[0] + " found";
        }
        else {
            db.getBus(Integer.parseInt(args[0])).assignRoute(db.getBusRoute(args[1]));
            return "assigned bus: " + args[0] + " to route: " + args[1];
        }
    }

    /**
     * 
     * @param command
     * Parses command and adds books based on command, returns success info
     * @return String
     */
    public String addBook(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. add book:title,author";
        }
        else {
            Book temp = new Book(args[0], args[1]);
            library.addBook(temp);
        }
        return "added book";
    }

    /**
     * 
     * @param command
     * Parses command and checks in books based on command, returns success info
     * @return String
     */
    public String checkIn(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. check in book:title,author";
        }
        else {
            Book temp = library.findBook(args[0], args[1]);
            if(temp != null){
                student tempStudent = database.findStudent(user);
                return library.checkInBook(tempStudent, temp);
            }
            else{
                return "no book by that title and author";
            }
        }
    }

    /**
     * 
     * @param command
     * Parses command and checks out books based on command, returns success info
     * @return String
     */
    public String checkOut(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. check out book:title,author";
        }
        else {
            Book temp = library.findBook(args[0], args[1]);
            if(temp != null){
                student tempStudent = database.findStudent(user);
                if(tempStudent != null) {
                    return library.checkOutBook(tempStudent, temp);
                }
                else{
                    return "must be a student in order to check out book";
                }
            }
            else{
                return "no book by that title and author";
            }
        }
    }

    /**
     * 
     * @param command
     * Parses command and checks overdue books based on command, returns success info
     * @return
     */
    public String overDueBooks(String command){
        String temp = "";
        for(Map.Entry<Book,student> entry: library.checkForLate().entrySet()){
            temp += "Book:" + entry.getKey().toString() + " Student:" + entry.getValue().getFullname();
        }
        return temp;
    }

    public String listBooks(String command){
        return library.getBooks();
    }
    
    /**
     * 
     * @param command
     * Parses command and removes book based on command, returns success info
     * @return String
     */
    public String removeBook(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        if(args.length != 2){
            return "invalid parameters. remove book:title,author";
        }
        else {
           Book temp = library.findBook(args[0], args[1]);
           if(temp != null) {
               library.removeBook(temp);
           }
        }
        return "removed book";
    }

    /**
     * 
     * @param command
     * Parses command and creates faculty based on command, returns success info
     * @return String
     */
    public String createFaculty(String command){
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        faculty temp = new faculty(args[0], args[1], args[2]);
        db.addFaculty(temp);
        return "added Faculty: Username:" + args[0] + "Password:" + args[1] + "Full Name:" + args[2] + temp.getId();
    }
    
    /**
     * 
     * @param command
     * Parses command and removes faculty based on command, returns success info
     * @return String
     */
    public String removeFaculty(String command){
        String removed = "";
        String com = command.replaceFirst("(.*?)\\:", "");
        String[] args = com.split(",");
        for(String x: args){
            if(db.findFaculty(x) != null){
                db.removeFaculty(x);
                removed += "removed faculty:" + x + "\n";
            }
            else{
                removed += "failed to remove faculty:" + x + "\n";
            }
        }
        return "Successfully removed faculty";

    }

    /**
     * This method is responsible for creating a token regex expression form a list of keywords to be used in the
     * controller object in the parseCommand method
     * @param keywords
     * @param caseSensitive
     * @return
     */
    public String createRegexFromList(List<String> keywords, boolean caseSensitive){
        String regex = "";
        if(!caseSensitive){
            regex = "(?i)";
        }

        for(int i = 0; i < keywords.size(); i++){
            if(i > 0)
                regex += "|";
            regex += keywords.get(i);
        }
        return "(" + regex + ")";
    }
}
