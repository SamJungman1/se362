package uniDB;

import java.util.ArrayList;
import java.util.List;
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
    private database db;
    public String user;      


    public Controller(){
        db = new database();
        db.addFaculty(new faculty("admin", "admin", "admin"));
        db.addStudent(new student("test", "test", "test"));
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
            switch(match.group(0)) {
                case "get student:":
                    String studentsFound = null;
                    for(student x: findStudents(command))
                    {
                        studentsFound += x.toString() + "\n";
                    }
                    if(studentsFound != null) {
                        return studentsFound;
                    }
                    else{
                        return "Error finding one or more students with given id's";
                    }
                    //TODO:
                case "remove student:":

                    break;
                case"add student:":
                    return createStudent(command);
                    //TODO:
                case"edit student:":
                    if(findStudents(command) != null){
                        editField(command);
                    }
                    else{
                        return "student not found, please try again";
                    }
                case"edit group:":
                    if(findGroup(command) != null) {
                        //edit group:groupName field fieldValue
                        editField(findGroup(command), command);
                        return "updated values for group:" + findGroup(command).getGroupName();
                    }
                    else{
                        return null;
                    }
                case"create group:":
                    if(createGroup(command) != null) {
                        database.addGroup(createGroup(command));
                        return "successfully added group";
                    }
                    else {return "one or more invalid id's, please retry";}
                    
                case "pay tuition:":
                	student stu = db.findStudent(user);
                	stu.payTuition(command.substring(11));
                	
                case "msg student:":
                	db.msgStudent(command.substring(11), "What's crackin");
                	
                case "msg faculty:":
                	db.msgStudent(command.substring(11), "What's crackin");
                	
                case "getMsg student:":
                	db.getMsgsStudent(command.substring(14));
                	
                case "getMsg faculty:":
                	db.getMsgsStudent(command.substring(14));
                case "make major:":
                    Major newmajor = new Major(command.substring(11).trim());
                    db.addMajor(newmajor);
                    return "major "+command.substring(11)+" has been made.";
                case "add adviser:": // major = args[0]     faculty = args[1]
                    String aa = command.replaceFirst("add adviser:", "");
                    String[] aargs = aa.trim().split(" ");
                    faculty adv = db.findFaculty(aargs[1]); Major major1 = db.findMajor(aargs[0]);
                    if(major1 == null){return aargs[0]+" is an invalid major id. Please use a valid id.";}
                        if(adv == null){return aargs[1]+" is an invalid username. Please use a valid username.";}
                            if(major1.addAdviser(aargs[1])){return aargs[1]+" has been added as adviser to "+aargs[0]+" major.";}
                            else{return "addition of "+aargs[1]+" to adviser list has failed.";}
                case "make class:":
                    String mc = command.replaceFirst("make class:", "");
                    String[] margs = mc.trim().split(" ");  // [0] major  [1] new class
                    Major ma = db.findMajor(margs[0]);
                    if (ma == null) {return"major entered is invalid";}
                    if(ma.makeClass(margs[1])){return "class "+margs[1]+" has been created";}
                    else{return "Failed to make class";}
                case "change major id:":
                    String cmi = command.replaceFirst("change major id:", "");
                    String[] cargs = cmi.trim().split(" ");  //[0] major old id  [1] major new id
                    ma = db.findMajor(cargs[0]); if(ma == null){return "major id is invalid";}
                    ma.changeID(cargs[1]); return "major id has been changed to "+cargs[1];
                case "remove adviser:":
                    cmi = command.replaceFirst("remove adviser:", "");
                    cargs = cmi.trim().split(" "); //  [0] major  [1] username of adviser to be removed
                    ma = db.findMajor(cargs[0]); if(ma == null){return "major id is invalid";}
                    adv = db.findFaculty(cargs[1]); if(adv == null){return "username of adviser does not exist";}
                    if(ma.removeAdvisor(cargs[1])){return "adviser "+cargs[1]+" has been removed";}
                    else{return "failed to remove adviser";}
                case "change class id:":
                    cmi = command.replaceFirst("change class id:", "");
                    String[] ccargs = cmi.trim().split(" "); // [0] major  [1] old class id [2] new class id
                    ma = db.findMajor(ccargs[0]); if(ma == null){return "major id is invalid";}
                    Major.Class cl = ma.findClass(ccargs[1]); if(cl == null){return "class id is invalid";}
                    if(cl.changeClassId(ccargs[2])){return "class id has been changed to "+ccargs[2];}
                case "change class instructor:":
                    cmi = command.replaceFirst("change class instructor:", "");
                    ccargs = cmi.trim().split(" "); // [0] = major id [1] = class id [2] = faculty user name
                    ma = db.findMajor(ccargs[0]); if(ma == null){return "major id is invalid";}
                    cl = ma.findClass(ccargs[1]); if(cl == null){return "class id is invalid";}
                    adv = db.findFaculty(ccargs[2]); if(adv == null){return "instructor username is invalid";}
                    if(cl.changeclassinstructor(adv)){return "class "+ccargs[2]+" instructor has been changed to "+ccargs[2];}
                case "add student to class:":
                    cmi = command.replaceFirst("add student to class:", "");
                    ccargs = cmi.trim().split(" "); // [0] = major id [1] = class id [2] = student username
                    ma = db.findMajor(ccargs[0]); if(ma == null){return "major id is invalid";}
                    cl = ma.findClass(ccargs[1]); if(cl == null){return "class id is invalid";}
                    student st = db.findStudent(ccargs[2]); if(st == null){return "student username is invalid";}
                    if(cl.addstudenttoclass(st)){ return "student "+ccargs[2]+" successfully added to class "+ccargs[1];}
            }
        }
        return "invalid command";
    }

    /**
     * This method edits a students attributes by calling the findStudent method of the database object
     * and the editAttribute method of the student object
     * @param command
     */
    public void editField(String command){
        String name = null;
        String com = command.replaceFirst("(.*?)edit student\\:", "");
        Pattern pattern = Pattern.compile("([^\\s]+)");
        Matcher matcher = pattern.matcher(com);
        if(matcher.find() && !matcher.group(0).trim().equals("")){
            name = matcher.group(0);
            com = com.replace(name, "");
        }
        String[] args = com.split(" ");
        if(name != null && findStudents(name) != null) {
            findStudents(name).get(0).editAttribute(args[1],args[2]);
        }
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
        String name = null;
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
     * This method is responsible for retrieving/verifying Group objects from the database object
     * @param command
     * @return Group object
     */
    public Group findGroup(String command){
        //regex for splitting command to find name
        String name = "group1";
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
        String com = command.replaceFirst("(.*?)get student\\:", "");
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
     * This method is responsible for taking a command and parsing its arguments to create a student object which is then added
     * to the database via the addStudent method of the database object
     * @param command
     * @return
     */
    public String createStudent(String command){
        String com = command.replaceFirst("(.*?)add student\\:", "");
        String[] args = com.split(",");
        student temp = new student(args[0], args[1], args[2]);
        database.addStudent(temp);
        return "added Student: Username:" + args[0] + " Password:" + args[1] + " Full Name:" + args[2] + " with id:" + temp.getId();
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
