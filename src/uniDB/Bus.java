package uniDB;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Tyler Lund
 */
public class Bus {
    private faculty _driver;
    private BusRoute _route;
    private int _busNumber;

    public Bus(faculty driver, int busNumber){
        _driver = driver;
      _busNumber = busNumber;
    }

    /**
     * returns the faculty object assigned as a driver
     * @return
     */
    public faculty get_driver() {
        return _driver;
    }

    /**
     * returns the busnumber assigned to the object
     * @return
     */
    public int getBusNumber() {
        return _busNumber;
    }

    /**
     * assigns a route to a bus object
     * @param route
     */
    public void assignRoute(BusRoute route){
        _route = route;
    }

    /**
     * returns the route object assigned to bus object
     * @return
     */
    public BusRoute get_route(){
        return _route;
    }

    @Override
    public String toString(){
        return
                "driver:" + get_driver().getFullname() + "\n" +
                "bus number:" + getBusNumber() + "\n" +
                     "Route:\n" + get_route();
    }
}
