package uniDB;

public class controller {
	
	private database database;
	
	public controller() {
		database = new database();
	}
	
	protected boolean login(String username, String password) {
		
		user user = database.findUser(username);
		
		if(user != null) {
			return user.login(username, password);
		} else {
			return false;
		}
	}

}
