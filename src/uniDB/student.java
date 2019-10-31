package uniDB;

import java.util.HashMap;
import java.util.List;
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
		id = this.ID.toString();
		this.inbox = new LinkedList();
	}

	public String getId()
	{
		return this.id;
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

	public void addAttribute(String attribut, String value) {
		att.put(attribut, value);
	}

	public void editAttribute(String attribut, String value) {
		att.put(attribut, value);
	}

	public void removeAttribute(String attribut) {
		att.remove(attribut);

	}
	
	public void payTuition(String command) {
		int amount = Integer.parseInt(command);
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("Enter Credit Card Number XXXXXXXXXXX:");
			String num = scanner.nextLine();
			
			System.out.print("Enter Expiration date MM/YY: ");
			String exp = scanner.nextLine();
			
			System.out.print("Enter CCV XXX: ");
			String ccv = scanner.nextLine();
			
			if(num.length() != 16 || exp.length() != 5 || ccv.length() != 3) {
				System.out.println("Invalid information");
			} else {
				break;
			}
		}
		
		if(amount > monthlyTuition) {
			amount -= monthlyTuition;
			totalTuition -= amount;
		} else {
			monthlyTuition -= amount;
		}
		
		System.out.println("Tuition Remaining: " + totalTuition);
		System.out.println("Montly Tuition Remaining: "+ monthlyTuition);


	}

	@Override
	public String toString() {
		String toReturn = null;
		toReturn += getFullname() + "\n" + getId() + "\n" + getUsername() + "\n" + getClassification() + "\n"
				+ getMajor() + "\n";
		for (String key : att.keySet()) {
			toReturn += key.toString() + ":" + att.get(key);
		}
		return toReturn;
	}
}
