package uniDB;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bus {
    private faculty _driver;
    private BusRoute _route;
    private int _busNumber;

    public Bus(faculty driver, int busNumber){
        _driver = driver;
      _busNumber = busNumber;
    }

    public faculty get_driver() {
        return _driver;
    }

    public int getBusNumber() {
        return _busNumber;
    }

    public void assignRoute(BusRoute route){
        _route = route;
    }

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
