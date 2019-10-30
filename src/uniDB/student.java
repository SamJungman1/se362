package uniDB;
import java.util.List;
import java.util.ArrayList;

public class student extends user {
	private double GPA;
	private String classification;
	private String major;
	private	List<String> attribute;
	
	public student() {}
	public student(String username, String password, String fullname)
	{
		this.setUsername(username);
		this.setPassword(password);
		this.setFullname(fullname);
		this.generateID();
		attribute = new ArrayList<String>();
	}

	public double getGPA() {
		return this.GPA;
	}

	public void setGPA(double GPA) {
		this.GPA = GPA;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public void addAttribute(String attribut)
	{
		attribute.add(attribut);
	}
	
	public void removeAttribute(String attribut)
	{
		attribute.remove(attribute.indexOf(attribut));
	}
}

