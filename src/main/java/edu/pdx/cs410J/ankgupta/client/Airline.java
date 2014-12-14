package edu.pdx.cs410J.ankgupta.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;

import java.text.ParseException;
import java.util.*;

/**
 * Airline class based on AbstractAirline
 * Created by Ankur on 7/5/2014.
 */
public class Airline extends AbstractAirline {
    private String name;
    private List<Flight> flightList;

    /**
     *
     * @param name of airline
     */
    public Airline(String name){
        this.name = name;
        flightList = new LinkedList<Flight>();
    }

    /**
     *
     */
    public Airline() {
    }

    /**
     *
     * @return the name of the Airline
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param newflight so that we can add a flight's schedule to the airline
     *
     */
    public void addFlight(AbstractFlight newflight) {
        Collections.sort(flightList);
        boolean same = checkExist((Flight)newflight);
        if(same == false) {
            try {
                flightList.add((Flight) newflight);
                Collections.sort(flightList);
            } catch (ClassCastException c) {
                System.out.println("Failed sort");
            }
        }
    }

    /**
     *
     * @param flight that is about to be added to list
     * @return whether not an identical flight is already in the list
     */
    public boolean checkExist(Flight flight){
        boolean same = false;
        for(Flight f : flightList){
            if(f.getNumber() == (flight.getNumber())){
                if(f.getSource().equals(flight.getSource())){
                    if(f.getDepartureString().equals(flight.getDepartureString())){
                        if(f.getDestination().equals(flight.getDestination())){
                            same = true;
                        }
                    }
                }
            }
        }
        return same;
    }

    /**
     *
     * @return the flight schedule
     */
    public java.util.Collection getFlights() {
        return flightList;
    }


    /**
     * Compiles the string of flight information to be added to the file
     * @return the string of the airline information
     */
    public String writetofile() {
        String file = "";
        Collections.sort(flightList);
        for(Flight f : flightList){
            file += name + " " + f.getNumber() + " " + f.getSource() +
                    " " + f.getDepartureString() + " " + f.getDestination() +
                    " " + f.getArrivalString() + "\n";
        }
        return file;
    }

    /**
     * return a string with the flight in pretty text
     * @return file
     */
    public String writePrettyFile(){
        String file = "------- Flights For Airline: " + name + "-------" + "\n";
        Collections.sort(flightList);
        for(Flight f : flightList){
            Date arrive = null;
            Date depart = null;
            String arrives = f.getArrivalString();
            String departs = f.getDepartureString();
            DateTimeFormat dateformat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
            try {
                depart = dateformat.parse(departs);
                arrive = dateformat.parse(arrives);
            } catch(Exception e) {
                System.err.println("Invalid date/time");
            }

            String source = f.getSource();
            String src = AirportNames.getName(source.toUpperCase());
            String destination = f.getDestination();
            String dest = AirportNames.getName(destination.toUpperCase());

            file += "Flight number:     " + f.getNumber() + "\n" +
                    "From:              " + src + "\n" +
                    "Departure time:    " + depart + "\n" +
                    "To:                " + dest + "\n" +
                    "Arrival time:      " + arrive + "\n" +
                    "Flight time (hrs): " + f.getLength() + "\n" + "\n";
        }
        return file;
    }

    /**
     * used for testing of the number of objects in the list
     * @return count
     */
    public int listCount(){
        int count = flightList.size();
        return count;
    }

    /**
     *
     * @return nothing
     */
    public java.lang.String toString() /* compiled code */ {
        return null;
    }

    /**
     * Used for testing, prints all the flights in the list
     */
    public void printAll(){
        Collections.sort(flightList);
        for(Flight f : flightList){
            System.out.println(name + " " + f.getNumber() + " " + f.getSource() +
                    " " + f.getDepartureString() + " " + f.getDestination() +
                    " " + f.getArrivalString());
        }
    }
}

