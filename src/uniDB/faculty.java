package uniDB;

public class faculty extends user {
	private double salary;
	private String title;

	public faculty() {
	}

	public faculty(String username, String password) {
		this.username = username;
		this.setPassword(password);
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
