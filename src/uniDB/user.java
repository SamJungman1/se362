package uniDB;
import java.util.UUID;

public abstract class user {
	public String username;
	private String password;
	public String fullname;
	private UUID ID;
	
	public boolean login(String usrnme,String psswrd)
	{
		return(psswrd == this.password && usrnme == this.username);
	}
	
	public void setPassword(String psswrd)
	{
		this.password = psswrd;
	}

	public boolean login(String psswrd) {
		return (psswrd == password);
	}

	public String getUsername() {
		return this.username;
	}
	
	public String setUsername(String username)
	{
		return this.username = username;
	}
	
	public String getFullname()
	{
		return this.username;
	}
	
	public String setFullname(String fullname)
	{
		return this.fullname = fullname;
	}
	
	public void generateID()
	{
		this.ID = ID.randomUUID();
	}

}
