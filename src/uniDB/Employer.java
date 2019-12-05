package uniDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Samuel Jungman
 *
 */
public class Employer extends user{
	ArrayList<JobOffer> currentOffers;
	String title;
	String company;
	String email;
	
	/**
	 * Empty constructor
	 */
	public Employer() {}
	/**
	 * 
	 * @param title
	 * @param company
	 * @param email
	 * Main constructor for employer class
	 * 
	 */
	public Employer(String username, String password, String fullname, String title, String company, String email) 
	{
		this.username = username;
		this.setPassword(password);
		this.fullname = fullname;
		this.title = title;
		this.company = company;
		this.email = email;
		this.currentOffers = new ArrayList<JobOffer>();
	}
	
	/**
	 * Creates a string in File format of this object
	 * @return String
	 */
	public String toFile()
	{
		String s = this.username + ":" + this.password + ":" + this.fullname + ":" + this.title + ":"+ this.company + ":"+ this.email + ":";
		for(JobOffer j: currentOffers)
		{
			s += j.getType() + ":" + j.getTitle() + ":" + j.getWage() + ":";
		}
		return s;
	}
	/**
	 * Gets the company of this employer
	 * @return String
	 */
	public String getCompany()
	{
		return this.company;
	}
	
	/**
	 * Gets the title of this employer
	 * @return String
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
	 * Gets the email of this employer
	 * @return String
	 */
	public String getEmail()
	{
		return this.email;
	}
	
	/**
	 * @param company
	 * Sets the company of this employer
	 */
	public void setCompany(String company)
	{
		this.company = company;
	}
	
	/**
	 * @param title
	 * Sets the title of this employer
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * @param email
	 * Sets the email of this employer
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * @param type
	 * @param title
	 * @param wage
	 * Creates an offer based on the params
	 */
	public void addOffer(String type, String title, double wage)
	{
		JobOffer job = new JobOffer(type, title, wage);
		currentOffers.add(job);
	}
	
	/**
	 * Lists all offers from this employer
	 * @return String
	 */
	public String getOffers()
	{
		String s = "\n";
		for(JobOffer a: currentOffers)
		{
			s += "Employer email: " + this.email + "\n";
			s += a.type + ":" + a.title + ":" + a.wage + "\n";
		}
		return s;
	}
	
	/**
	 * 
	 * @param type
	 * Lists all offers from this employer of a certain type
	 * @return String
	 */
	public String getOffersType(String type)
	{
		String s = "\n";
		for(JobOffer a: currentOffers)
		{
			if(a.getType() == type)
			{
				s += "Employer email: " + this.email + "\n";
				s += a.type + ":" + a.title + ":" + a.wage + "\n";
			}
		}
		return s;
	}
	
	/**
	 * 
	 * @param title
	 * Lists all offers from this employer of a certain title
	 * @return String
	 */
	public String getOffersTitle(String title)
	{
		String s = "\n";
		for(JobOffer a: currentOffers)
		{
			if(a.getTitle() == title)
			{
				s += "Employer email: " + this.email + "\n";
				s += a.type + ":" + a.title + ":" + a.wage + "\n";
			}
		}
		return s;
	}
	
	/**
	 * 
	 * @param wage
	 * Lists all offers from this employer where the wage is equal or above a certain threshold
	 * @return String
	 */
	public String getOffersWage(double wage)
	{
		String s = "\n";
		for(JobOffer a: currentOffers)
		{
			if(a.getWage() >= wage)
			{
				s += "Employer email: " + this.email + "\n";
				s += a.type + ":" + a.title + ":" + a.wage + "\n";
			}
		}
		return s;
	}
	
	/**
	 * 
	 * Private class that employers can use to display offers to people at the university
	 *
	 */
	private class JobOffer 
	{
		String type;
		String title;
		double wage;
		
		
		private JobOffer(String type, String title, double wage)
		{
			this.type = type;
			this.title = title;
			this.wage = wage;
		}
		
		public String getType()
		{
			return this.type;
		}
		
		public String getTitle()
		{
			return this.title;
		}
		
		public double getWage()
		{
			return this.wage;
		}
		
		public void setType(String type)
		{
			this.type = type;
		}
		
		public void setTitle(String title)
		{
			this.title = title;
		}
		
		public void setWage(double wage)
		{
			this.wage = wage;
		}
	}
}


