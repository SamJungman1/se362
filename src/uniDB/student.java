package uniDB;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class student extends user {
	private double GPA;
	private String classification;
	private String major;
	private String id;
	private Map<String, String> att;
	private int tuition;
	private String payentSchedule;

	public student() {
	}

	public student(String username, String password, String fullname) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFullname(fullname);
		this.generateID();
		att = new HashMap<>();
		tuition = 20000;
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
		// attribute.add(attribut);
	}

	public void editAttribute(String attribut, String value) {
		att.put(attribut, value);
	}

	public void removeAttribute(String attribut) {
		att.remove(attribut);
		// attribute.remove(attribute.indexOf(attribut));
	}
	
	public void payTuition() {
		
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
