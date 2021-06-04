package Collections;

import Classes.Customer;
import Classes.Shift;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Shifts_map {

    private final Scanner scann = new Scanner(System.in);

    Map<String[], Integer> availableShifts;

    Map<String, Map<String[], Integer>> days;

    public Shifts_map() {
        availableShifts = new HashMap<String[], Integer>();
        days = new HashMap<String, Map<String[], Integer>>();
    }

    public void hardcodeShifts() {
        Calendar c = Calendar.getInstance();
        List<String[]> activityList = new ArrayList<>();


        String[] musc = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "musculacion"};
        String[] cross = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "crossfit"};
        String[] func = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "funcional"};

        activityList.add(musc);
        activityList.add(cross);
        activityList.add(func);

        availableShifts.put(activityList.get(0), 10);
        availableShifts.put(activityList.get(1), 10);
        availableShifts.put(activityList.get(2), 10);



        // Set the calendar to monday of the current week

        int day = LocalDate.now().getDayOfMonth();
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");

        Calendar aux = Calendar.getInstance();

        c.set(Calendar.DATE, day - 1);

        int dia = 0;

        if (aux.get(Calendar.DAY_OF_WEEK) == 2) dia = 1;
        else if (aux.get(Calendar.DAY_OF_WEEK) == 3) dia = 2;
        else if (aux.get(Calendar.DAY_OF_WEEK) == 4) dia = 3;
        else if (aux.get(Calendar.DAY_OF_WEEK) == 5) dia = 4;
        else if (aux.get(Calendar.DAY_OF_WEEK) == 6) dia = 5;
        else if (aux.get(Calendar.DAY_OF_WEEK) == 7) dia = 6;
        else if (aux.get(Calendar.DAY_OF_WEEK) == 1)
            dia = 1; //en caso de que sea domingo, muestra toda la semana siguiente

        int dayToAdd = 7 - dia;

        //we keep the days of the week, from current to Saturday inclusive
        for (int i = 0; i < dayToAdd; i++) {

            c.add(Calendar.DATE, 1);  //amount is an incremental to move between the dates to add on the map


            String dayToPut = df.format(c.getTimeInMillis());
            days.put(dayToPut, availableShifts);
        }

    }

    public String chooseDay() {
        AtomicInteger i = new AtomicInteger();
        int day;

        String[] aux = new String[7];


        this.days.forEach(
                (k, v) ->
                {
                    System.out.println(i + " " + k);
                    aux[i.get()] = k;
                    i.getAndIncrement();
                });
        System.out.println("En que dia de la corriente semana desea anotarse?");
        day = scann.nextInt();

        return aux[day];
    }

    public void reserveShift(Customer cust, String day, String activity, String hour) {

        this.days.forEach(
                (k, v) ->
                {
                     if (k.equals(day)) {
                         v.forEach(
                                 (hora, slot) -> {
                                     for (int i = 0 ; i < hora.length ; i++) {
                                         if (hora[i].equals(activity)) {

                                             String[] auxiliar = hora;

                                             for (int j = 0 ; j < auxiliar.length; j++) {
                                                 if(auxiliar[j].equals(hour))
                                                 {
                                                     if (slot != 0) {

                                                         Shift shift = new Shift(day, hour, activity);
                                                         if ((cust.getTraining_Plan() == 1) && (cust.getShifts().shift_list.size() < 3)) {
                                                             cust.getShifts().addShiftToClient(shift);
                                                             slot = slot -1;
                                                             System.out.println("Sucess");
                                                             System.out.println(shift);
                                                         } else if (cust.getTraining_Plan() == 2) {

                                                             cust.getShifts().addShiftToClient(shift);
                                                             slot = slot -1;
                                                             System.out.println("Sucess");
                                                             System.out.println(shift);
                                                         } else System.out.println("Wrong case");
                                                     } else System.out.println("All shifts are reserved");

                                                 }
                                             }

                                         }
                                     }
                                 });
                     }
                });
    }


    //for each day of the week we need 6 shifts with 10 slots each 1 - 3 act
    //we need to create the shift, with the day the time and the date, subtract 1 slot <- the user if it is basic
    //it could have 3 turns, then it would be subtracted in a static variable


    public void consultAvailableShifts() {

        this.days.forEach(
                (k,v) ->
                {
                    System.out.println(k);

                    v.forEach(

                        (activity, slot) ->
                        {
                                if(activity[6].equals("musculacion")){
                                    String[] auxiliar = activity;

                                    System.out.println("Musculacion");
                                    for (int j = 0 ; j < auxiliar.length ; j++)
                                    {
                                        if ((j != 6)) {
                                            System.out.println(auxiliar[j]);
                                            System.out.println(slot);
                                        }
                                    }

                                }
                                else if (activity[6].equals("crossfit"))
                                {
                                    String[] auxiliar = activity;

                                    System.out.println("Crossfit");

                                    for (int j = 0 ; j < auxiliar.length ; j++)
                                    {
                                        if ((j != 6)) {
                                            System.out.println(auxiliar[j]);
                                            System.out.println(slot);
                                        }
                                    }
                                }
                                else {
                                    String[] auxiliar = activity;

                                    System.out.println("Funcional");

                                    for (int j = 0; j < auxiliar.length; j++) {
                                        if ((j != 6)) {
                                            System.out.println(auxiliar[j]);
                                            System.out.println(slot);

                                        }
                                    }
                                }
                        }

                    );
                }
        );
    }
}

