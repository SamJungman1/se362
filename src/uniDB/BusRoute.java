package uniDB;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tyler Lund
 */
public class BusRoute {
    private Map<String, Integer> stops;
    private String routeName;

    public BusRoute(String route) {
        routeName = route;
        stops = new HashMap<>();
    }

    /**
     * adds a k,v to the stops map
     * @param location
     * @param time
     */
    public void addStop(String location, Integer time){
        stops.put(location, time);
    }

    /**
     * returns the routename assigned to route object
     * @return
     */
    public String getRouteName(){
        return routeName;
    }

    /**
     * removes a k,v from the stops map
     * @param location
     * @param time
     */
    public void removeStop(String location, Integer time) { stops.remove(location,time);}

    @Override
    public String toString(){
        String temp = "";
        temp += "Route Name:" + getRouteName() + "\n";
        for(Map.Entry<String,Integer> entry: stops.entrySet()){
            temp += "location:" + entry.getKey().toString() + " Time:" + entry.getValue() + "\n";
        }
        return temp;
    }
}