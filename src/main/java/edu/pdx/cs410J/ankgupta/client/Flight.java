package edu.pdx.cs410J.ankgupta.client;


import com.google.gwt.i18n.client.DateTimeFormat;
import edu.pdx.cs410J.AbstractFlight;
import java.util.Date;
import java.text.ParseException;

/**
 * Created by Ankur on 7/5/2014.
 */

/**
 * Class defining the flight object and its associated variables
 */
public class Flight extends AbstractFlight implements Comparable<Flight>{
    int flightNumber;
    String src;
    String dest;
    Date departDate;
    Date arriveDate;
    long flightDuration;

    /**
     *
     * @param flightNumber
     * @param src
     * @param departDate
     * @param dest
     * @param arriveDate
     */
    public Flight(int flightNumber, String src, Date departDate, String dest, Date arriveDate){
        this.flightNumber = flightNumber;
        this.src = src;
        this.departDate = departDate;
        this.dest = dest;
        this.arriveDate = arriveDate;
        this.flightDuration = getFlightDuration(departDate, arriveDate);
    }

    /**
     *
     */
    public Flight() {
    }


    /**
     *
     * @param f the flight
     * @return the comparison between two flight's sources, or departure time if source the same
     */
    @Override
    public int compareTo(Flight f){
        if(this.getSource().compareTo(f.getSource()) != 0){
            return this.getSource().compareTo(f.getSource());
        }
        else {
            return this.departDate.toString().compareTo(f.departDate.toString());
        }
    }

    /**
     * Return the flights duration in hours
     * @param departDate date/time of departure
     * @param arriveDate date/time of arrival
     * @return the difference between the two times
     */
    public long getFlightDuration(Date departDate, Date arriveDate){
        return((arriveDate.getTime() - departDate.getTime())/3600000);
    }

    /**
     * return the flight's duration
     * @return flightDuration
     */
    public String getLength(){
        String n = String.valueOf(flightDuration);
        return n;
    }

    /**
     *
     * @return the flight's number
     */
    public int getNumber(){
        return flightNumber;
    }

    /**
     *
     * @return the flights departure source
     */
    public java.lang.String getSource(){
        return src;
    }

    /**
     *
     * @return null, since this is an optional functiont to implement
     */
    public java.util.Date getDeparture() { /* compiled code */
        return departDate;
    }

    /**
     *
     * @return date as a string instead of util.Date
     */
    @Override
    public String getDepartureString() {
        String ddate = departDate.toString();
        return ddate;
    }

    /**
     *
     * @return the flight's destination
     */
    public java.lang.String getDestination(){
        return dest;
    }

    /**
     *
     * @return null since its an optional function
     */
    public java.util.Date getArrival() { /* compiled code */
        return arriveDate;
    }

    /**
     *
     * @return arrival date as String instead of util.Date
     */
    @Override
    public String getArrivalString() {
        String adate = arriveDate.toString();
        return adate;
    }

    /**
     *
     * @return the flight's full information (except the name)
     */
    public String toString() { /* compiled code */
        return this.flightNumber + " " + this.src + " " + this.departDate + " " + this.dest + " " + this.arriveDate;
    }
}
