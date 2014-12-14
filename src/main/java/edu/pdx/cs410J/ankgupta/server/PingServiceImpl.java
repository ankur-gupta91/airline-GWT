package edu.pdx.cs410J.ankgupta.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.ankgupta.client.Airline;
import edu.pdx.cs410J.ankgupta.client.Flight;
import edu.pdx.cs410J.ankgupta.client.PingService;

import java.util.*;

/**
 * The server-side implementation of the Airline service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
    /**
     *
     */
    public HashMap<String, Airline> map = new HashMap<String, Airline>();

    /**
     *
     * @param text name of Airline
     * @param flight the flight object to be added to the airline's list
     * @return map the updated HashMap
     */
    @Override
    public HashMap addFlight(String text, Flight flight) {
        Airline airline = map.get(text);
        if(airline == null) {
            airline = new Airline(text);
            airline.addFlight(flight);
            map.put(text, airline);
        }
        else {
            airline.addFlight(flight);
        }
        return map;
    }

    /**
     *
     * @param name of the airline
     * @param source of the flight
     * @param dest of the flight
     * @return the entire HashMap
     */
    @Override
    public HashMap searchFlight(String name, String source, String dest) {
        return map;
    }

    /**
     *
     * @return the entire HashMap
     */
    @Override
    public HashMap Display() {
        return map;
    }
}
