package edu.pdx.cs410J.ankgupta.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirportNames;

import java.awt.*;
import java.text.ParseException;
import java.util.*;

/**
 * A basic GWT class that makes sure that we can send an airline back from the server
 */

public class AirlineGwt implements EntryPoint {
    public void onModuleLoad() {

        Command menuCommand = new Command(){
            public void execute(){
              Window.alert("Project 5 README: For this application you can add new flights to each airline. " +
                      "You may also search for an existing flight. " +
                      "To add new flight use text boxes and input all the data. " +
                      "To search the only info required is name, source and destintion. " +
                      "Reset Fields resets all the user input textboxes. " +
                      "Display All Flights displays all the flights in the system. " +
                      "Submit will only work if you have correctly inputted the information. " +
                      "The Search function only requires the Name, Source, and Destination" +
                      "NOTE: RUN PROGRAM ON   MVN GWT:RUN   " +
                      "NOTE: If the flight already exists in the table, it won't be added");
            }
        };

        MenuBar menu = new MenuBar();
        menu.setAutoOpen(true);
        menu.setWidth("800px");
        menu.setAnimationEnabled(true);

        MenuBar helpmenu = new MenuBar(true);
        menu.addItem(new MenuItem("Help", true, helpmenu));
        helpmenu.addItem("README", menuCommand);

        FlexTable flex = new FlexTable();
        FlexTable.FlexCellFormatter cellformat  = flex.getFlexCellFormatter();
        flex.addStyleName("flexTable");
        flex.setWidth("62em");
        flex.setCellSpacing(5);
        flex.setCellPadding(3);

        cellformat.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
      //  flex.setHTML(0, 0, "  <b>ALL FLIGHTS </b>");
        cellformat.setColSpan(0, 0, 8);


        final FlexTable table = new FlexTable();
        table.setWidth("45em");
        flex.setWidget(0,0, table);

        table.setText(0,0, "Name");
        table.setText(0,1, "Number");
        table.setText(0,2, "Source");
        table.setText(0,3, "Depart Date");
        table.setText(0,4, "Destination");
        table.setText(0,5, "Arrival Date");
        table.setText(0,6, "Duration");
        final int i = 1;

        FormPanel form = new FormPanel();
        form.addStyleName("form");

        Grid layout = new Grid(8, 2);
       // form.getElement().getStyle().setBorderWidth(0, Style.Unit.PX);
       // layout.getElement().getStyle().setBorderWidth(0, Style.Unit.PX);
       // form.getElement().getStyle().clearBorderColor();
       // layout.getElement().getStyle().clearBorderColor();
       // layout.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        form.getElement().getStyle().setBorderStyle(Style.BorderStyle.HIDDEN);
        layout.getElement().getStyle().setBorderStyle(Style.BorderStyle.DOTTED);
        layout.addStyleName("form");
        form.setWidget(layout);



        //Airline Name textbox
        Label namelabel = new Label();
        namelabel.setText("Airline NAME:");
        namelabel.setStyleName("form");
        layout.setWidget(0, 0, namelabel);
        final TextBox namebox = new TextBox();
        namebox.addStyleName("textBox");
        namebox.getElement().setAttribute("placeholder", "United");
        //namebox.setText("United");
        namebox.getElement().getStyle().setColor("#C0C0C0");
        namebox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                namebox.getElement().getStyle().setColor("#000000");
                namebox.getElement().setAttribute("placeholder", "");
            }
        });
        namebox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                namebox.getElement().getStyle().setColor("#000000");
                namebox.getElement().setAttribute("placeholder", "");
            }
        });

        layout.setWidget(0, 1, namebox);

        //Airline Number textbox
        Label numlabel = new Label();
        numlabel.setStyleName("form");
        numlabel.setText("Flight #:");
        layout.setWidget(1,0, numlabel);
        final TextBox numberbox = new TextBox();
        numberbox.addStyleName("textBox");
        numberbox.getElement().setAttribute("placeholder", "1231");
        //numberbox.setText("123");
        numberbox.getElement().getStyle().setColor("#C0C0C0");
        numberbox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                numberbox.getElement().getStyle().setColor("#000000");
                numberbox.getElement().setAttribute("placeholder", "");
            }
        });
        numberbox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                numberbox.getElement().getStyle().setColor("#000000");
                numberbox.getElement().setAttribute("placeholder", "");
            }
        });
        layout.setWidget(1,1,numberbox);

        //Flight source textbox
        Label srclabel = new Label();
        srclabel.setStyleName("form");
        srclabel.setText("Source:");
        layout.setWidget(2,0, srclabel);
        final TextBox srcbox = new TextBox();
        srcbox.addStyleName("textBox");
        srcbox.getElement().setAttribute("placeholder", "SFO");
        //srcbox.setText("SFO");
        srcbox.getElement().getStyle().setColor("#C0C0C0");
        srcbox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                srcbox.getElement().getStyle().setColor("#000000");
                srcbox.getElement().setAttribute("placeholder", "");
            }
        });
        srcbox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                srcbox.getElement().getStyle().setColor("#000000");
                srcbox.getElement().setAttribute("placeholder", "");
            }
        });
        layout.setWidget(2,1,srcbox);

        //Depart Date textbox
        Label deplabel = new Label();
        deplabel.setStyleName("form");
        deplabel.setText("Depart Date: ");
        layout.setWidget(3,0,deplabel);
        final TextBox depbox = new TextBox();
        depbox.addStyleName("textBox");
        depbox.getElement().setAttribute("placeholder", "02/12/2130 08:45 pm");
        //depbox.setText("01/11/1001 02:00 am");
        depbox.getElement().getStyle().setColor("#C0C0C0");
        depbox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                depbox.getElement().getStyle().setColor("#000000");
                depbox.getElement().setAttribute("placeholder", "");
            }
        });
        depbox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                depbox.getElement().getStyle().setColor("#000000");
                depbox.getElement().setAttribute("placeholder", "");
            }
        });
        layout.setWidget(3,1,depbox);


        //Flight dest textbox
        Label destlabel = new Label();
        destlabel.setStyleName("form");
        destlabel.setText("Flight Dest: ");
        layout.setWidget(4,0,destlabel);
        final TextBox destbox = new TextBox();
        destbox.addStyleName("textBox");
        destbox.getElement().setAttribute("placeholder" , "LAX");
        //destbox.setText("LAX");
        destbox.getElement().getStyle().setColor("#C0C0C0");
        destbox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                destbox.getElement().getStyle().setColor("#000000");
                destbox.getElement().setAttribute("placeholder", "");
            }
        });
        destbox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                destbox.getElement().getStyle().setColor("#000000");
                destbox.getElement().setAttribute("placeholder", "");
            }
        });
        layout.setWidget(4,1,destbox);

        //Flight arrive texbox
        Label arrivelabel = new Label();
        arrivelabel.setStyleName("form");
        arrivelabel.setText("Arrive Date: ");
        layout.setWidget(5,0,arrivelabel);
        final TextBox arrivebox = new TextBox();
        arrivebox.addStyleName("textBox");
        arrivebox.getElement().setAttribute("placeholder", "01/23/2013 11:45 am");
       // arrivebox.setText("01/22/1991 01:00 pm");
        arrivebox.getElement().getStyle().setColor("#C0C0C0");
        arrivebox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                arrivebox.getElement().getStyle().setColor("#000000");
            }
        });
        arrivebox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                arrivebox.getElement().getStyle().setColor("#000000");
            }
        });
        layout.setWidget(5,1, arrivebox);


        HorizontalPanel choice = new HorizontalPanel();
        final RadioButton create = new RadioButton("choice", "Create");
        create.setStyleName("form");
        choice.add(create);
        final RadioButton search = new RadioButton("choice", "Search");
        search.setStyleName("form");
        choice.add(search);
        layout.setWidget(6,1,choice);

        Button button = new Button("Submit");
        button.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent clickEvent) {
            if (create.getValue() == true) {
                boolean addflight = true;
                //Validate Number
                String number = numberbox.getText();
                int flightnumber = validateNumber(number);
                if (flightnumber == 0) {
                    addflight = false;
                }

                //Validate Source
                String source = validateAirport(srcbox.getText());
                if (source == null) {
                    addflight = false;
                }
                //Validate Departure Date
                Date ddate = validateDate(depbox.getText());
                if (ddate == null) {
                    addflight = false;
                }

                //Validate Dest
                String dest = validateAirport(destbox.getText());
                if (dest == null) {
                    addflight = false;
                }

                //Validate Arrival Date
                Date arrival = validateDate(arrivebox.getText());
                if (arrival == null) {
                    addflight = false;
                }

                //Validate Duration
                long duration = validateDuration(ddate, arrival);
                if(duration < 1) {
                    addflight = false;
                    Window.alert("Flight cannot go back in time");
                }

                Flight flight = new Flight(flightnumber, source, ddate, dest, arrival);
                final String name = namebox.getText();
                if(name == null) {addflight = false ; Window.alert("Please input name") ; }
                if(name.length() < 0) {addflight = false; Window.alert("Please input name") ; }
                if(name.equals("")) {addflight = false; Window.alert("Please input name"); }

                final int rowcount = table.getRowCount();

                if (addflight == true) {
                    PingServiceAsync async = GWT.create(PingService.class);
                    async.addFlight(name, flight, new AsyncCallback<HashMap>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("!!!!!!!!!!!!!" + throwable.toString());
                        }

                        @Override
                        public void onSuccess(HashMap hashMap) {
                            table.removeAllRows();
                            table.setText(0,0, "Name");
                            table.setText(0,1, "Number");
                            table.setText(0,2, "Source");
                            table.setText(0,3, "Depart Date");
                            table.setText(0,4, "Destination");
                            table.setText(0,5, "Arrival Date");
                            table.setText(0,6, "Duration");

                            int j = i;

                            ArrayList<String> keys = new ArrayList<String>();
                            for(Object key : hashMap.keySet()){
                                String n = String.valueOf(key);
                                keys.add(n);
                            }
                            Collections.sort(keys);
                            Collections.sort(keys);
                            for(String s : keys){
                                Airline a = (Airline)hashMap.get(s);
                                Collection<Flight> flightList;
                                flightList = a.getFlights();
                                for(Flight f : flightList) {
                                    table.setText(j, 0, a.getName());
                                    String num = String.valueOf(f.getNumber());
                                    table.setText(j, 1, num);
                                    table.setText(j, 2, f.getSource());
                                    table.setText(j, 3, f.getDepartureString());
                                    table.setText(j, 4, f.getDestination());
                                    table.setText(j, 5, f.getArrivalString());
                                    table.setText(j, 6, f.getLength());
                                    j++;
                                }
                            }
                            int afteradd = table.getRowCount();
                            if(afteradd == rowcount) { Window.alert("Did not add duplicate flight"); }
                        }
                    });
                }
            }
            else if(search.getValue() == true) {
                  boolean searchflight = true;

                  //Validate Source
                  final String source = validateAirport(srcbox.getText());
                  if (source == null) {
                      searchflight = false;
                  }

                  //Validate Dest
                  final String dest = validateAirport(destbox.getText());
                  if (dest == null) {
                      searchflight = false;
                  }

                  final String name = namebox.getText();
                  if(name.equals("")) {searchflight = false; Window.alert("Please input name"); }
                  if(name == null) {searchflight = false ; Window.alert("Please input name") ; }
                  if(name.length() < 0) {searchflight = false; Window.alert("Please input name") ; }

                  if (searchflight == true) {

                      PingServiceAsync async = GWT.create(PingService.class);
                      async.searchFlight(name, source, dest, new AsyncCallback<HashMap>() {

                          @Override
                          public void onFailure(Throwable throwable) {
                              Window.alert("!!!!!!!!!!!!!" + throwable.toString());
                          }

                          @Override
                          public void onSuccess(HashMap hashMap) {
                              table.removeAllRows();
                              table.setText(0,0, "Name");
                              table.setText(0,1, "Number");
                              table.setText(0,2, "Source");
                              table.setText(0,3, "Depart Date");
                              table.setText(0,4, "Destination");
                              table.setText(0,5, "Arrival Date");
                              table.setText(0,6, "Duration");

                              int j = i;

                              boolean noexist;
                              if(hashMap.containsKey(name)){
                                  noexist = true;
                              }
                              else{
                                  Window.alert("Airline does not exist on the server");
                                  noexist = false;
                              }
                              Airline a = (Airline) hashMap.get(name);
                              Collection<Flight> flightList;
                              flightList = a.getFlights();
                              boolean found = false;
                              if(noexist == false) {  }
                              for (Flight f : flightList) {
                                  String s = f.getSource();
                                  if (f.getSource().equals(source)) {
                                        if (f.getDestination().equals(dest)) {
                                            found = true;
                                            table.setText(j, 0, a.getName());
                                            String num = String.valueOf(f.getNumber());
                                            table.setText(j, 1, num);
                                            table.setText(j, 2, f.getSource());
                                            table.setText(j, 3, f.getDepartureString());
                                            table.setText(j, 4, f.getDestination());
                                            table.setText(j, 5, f.getArrivalString());
                                            table.setText(j, 6, f.getLength());
                                            j++;
                                        }
                                  }
                              }
                              if(found == false) { Window.alert("Airline's flight itinerary does not exist for " + source + " to " + dest); }

                          }
                      });
                  }
            }
            else{
                Window.alert("Select either the 'Create' 'Search' to either Search for flights or add a new flight");
            }
        }
        });

        Button reset = new Button("Reset Fields");
        reset.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                numberbox.setText("");
                numberbox.getElement().setAttribute("placeholder", "");
                namebox.setText("");
                namebox.getElement().setAttribute("placeholder", "");
                srcbox.setText("");
                srcbox.getElement().setAttribute("placeholder", "");
                depbox.setText("");
                depbox.getElement().setAttribute("placeholder", "");
                destbox.setText("");
                destbox.getElement().setAttribute("placeholder", "");
                arrivebox.setText("");
                arrivebox.getElement().setAttribute("placeholder", "");
            }
        });

        Button display = new Button("Display all flights");
        display.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                PingServiceAsync async = GWT.create(PingService.class);
                async.Display(new AsyncCallback<HashMap>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("!!!!!!!!!!!!!" + throwable.toString());
                    }

                    @Override
                    public void onSuccess(HashMap hashMap) {
                        table.removeAllRows();
                        table.setText(0,0, "Name");
                        table.setText(0,1, "Number");
                        table.setText(0,2, "Source");
                        table.setText(0,3, "Depart Date");
                        table.setText(0,4, "Destination");
                        table.setText(0,5, "Arrival Date");
                        table.setText(0,6, "Duration");

                        int j = i;

                        ArrayList<String> keys = new ArrayList<String>();
                        for(Object key : hashMap.keySet()){
                            String n = String.valueOf(key);
                            keys.add(n);
                        }
                        Collections.sort(keys);
                        Collections.sort(keys);
                        for(String s : keys){
                            Airline a = (Airline)hashMap.get(s);
                            Collection<Flight> flightList;
                            flightList = a.getFlights();
                            for(Flight f : flightList) {
                                table.setText(j, 0, a.getName());
                                String num = String.valueOf(f.getNumber());
                                table.setText(j, 1, num);
                                table.setText(j, 2, f.getSource());
                                table.setText(j, 3, f.getDepartureString());
                                table.setText(j, 4, f.getDestination());
                                table.setText(j, 5, f.getArrivalString());
                                table.setText(j, 6, f.getLength());
                                j++;
                            }
                        }
                    }
                });
            }
        });


        layout.setWidget(6,0, button);
        layout.setWidget(7,0, reset);
        layout.setWidget(7,1, display);

        flex.setWidget(0, 1, form);


        cellformat.setVerticalAlignment(0,1,HasVerticalAlignment.ALIGN_TOP);

        RootPanel rootPanel = RootPanel.get();
        rootPanel.add(menu);
        rootPanel.add(flex);
  }

  public void AddFlight(){

  }

    /**
     *
     * @param number of the flight to validate
     * @return the validated number
     */
  public static int validateNumber(String number){
      int num = 0;
      try{
          num = Integer.parseInt(number);

      }
      catch(NumberFormatException ex){
          Window.alert("Please use numbers for flight number");
          return 0;
      }
      return num;
  }

    /**
     *
     * @param src source/destination of the flight
     * @return the validated source/destination of the flight
     */
  public static String validateAirport(String src){
      for(char c : src.toCharArray()){
          if(!Character.isLetter(c)){
              Window.alert("Please use characters for airport code");
              return null;
          }
      }
      if(src.length() != 3){
          Window.alert("Please use the 3 letter airport code");
          return null;
      }
      String name = null;
      name = AirportNames.getName(src.toUpperCase());
      if(name == null){
          Window.alert("Please use valid airport code");
          return null;
      }
      src = src.toUpperCase();
      return src;
  }

    /**
     *
     * @param date the date string to be validated
     * @return the validated date as util.Date
     */
  public static Date validateDate(String date){
      DateTimeFormat ddate = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
      Date d = null;
      try{
          d = ddate.parse(date);
      }
      catch(Exception e){
          Window.alert("Please use valid date");
          return null;
      }
      return d;
  }

    /**
     *
     * @param ddate the departure date
     * @param adate the arrival date
     * @return the duration of the flight, to be used to validate not going back in time
     */
  public static long validateDuration(Date ddate, Date adate){
      return((adate.getTime() - ddate.getTime())/3600000);
  }

}

