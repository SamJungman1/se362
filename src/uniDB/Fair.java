package uniDB;

/**
 * @author Tyler Lund
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Fair {

    private String _building;
    private int _capacity;
    ArrayList<String> companies;
    private Date _date;

    public Fair(String building, int capacity, Date date){
        _building = building;
        _capacity = capacity;
        _date = date;
        companies = new ArrayList<>();
    }

    /**
     * returns the current capacity of the fair
     * @return
     */
    public int get_capacity(){
        return _capacity;
    }

    /**
     * returns the number of companies
     * @return
     */
    public int getCount(){
        return companies.size();
    }

    /**
     * adds a company to the list of companies if it is not already
     * @param company
     */
    public void registerCompany(String company){
        if(!companies.contains(company)) {
            companies.add(company);
        }
    }

    /**
     * returns the date set for the fair object
     * @return
     */
    public Date get_date(){
        return _date;
    }

    /**
     * returns the building set for the fair object
     * @return
     */
    public String get_building(){
        return _building;
    }

    /**
     * removes a company from the company list if they exist
     * @param company
     */
    public void removeCompany(String company){
        if(companies.contains(company)){
            companies.remove(company);
        }
    }

    @Override
    public String toString(){
        String temp = "";
   temp = "Building:" + get_building() + "\n" +
    "Date:" + get_date().toString() + "\n" +
    "Companies:" + "\n";
   for(String x : companies){
       temp += x + "\n";
   }
   return temp;
    }

}
