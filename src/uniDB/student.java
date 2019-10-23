package uniDB;

public class student extends user{
	private double GPA;
	private String classification;
	
	public student() {}
	public student(String username, String password)
	{
		this.username = username;
		this.setPassword(password);
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
