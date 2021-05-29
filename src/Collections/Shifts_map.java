package Collections;

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

            // guardamos los dias de la semana, desde el actual al sabado inclusive
            for (int i = 0; i < dayToAdd ; i++) {

                c.add(Calendar.DATE, 1);  //amount es un incremental para movernos entre las fechas a agregar en el mapa

                String dayToPut = df.format(c.getTimeInMillis());
                days.put(dayToPut, availableShifts);
            }

        }

        public void consultShifts() // deberiamos recibir un cliente
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

                                    for (int aux = 0; aux < 6; aux++) {  //debemos mostrar los slot de la hora seleccionada por cada dia
                                        if (hora[6] == activity ) {      //estamos parados dentro de la activity consultada
                                            if(hora[aux] == hour) {
                                                System.out.println("Class: " + hora[aux]);
                                                System.out.println("slot :" + slot.toString());
                                                //aqui deberiamos consultar si el cliente tiene dinero en su billetera
                                                //en el caso de que pueda efectuar el pago, restar en uno al slot y crear el objeto
                                                //turno con los datos seleccionados, y restar el correspondiente slot
                                            }
                                        }
                                    }

                                }
                        );

                    }
            );
        }

        //por cada dia de la semana necesitamos 6 turnos con 10 slot cada 1 - 3 act
        //tiene que crear el turno, con el dia ell horario y la fecha, resta 1 slot <- el usuario si es basico
        //podia tener 3 turnos, entonces se restaria en una variable static

    }

