package uniDB;

import java.util.Scanner;

public class cmdView {

	private Scanner scanner;
	private boolean loggedIn;
	private Controller controller;

	public cmdView() {
		scanner = new Scanner(System.in);
		loggedIn = false;
		controller = new Controller();
	}

	public void start() {

		while(!loggedIn) {
			loggedIn = login();
		}
		if(loggedIn)
			while(true){
				System.out.println("enter command:");
				System.out.println(controller.parseCommand(scanner.nextLine()));
			}
	}

	private boolean login() {
		System.out.print("Enter Username: ");
		// Take username
		String username =  scanner.nextLine();

		System.out.print("Enter Password: ");
		// Get password from user
		String password = scanner.nextLine();

		return true;
		//return controller.login(username, password);
	}

}
