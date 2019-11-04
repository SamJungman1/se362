package uniDB;
import java.util.UUID;
/**
 * 
 * Abstract User class meant to be extended by Student and Faculty
 *
 */
public abstract class user {
	public String username;
	private String password;
	public String fullname;
	private UUID ID;
	
	/**
	 * 
	 * @param usrnme - Your username
	 * @param psswrd - Your Password
	 * This is used to verify credentials matching account 
	 * @return Boolean - Verification of login
	 */
	public boolean login(String usrnme,String psswrd)
	{
		return(psswrd == this.password && usrnme == this.username);
	}
	
	/**
	 * 
	 * @param psswrd - Your new password
	 * Sets password
	 */
	public void setPassword(String psswrd)
	{
		this.password = psswrd;
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
		return this.fullname;
	}
	
	public String setFullname(String fullname)
	{
		return this.fullname = fullname;
	}

	public String getId(){
		return ID.toString();
	}
	
	public void generateID()
	{
		this.ID = ID.randomUUID();
	}

}
