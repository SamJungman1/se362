package uniDB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
public class faculty extends user {
	private double salary;
	private String title;
	
	public faculty() {}
	public faculty(String username, String password, String fullname)
	{
		this.username = username;
		this.setPassword(password);
		this.setFullname(fullname);
		this.generateID();
		this.inbox = new LinkedList();
	}

	/**
	 * Gets salary
	 * @return double
	 */
	public double getSalary() {
		return this.salary;
	}

	/**
	 * Sets salary
	 * @param salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * Gets title
	 * @return String
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Allows faculty to review the application
	 * @throws FileNotFoundException
	 */
	public void reviewApp() throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("--- Pending Apps ---");
		
		File directory = new File(System.getProperty("user.dir") + "\\apps\\");
		String[] directoryFiles = directory.list();
		
		for (int i = 0; i < directoryFiles.length; i++) {
			System.out.println(directoryFiles[i]);
		}
		
		System.out.print("Enter Student's Name: ");
		String name = in.nextLine();
		
		System.out.println();
		
		File app = new File(System.getProperty("user.dir") + "\\apps\\" + name + ".txt");
		Scanner file;
		
		try {
			file = new Scanner(app);
		} catch (Exception e) {
			System.out.println("File not found");
			return;
			
		}
		
		String username = null;
		
		while(file.hasNextLine()) {
			String line = file.nextLine();
			
			if(line.length() >= 10) {
				if(line.substring(0, 10).equals("username: ") && username == null) {
					username = line.substring(10, line.length());
				}
			}
			
			
			System.out.println(line);
		}
		
		System.out.println();
		System.out.println("Enter Comments: ");
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\apps\\" + name + ".txt", true));
			writer.write("Comment: " + in.nextLine());
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
		} 
		
		file.close();
		System.out.print("Accept/Reject/none: ");
		String decision = in.nextLine();
		student student = Controller.db.findStudent(username);
		File appFile = new File(System.getProperty("user.dir") + "\\apps\\" + name);
		
		while(true) {
			if(decision.toLowerCase().equals("accept")) {
				student.setClassification("accepted");
				app.delete();
				break;
			} else if(decision.toLowerCase().equals("reject")) {
				student.setClassification("rejected");
				app.delete();
				break;
			} else if(!decision.toLowerCase().equals("none")){
				System.out.println("invalid command");
			}
		}
		
		
		
	}
	
	/**
	 * Generates a String in a format suitable to be written to to a file
	 * @return String 
	 */
	public String toFile()
	{	
		String s = "Username:" + this.username + ":" + "Password:" + this.password + ":" + "Fullname:" + this.fullname + ":"
				+ "Salary:" + this.salary + ":" + "Title:" + this.title;
		return s;
	}

	@Override
	public String toString(){
		return "Name:" + getFullname() + "\nUsername:" + getUsername() +"\nId:" + getId() + "\nTitle:" + getTitle() + "\nSalary:" + getSalary() + "\n";
	}
}
