package uniDB;

public abstract class user {
	public String username;
	private String password;
	
	public boolean login(String psswrd)
	{
		return(psswrd == password);
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setPassword(String psswrd)
	{
		password = psswrd;
	}

}
