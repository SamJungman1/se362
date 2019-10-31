package uniDB;
import java.util.LinkedList;
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

}
