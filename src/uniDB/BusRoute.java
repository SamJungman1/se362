package uniDB;

import java.util.HashMap;
import java.util.Map;

public class BusRoute {
    private Map<String, Integer> stops;
    private String routeName;

    public BusRoute(String route) {
        routeName = route;
        stops = new HashMap<>();
    }

    public void addStop(String location, Integer time){
        stops.put(location, time);
    }

    public String getRouteName(){
        return routeName;
    }

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