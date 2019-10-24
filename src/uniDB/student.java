package uniDB;

public class student extends user{
	private double GPA;
	private String classification;
	
	public student() {}
	public student(String username, String password, String fullname)
	{
		this.setUsername(username);
		this.setPassword(password);
		this.setFullname(fullname);
		this.generateID();
	}
	public double getGPA()
	{
		return this.GPA;
	}
	
	public void setGPA(double GPA)
	{
		this.GPA = GPA;
	}
	
	public String getClassification()
	{
		return this.classification;
	}
	
	public void setClassification(String classification)
	{
		this.classification = classification;
	}

}
