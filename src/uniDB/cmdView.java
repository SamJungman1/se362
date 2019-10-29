package uniDB;

import java.util.Scanner;

public class cmdView {
	
	private Scanner scanner;
	private boolean loggedIn;
	private controller controller;
	
	public cmdView() {
		scanner = new Scanner(System.in);
		loggedIn = false;
		controller = new controller();
	}
	
	public void start() {
		
		while(!loggedIn) {
			login();
		}
	}
	
	private boolean login() {
		System.out.print("Enter Username: ");
		// Take username
		String username =  scanner.nextLine();
		
		System.out.print("Enter Password: ");
		// Get password from user
		String password = scanner.nextLine();
		
		return controller.login(username, password);
	}
			
}
