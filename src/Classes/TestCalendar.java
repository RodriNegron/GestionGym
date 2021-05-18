package Classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCalendar {


    public static void ejecutable() {

            Calendar c = Calendar.getInstance();

            Map<String[], Integer> turnosDisp = new HashMap<>();

            Map<String, Map<String[], Integer>> days = new HashMap<>();


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

            int dia = LocalDate.now().getDayOfMonth();
            DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");

            Calendar aux = Calendar.getInstance();
            aux.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            c.set(Calendar.DATE, dia-1);


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


            int domingo = aux.getTime().getDate();
            int diasAAgregar = domingo - dia;

            // guardamos los dias de la semana, desde el actual al sabado inclusive
            for (int i = 0; i < diasAAgregar ; i++) {

                c.add(Calendar.DATE, 1);  //amount es un incremental para movernos entre las fechas a agregar en el mapa

                String aAgregar = df.format(c.getTimeInMillis());
                days.put(aAgregar, turnosDisp);
            }

            reservarTurno(days, actividad, letras);

        }


        public static void reservarTurno( Map<String, Map<String[], Integer>> days, String actividad, String letras) // deberiamos recibir un cliente
        {
            days.forEach(
                    (k,v) ->
                    {
                        System.out.println(k);

                        v.forEach(
                                (hora , slot) -> {

                                    for (int aux = 0; aux < 6; aux++) {  //debemos mostrar los slot de la hora seleccionada por cada dia
                                        if (hora[6] == actividad ) {      //estamos parados dentro de la actividad consultada
                                            if(hora[aux] == letras) {
                                                System.out.println("clase: " + hora[aux]);
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

