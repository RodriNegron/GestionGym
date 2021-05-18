import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCalendar {


    public static void ejecutable() {
        Calendar c = Calendar.getInstance();

        Map<String[], Integer> turnosDisp = new HashMap<>();

        Map<Calendar, Map<String[], Integer>> days = new HashMap<>();


        List<String[]> listadoDeActividades = new ArrayList<>();


        String[] musc = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "musculacion"};
        String[] cross = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "crossfit"};
        String[] func = {"8-9:30", "10-11:30", "12-13:30", "14-15:30", "16-17:30", "18-19:30", "funcional"};


        listadoDeActividades.add(musc);
        listadoDeActividades.add(cross);
        listadoDeActividades.add(func);

        for (int i = 0; i < 7; i++) {
            turnosDisp.put(listadoDeActividades.get(0), 10);
            turnosDisp.put(listadoDeActividades.get(1), 10);
            turnosDisp.put(listadoDeActividades.get(2), 10);

        }


        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        
        Scanner scann = new Scanner(System.in);

        int numero = 0;
        System.out.println("1- Func");
        System.out.println("2- Musc");
        System.out.println("3- Cross");

        numero = scann.nextInt();
        String actividad;

        if(numero == 1)  actividad = "funcional";
        else if(numero == 2)  actividad = "musculacion";
        else actividad = "crossfit";

        int horario;
        String letras;

        System.out.println("Que horario desea consultar?");
        System.out.println("1 - 8-9:30");
        System.out.println("2 - 10-11:30");
        System.out.println("3 - 12-13:30");
        System.out.println("4 - 14-15:30");
        System.out.println("5 - 16-17:30");
        System.out.println("6 - 18-19:30");
        horario = scann.nextInt();

        if(horario == 1) letras = "8-9:30";
        else if(horario == 2) letras = "10-11:30";
        else if(horario == 3) letras = "12-13:30";
        else if(horario == 4) letras = "14-15:30";
        else if(horario == 5) letras = "16-17:30";
        else letras = "18-19:30";

        // Print dates of the current week starting on Monday to Friday
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (int i = 0; i <= 6; i++) {
            System.out.println(df.format(c.getTime()));
            c.add(Calendar.DATE, 1);
            days.put(c, turnosDisp);


            days.forEach(
                    (k,v) ->
                    {
                        v.forEach(
                                (hora , slot) -> {
                                    if (hora[6] == actividad) {
                                        for (int aux = 0; aux < 6; aux++) {
                                            if (hora[aux] == letras) {
                                                System.out.println("clase: " + hora[aux]);
                                                System.out.println("slot :" + slot.toString());
                                            }
                                        }
                                    }
                                }
                        );

                    }
            );
        }

    }

    //por cada dia de la semana necesitamos 6 turnos con 10 slot cada 1
}