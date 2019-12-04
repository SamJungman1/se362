package uniDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
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
		
		File app = new File(System.getProperty("user.dir") + "\\apps\\" + name + ".txt");
		
		Scanner file = new Scanner(app);
		
		while(file.hasNextLine()) {
			System.out.println(file.nextLine());
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
