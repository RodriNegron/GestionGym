package Collections;

import Classes.*;
import Classes.Abstract.Activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


        //we keep the days of the week, from current to Saturday inclusive
        for (int i = 0; i < 6; i++) {
            c.add(Calendar.DATE, 1);  //amount is an incremental to move between the dates to add on the map

            String dayToPut = df.format(c.getTimeInMillis());

            days.put(dayToPut, addInstructorsToActivityList(instructors)); //rest of the days in week to add

        }

    }


    public String chooseDay(Sunday persistedSunday) {
        AtomicInteger i = new AtomicInteger();
        i.set(0);
        String[] dates = new String[7];
        dates[0] = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE dd/MM/yyyy"));


        int day = Integer.valueOf(dates[0].substring(5, 7));
        int nextSund = Integer.valueOf(persistedSunday.getNextSunday().substring(0, 2));

        int daysToShow = nextSund - day;

        for (int j = 1; j < daysToShow; j++) {
            dates[j] = LocalDate.now().plusDays(j).format(DateTimeFormatter.ofPattern("EEE dd/MM/yyyy"));
        }

        String[] toShow = new String[7];

        for (int j = 0; j < dates.length && dates[j] != null; j++) {
            int finalJ = j;
            this.days.forEach(
                    (k, v) ->
                    {
                        if (dates[finalJ].equals((k))) {
                            System.out.println(i + " " + k);
                            toShow[i.get()] = k;
                            i.getAndIncrement();
                        }
                    });
        }
        do {
            System.out.println("En que dia de la corriente semana desea anotarse?");
            try{
                day = scann.nextInt();
                scann.nextLine();
            }catch (InputMismatchException e){
                System.out.println("erroooor");
            }catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }while (toShow[day]!=null);
        return toShow[day];
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

                            if (mapHour.equals(hour) && (slot != 0)) {

                                Shift shift = new Shift(day, hour, activity);

                                boolean found = false;

                                for (int c = 0; c < cust.getShifts().getShift_list().size(); c++) {
                                    if (cust.getShifts().getShift_list().get(c).hashCode() == shift.hashCode()) {
                                        found = true;
                                    }
                                }

                                if (!found) {
                                    cust.getShifts().add(shift);

                                    Integer inti = slot - 1;
                                    al.get(i).getAvailableShifts().put(hour, inti);

                                    System.out.println("Exito");
                                    System.out.println(shift);

                                    Activity_list acts = new Activity_list();
                                    acts.setActivity_list(al);

                                    days.put(today, acts);
                                }
                                else System.out.println("Usted ya posee este turno!");
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

    public void consultActivitiesByDays() {
        this.days.forEach(
                (day, activities) ->
                {
                    System.out.println(day);

                    List<Activity> aux = activities.getActivity_list();

                    for (int i = 0; i < aux.size(); i++) {
                        System.out.println("Nombre: " + aux.get(i).getName());
                    }
                }
        );
    }

    public void consultActivities() {
        Activity_list aux = this.days.get(LocalDate.now().format(DateTimeFormatter.ofPattern("EEE dd/MM/yyyy")));

        for (int i = 0; i < aux.getActivity_list().size(); i++) {
            System.out.println("id: " + i + " " + "nombre: " + aux.getActivity_list().get(i).getName());
        }
    }


    public Activity_list getActivityByName(String name) {
        Activity_list aux = new Activity_list();

        for (Map.Entry<String, Activity_list> e : this.days.entrySet()) {

            Activity_list activity_list = e.getValue();
            List<Activity> al = activity_list.getActivity_list();

            for (int i = 0; i < al.size() && al.get(i) != null; i++) {
                if (al.get(i).getName().compareTo(name) == 0) {
                    aux.add(al.get(i));
                }
            }
        }
        return aux;
    }

    public Activity_list addInstructorsToActivityList(Instructor_list instructors) {

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

    public void addActivity(Activity activity, Instructor_list instructor_list) {
        days.forEach(
                (day, act) -> {
                    activity.getInstructors().add(instructor_list.getInstructors().get(6));
                    activity.getInstructors().add(instructor_list.getInstructors().get(7));
                    act.add(activity);
                }
        );
    }

    public void deleteActivity(Activity_list activities, Customer_list customer_list) {

        days.forEach(
                (day, act) -> {
                    for (int j = 0; j < activities.getActivity_list().size() && activities.getActivity_list().get(j) != null; j++) {
                        for (int i = 0; i < act.getActivity_list().size() && act.getActivity_list().get(i) != null; i++) {
                            if (act.getActivity_list().get(i).getIdActivity() == activities.getActivity_list().get(j).getIdActivity())
                                act.getActivity_list().remove(act.getActivity_list().get(i));

                        }
                    }

                }
        );
        for (int i = 0; i < customer_list.getCustomers_list().size(); i++) {
            customer_list.getCustomers_list().get(i).getShifts().deleteShiftsByActivity(activities.getActivity_list().get(i));
        }
        System.out.println("Se han eliminado con exito la actividad, y los turnos de los clientes reservados sobre la misma");
    }
}
