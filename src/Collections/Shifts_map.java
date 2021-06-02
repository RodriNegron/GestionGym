package Collections;

import Classes.Customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Shifts_map {

    private final Scanner scann = new Scanner(System.in);

    Map<String[], Integer> availableShifts;

    Map<String, Map<String[], Integer>> days;

    public Shifts_map() {
        availableShifts = new HashMap<>();
        days = new HashMap<>();
    }

    public void hardcodeShifts()
    {
        Calendar c = Calendar.getInstance();
        List<String[]> activityList = new ArrayList<>();

        String[] musc = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "musculacion"};
        String[] cross = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "crossfit"};
        String[] func = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "funcional"};

        activityList.add(musc);
        activityList.add(cross);
        activityList.add(func);

        for (int i = 0; i < 7; i++) {
            availableShifts.put(activityList.get(0), 10);
            availableShifts.put(activityList.get(1), 10);
            availableShifts.put(activityList.get(2), 10);
        }

        // Set the calendar to monday of the current week

        int day = LocalDate.now().getDayOfMonth();
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");

        Calendar aux = Calendar.getInstance();
        aux.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        c.set(Calendar.DATE, day-1);

        int sunday = aux.getTime().getDate();
        int dayToAdd = sunday - day;

        //we keep the days of the week, from current to Saturday inclusive
        for (int i = 0; i < dayToAdd ; i++) {

            c.add(Calendar.DATE, 1);  //amount is an incremental to move between the dates to add on the map

            String dayToPut = df.format(c.getTimeInMillis());
            days.put(dayToPut, availableShifts);
        }

    }

    public void checkWeeklyShifts()
    {
        int number = 0;
        int time;
        String hour;

        System.out.println("1- Func");
        System.out.println("2- Musc");
        System.out.println("3- Cross");

        number = scann.nextInt();
        String activity;

        if(number == 1)  activity = "funcional";
        else if(number == 2)  activity = "musculacion";
        else activity = "crossfit";

        System.out.println("What time do you want to consult?");
        System.out.println("1 - 8-9:30");
        System.out.println("2 - 10-11:30");
        System.out.println("3 - 12-13:30");
        System.out.println("4 - 14-15:30");
        System.out.println("5 - 16-17:30");
        System.out.println("6 - 18-19:30");
        time = scann.nextInt();

        if(time == 1) hour = "8-9:30";
        else if(time == 2) hour = "10-11:30";
        else if(time == 3) hour = "12-13:30";
        else if(time == 4) hour = "14-15:30";
        else if(time == 5) hour = "16-17:30";
        else hour = "18-19:30";

        days.forEach(
                (k,v) ->
                {
                    System.out.println(k);

                    v.forEach(
                            (hora , slot) -> {

                                for (int aux = 0; aux < 6; aux++) {  //we must show the slots of the time selected for each day
                                    if (hora[6] == activity ) {      //we are stopped within the queried activity
                                        if(hora[aux] == hour) {
                                            System.out.println("Class: " + hora[aux]);
                                            System.out.println("slot :" + slot.toString());
                                            //here we should check if the client has money in his wallet
                                            //In the event that you can make the payment, subtract the slot by one and create the object
                                            //turn with the selected data, and subtract the corresponding slot
                                        }
                                    }
                                }

                            });

                });
    }

    //for each day of the week we need 6 shifts with 10 slots each 1 - 3 act
    //we need to create the shift, with the day the time and the date, subtract 1 slot <- the user if it is basic
    //it could have 3 turns, then it would be subtracted in a static variable

    public void reserveShift(Customer customer)
    {

    }

}


