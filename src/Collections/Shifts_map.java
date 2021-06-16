package Collections;

import Classes.Abstract.Activity;
import Classes.Crossfit;
import Classes.Customer;
import Classes.Funcional;
import Classes.Shift;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Shifts_map {

    private final Scanner scann = new Scanner(System.in);

    Map<String, Activity_list> days;

    //region Constructor, setter & getter
    public Shifts_map() {
        days = new HashMap<String, Activity_list>();
    }

    public Map<String, Activity_list> getDays() {
        return days;
    }

    public void setDays(Map<String, Activity_list> days) {
        this.days = days;
    }
    //endregion

    public void hardcodeShifts(Instructor_list instructors) {
        Calendar c = Calendar.getInstance();

        // Set the calendar to monday of the current week

        int day = LocalDate.now().getDayOfMonth();

        if (LocalDate.now().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) day++;

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
            dia = 1;    //in case of sunday, list all next week

        int dayToAdd = 7 - dia;

        //we keep the days of the week, from current to Saturday inclusive
        for (int i = 0; i < dayToAdd; i++) {
            c.add(Calendar.DATE, 1);  //amount is an incremental to move between the dates to add on the map

            String dayToPut = df.format(c.getTimeInMillis());

            days.put(dayToPut, addInstructorsToActivityList(instructors)); //rest of the days in week to add

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

        for (Map.Entry<String, Activity_list> e : this.days.entrySet()) {
            String today = e.getKey();

            Activity_list activity_list = e.getValue();
            List<Activity> al = activity_list.getActivity_list();

            if (today.equals(day)) {

                for (int i = 0; i < al.size(); i++) {
                    if (al.get(i).getName().equals(activity)) {

                        Map<String, Integer> activityMap = al.get(i).getAvailableShifts();
                        for (Map.Entry<String, Integer> entry : activityMap.entrySet()) {
                            String mapHour = entry.getKey();
                            Integer slot = entry.getValue();
                            Shift shift = new Shift(day, hour, activity);
                            if (mapHour.equals(hour) && (slot != 0)) {

                                    cust.getShifts().add(shift);

                                    Integer inti = slot - 1;
                                    al.get(i).getAvailableShifts().put(hour, inti);

                                    System.out.println("Sucess");
                                    System.out.println(shift);

                                    Activity_list acts = new Activity_list();
                                    acts.setActivity_list(al);

                                    days.put(today, acts);
                            }
                        }
                    }
                }
            }
        }
    }


    //for each day of the week we need 6 shifts with 10 slots each 1 - 3 act
    //we need to create the shift, with the day the time and the date, subtract 1 slot <- the user if it is basic
    //it could have 3 turns, then it would be subtracted in a static variable

    public void consultAvailableShifts() {

        this.days.forEach(
                (day, activities) ->
                {
                    System.out.println(day);

                    List<Activity> aux = activities.getActivity_list();

                    for (int i = 0; i < aux.size(); i++) {
                        System.out.println(aux.get(i).getName());
                        aux.get(i).getAvailableShifts().forEach(
                                (hour, slot) ->
                                {
                                    System.out.println("Hora:" + hour);
                                    System.out.println("Slot:" + slot);
                                }
                        );
                    }
                });
    }

    public void consultActivities() {
        this.days.forEach(
                (day, activities) ->
                {
                    System.out.println(day);

                    List<Activity> aux = activities.getActivity_list();

                    for (int i = 0; i < aux.size(); i++) {
                        System.out.println(aux.get(i).getName());
                    }
                }
        );
    }

    public void addActivity(Activity activity, Instructor_list instructor_list) {
        days.forEach(
                (day, act) -> {
                    activity.getInstructors().add(instructor_list.getInstructors().get(6));
                    activity.getInstructors().add(instructor_list.getInstructors().get(7));
                    act.add(activity);
                }
        );
    }

    public Activity_list addInstructorsToActivityList (Instructor_list instructors){

        Activity_list aux1 = new Activity_list();

        Activity crossfit = new Crossfit("Crossfit");
        Activity funcional = new Funcional("Funcional");
        Activity aerobic = new Crossfit("Aerobic");

        crossfit.getInstructors().add(instructors.getInstructors().get(0));
        crossfit.getInstructors().add(instructors.getInstructors().get(1));
        funcional.getInstructors().add(instructors.getInstructors().get(2));
        funcional.getInstructors().add(instructors.getInstructors().get(3));
        aerobic.getInstructors().add(instructors.getInstructors().get(4));
        aerobic.getInstructors().add(instructors.getInstructors().get(5));

        aux1.add(crossfit);
        aux1.add(funcional);
        aux1.add(aerobic);

        return aux1;

    }
}
