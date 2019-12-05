package uniDB;

import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;

public class student extends user {
	private double GPA;
	private String classification;
	private String major;
	private String id;
	private Map<String, String> att;
	private final int TUITION = 20000;
	private int totalTuition;
	private int monthlyTuition;
	private int totalHousing;
	private int montlyHousing;
	private Dorm dormAssignment;
	private Room roomAssignment;
	private int mealSwipes;

	public student() {
	}

	public student(String username, String password, String fullname) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFullname(fullname);
		this.generateID();
		att = new HashMap<>();
		totalTuition = TUITION;
		monthlyTuition = totalTuition / 12;
		totalHousing = 8000;
		montlyHousing = totalHousing / 12;
		id = this.ID.toString();
		this.inbox = new LinkedList();
		mealSwipes = 500;
	}

	/**
	 * Get id
	 * @return String
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets GPA
	 * @return double
	 */
	public double getGPA() {
		return this.GPA;
	}

	/**
	 * Sets GPA
	 * @param GPA
	 */
	public void setGPA(double GPA) {
		this.GPA = GPA;
	}

	/**
	 * Gets classification
	 * @return String
	 */
	public String getClassification() {
		return this.classification;
	}

	/**
	 * Sets classification
	 * @param classification
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * Gets major
	 * @return String
	 */
	public String getMajor() {
		return this.major;
	}

	/**
	 * Sets major
	 * @param major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * 
	 * @param attribut
	 * @param value
	 * Adds attribute to hashmap
	 */
	public void addAttribute(String attribut, String value) {
		att.put(attribut, value);

		// attribute.add(attribut);
	}

	/**
	 * 
	 * @param attribut
	 * @param value
	 * Edits attribute with new value
	 */
	public void editAttribute(String attribut, String value) {
		att.put(attribut, value);
	}

	/**
	 * 
	 * @param dorm
	 * @param room
	 * Changes housing info based on new housing entered
	 */
	public void changeHousing(Dorm dorm, Room room) {
		this.dormAssignment = dorm;
		this.roomAssignment = room;
	}

	/**
	 * 
	 * @param attribut
	 * Removes attribute entered
	 */
	public void removeAttribute(String attribut) {
		att.remove(attribut);

	}

	/**
	 * 
	 * @param command
	 * Pays tuition info based on command entered
	 */
	public void payTuition(String command) {
		int amount = Integer.parseInt(command);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Credit Card Number XXXXXXXXXXX:");
			String num = scanner.nextLine();

			System.out.print("Enter Expiration date MM/YY: ");
			String exp = scanner.nextLine();

			System.out.print("Enter CCV XXX: ");
			String ccv = scanner.nextLine();

			if (num.length() != 16 || exp.length() != 5 || ccv.length() != 3) {
				System.out.println("Invalid information");
			} else {
				break;
			}
		}

		if (amount > monthlyTuition) {
			amount -= monthlyTuition;
			totalTuition -= amount;
		} else {
			monthlyTuition -= amount;
		}

		System.out.println("Tuition Remaining: " + totalTuition);
		System.out.println("Montly Tuition Remaining: " + monthlyTuition);

	}

	/**
	 * 
	 * @param command
	 * Pays housing info based on command entered
	 */
	public void payHousing(String command) {
		int amount = Integer.parseInt(command);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Credit Card Number XXXXXXXXXXX:");
			String num = scanner.nextLine();

			System.out.print("Enter Expiration date MM/YY: ");
			String exp = scanner.nextLine();

			System.out.print("Enter CCV XXX: ");
			String ccv = scanner.nextLine();

			if (num.length() != 16 || exp.length() != 5 || ccv.length() != 3) {
				System.out.println("Invalid information");
			} else {
				break;
			}
		}

		if (amount > montlyHousing) {
			amount -= montlyHousing;
			totalHousing -= amount;
		} else {
			montlyHousing -= amount;
		}

		System.out.println("Housing Bill Remaining: " + totalHousing);
		System.out.println("Montly housing Remaining: " + montlyHousing);
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * Fetches application and enters it into system
	 */
	public void apply() throws FileNotFoundException, UnsupportedEncodingException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Full Name: ");
		String name = scanner.nextLine();
		
		Application app = new Application(name);
		app.openFile();
		
		app.writeToFile(name);
		
		
		System.out.println("Majors -------------");
		for(int i = 0;i < database.majorTable.size();i++) {
			System.out.println(database.majorTable.get(i).getId());
		}
		System.out.println("--------------------");
		System.out.print("Major you are applying to: ");
		String appMajor = scanner.nextLine();


		app.writeToFile("Major: " + appMajor);
		
		
		
		System.out.print("High SChool GPA: ");
		app.writeToFile("High School GPA: " + scanner.nextLine());
		
		System.out.print("ACT/SAT Score: ");
		app.writeToFile("ACT/SAT Score: " + scanner.nextLine());
		
		System.out.print("List Extracurriculars (q to quit): ");
		app.writeToFile("--- Extracurriculars ---");
		
		String extra = scanner.nextLine();
		while(!extra.equals("q")) {
			app.writeToFile(extra);
			extra = scanner.nextLine();
		}
		app.writeToFile("-----------------------");
		
		System.out.print("Why do you want to attend this university?");
		String essay = scanner.nextLine();
		app.writeToFile("Why do you want to attend this university?");
		app.writeToFile(essay);
		
		app.closeFile();		
	}

	/**
	 * Resets students swipe number to that they started with
	 */
	public void resetSwipes() {
		this.mealSwipes = 500;
	}

	/**
	 * Get method that returns student's swipes
	 * 
	 * @return
	 */
	public int getSwipes() {
		return this.mealSwipes;
	}

	/**
	 * Attempts to subtract a swipe and returns success or failure
	 * 
	 * @return
	 */
	public boolean useSwipe() {
		this.mealSwipes--;
		return (this.mealSwipes >= 0);
	}

	/**
	 * Simple set method for swipes
	 * 
	 * @param num Of Swipes
	 */
	public void setSwipes(int num) {
		this.mealSwipes = num;
	}

	/**
	 * Generates a String in a format suitable to be written to to a file
	 * 
	 * @return String
	 */
	public String toFile() {

		String s = "Username:" + this.username + ":" + "Password:" + this.password + ":" + "Fullname:" + this.fullname
				+ ":" + "classification:" + this.classification + ":" + "GPA:" + this.GPA + ":" + "Major:" + this.major
				+ ":" + "MealSwipes:" + this.mealSwipes + ":";
		for (String key : att.keySet()) {
			s += key.toString() + ":" + att.get(key) + ":";
		}
		return s;
	}

	@Override
	public String toString() {
		String toReturn = "--------------------\n";
		toReturn += "Name:" + getFullname() + "\nId:" + getId() + "\nUsername:" + getUsername() + "\nGPA:" + getGPA()
				+ "\nClassification:" + getClassification() + "\nMajor:" + getMajor() + "\n";
		for (String key : att.keySet()) {
			toReturn += key.toString() + ":" + att.get(key) + "\n";
		}
		toReturn += "--------------------\n";
		return toReturn;
	}
}
