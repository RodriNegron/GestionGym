import Classes.*;
import Classes.Abstract.Activity;
import Collections.Activity_list;
import Collections.Customer_list;
import Utils.Password;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym("Forza", "La 39-Mar del Plata", "3120492");
        //region files
        String Customer_file = "customers.json";
        String Shift_file = "shifts.json";
        String sunday = "sundays.json";
        String gains = "montlhyGains.json";

        Customer_list persistedList;
        HashMap<String, Activity_list> persistedMap;
        Sunday persistedSundays;
        HashMap<String, Double> monthlyGain;
        //endregion

        gym.hardcodeInstructor();
        gym.hardcodeUsers();
        gym.hardcodeTrainingPlans();
        String salt = Password.getSalt(30);

        persistedSundays = toFiles.readSundayFile(sunday);
        monthlyGain = toFiles.readMonthlyGains(gains);
        gym.getMonthlyGain().setGains(monthlyGain);

        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/u"));

        if ((LocalDate.now().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) && (persistedSundays.getAux() == 0)) {

            persistedSundays.setAux(1);
            persistedSundays.setSunday(LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/u")));
            persistedSundays.setNextSunday(LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("d/M/u")));

            persistedList = toFiles.readFile(Customer_file);
            gym.setCustomers_list(persistedList);

            gym.getShifts_map().hardcodeShifts(gym.getInstructor_list());
            gym.resetShiftsInClients();

            loggin(gym, salt, persistedSundays, monthlyGain);

            toFiles.writeFile(gym.getShifts_map().getDays(), Shift_file);
            toFiles.writeFile(gym.getCustomers_list(), Customer_file);
            toFiles.writeFile(persistedSundays, sunday);
            toFiles.writeFile(gym.getMonthlyGain().getGains(), gains);

        } else if (day.equals(persistedSundays.getNextSunday())) {
            System.out.println("Al ser domingo, al volver a ingresar se resetearan los turnos semanales");
            persistedSundays.setAux(0);
            toFiles.writeFile(persistedSundays, sunday);
        } else {

            persistedList = toFiles.readFile(Customer_file);
            persistedMap = toFiles.readMapFile(Shift_file);

            gym.setCustomers_list(persistedList);

            gym.getShifts_map().setDays(persistedMap);


            loggin(gym, salt, persistedSundays, monthlyGain);

            toFiles.writeFile(gym.getShifts_map().getDays(), Shift_file);
            toFiles.writeFile(gym.getCustomers_list(), Customer_file);
            toFiles.writeFile(gym.getMonthlyGain().getGains(), gains);

        }
    }

    public static int optionEntry(int number) {
        Scanner scanner = new Scanner(System.in);
        int op = 0;
        try {
            op = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dato ingresado no valido");
        }
        while (number < op || op <= 0) {
            scanner.reset();
            System.out.print("Ingrese una opcion valida: ");
            try {
                op = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Datos incorrectos");
                op = 0;
                break;
            }
        }
        return op;
    }

    public static void loggin(Gym gym, String salt, Sunday persistedSunday, HashMap<String, Double> monthlyGain) {

        Scanner scann = new Scanner(System.in);
        Customer cust;
        Admin administrator = new Admin("admin@admin", "admin123");

        int option = 0;
        String string;

        System.out.println("\nBienvenido a |" + gym.getName() + " Gym|:");
        do {
            System.out.println("\t1- Ingreso Clientes");
            System.out.println("\t2- Ingreso Admin");
            System.out.println("\t3- Registrarse");
            System.out.println("\t4- Salir");


            option = optionEntry(4);

            switch (option) {
                case 1:
                    menuUsuario(scann, gym, salt, persistedSunday, monthlyGain);
                    break;
                case 2:
                    menuAdmin(scann, gym, administrator, salt, persistedSunday, monthlyGain);
                    break;
                case 3:
                    scann.reset();
                    cust = gym.register(scann, salt);
                    gym.addToCustomerList(cust);
                    break;
                case 4:
                    System.out.println("Gracias por utilizar el sistema, Vuelva prontos.");
                    break;
            }

        } while (option != 4);

    }

    public static void menuUsuario(Scanner scann, Gym gym, String salt, Sunday persistedSunday, HashMap<String, Double> monthlyGain) {
        int option = 0;

        Customer client;
        client = gym.checkClient();

        if (client != null) {
            do {
                System.out.println("-------------------");
                System.out.println("Bienvenido " + client.getFirstName() + "," + gym.expired(client));
                System.out.println("1-Inscribirse");
                System.out.println("2-Reservar turno");
                System.out.println("3-Ingresar dinero a su billetera");
                System.out.println("4-Consultar saldo");
                System.out.println("5-Consultar turnos reservados");
                System.out.println("6-Consultar estado de cuenta");
                System.out.println("7-Consultar turnos disponibles");
                System.out.println("8-Regresar");
                System.out.println("-------------------");

                option = optionEntry(8);
                switch (option) {
                    case 1:
                        if (client.getTraining_Plan() == 0) {
                            gym.consultTrainingPlanList();
                            System.out.println("A que plan desea inscribirse?");
                            int op = optionEntry(2);
                            if (op == 0) break;
                            gym.signUp(client, op, monthlyGain);
                            scann.nextLine();
                        } else System.out.println("Ya te encuentras inscripto al sistema!");
                        break;
                    case 2:

                        if (client.getTraining_Plan() != 0) {
                            if (client.getTraining_Plan() == 2) {
                                scannReserveShift(gym, scann, client, persistedSunday);
                            } else if ((client.getTraining_Plan() == 1) && (client.getShifts().getShift_list().size() < 3))
                                scannReserveShift(gym, scann, client, persistedSunday);
                            else {
                                System.out.println("Ya tiene 3 turnos reservados. ");
                            }

                        } else
                            System.out.println("Usted no se encuentra en ningun plan de entrenamiento por el momento");

                        break;

                    case 3:
                        System.out.println("Ingrese monto a depositar");
                        int cash = 0;
                        try {
                            cash = scann.nextInt();
                            client.getWallet().deposit(cash);
                        } catch (InputMismatchException e) {
                            System.out.println("Dato ingresado no valido");
                            scann.nextLine();
                        }
                        break;
                    case 4:
                        System.out.println(client.getWallet().getTotal_Amount());
                        break;
                    case 5:
                        gym.consultShiftsOnClient(client);
                        break;
                    case 6:
                        gym.consultStatusOfUser(client);
                        break;
                    case 7:
                        gym.checkAvailableShifts();
                        break;
                    case 8:
                        break;
                }
            } while (option != 8);

        } else System.out.println("Credenciales invalidas.");

    }

    public static void menuAdmin(Scanner scann, Gym gym, Admin administrator, String salt, Sunday persistedSunday, HashMap<String, Double> monthlyGain) {
        int option = 0;
        Admin admin = null;
        admin = gym.checkAdmin(administrator);

        if (admin != null) {
            do {
                System.out.println("-------------------");
                System.out.println("Menu Administrador");
                System.out.println("1-Consultar actividades");
                System.out.println("2-Agregar actividad");
                System.out.println("3-Eliminar actividad");
                System.out.println("4-Ganancia Mensual");
                System.out.println("5-Ganancia Anual");
                System.out.println("6-Consultar clientes");
                System.out.println("7-Consultar instructores");
                System.out.println("8-Regresar");
                System.out.println("Elija una opcion: ");
                System.out.println("-------------------");

                option = optionEntry(8);
                switch (option) {
                    case 1:
                        gym.getShifts_map().consultActivities();
                        break;
                    case 2:
                        Activity fitness = new Funcional("Fitness");

                        gym.addActivityToList(fitness);
                        gym.getShifts_map().consultActivitiesByDays();
                        break;
                    case 3:
                        scann.reset();
                        String nameToDelete;
                        gym.getShifts_map().consultActivities();
                        System.out.println("A continuacion elija el nombre de la actividad que desea eliminar");
                        AtomicBoolean found = new AtomicBoolean(false);
                        nameToDelete = scann.nextLine();
                        if ((nameToDelete.compareTo("Crossfit") == 0) || (nameToDelete.compareTo("Aerobic") == 0) || (nameToDelete.compareTo("Funcional") == 0) || (nameToDelete.compareTo("Fitness") == 0)) {
                            gym.getShifts_map().getDays().forEach(
                                    (k, v) -> {
                                        for (int i = 0; i < v.getActivity_list().size(); i++) {
                                            if (v.getActivity_list().get(i).getName().equals(nameToDelete)) {
                                                found.set(true);
                                            }
                                        }
                                    }
                            );
                            if (found.get()) {
                                Activity_list aux = gym.foundActivity(nameToDelete);
                                gym.deleteActivity(aux, gym.getCustomers_list());
                            }
                        }

                        break;
                    case 4:
                        Double gainByMonth = gym.chekMonthlyGain();
                        System.out.println("Ganancia mensual hasta el momento: " + gainByMonth);
                        break;
                    case 5:
                        Double gainByYear = gym.checkGainsThisYear();
                        System.out.println("Ganancia anual hasta el momento: " + gainByYear);
                        break;
                    case 6:
                        gym.consultClients();
                        break;
                    case 7:
                        gym.consultInstructors();
                        break;
                    case 8:
                        break;
                }
            } while (option != 8);
        } else System.out.println("Credenciales invalidas");
    }

    public static void scannReserveShift(Gym gym, Scanner scann, Customer client, Sunday persistedSundays) {
        int time;
        String hour = " ";
        String activity;
        String day;
        AtomicBoolean found = new AtomicBoolean(false);
        System.out.println("En que actividad desea anotarse?");
        gym.getShifts_map().consultActivities();
        activity = scann.nextLine();

        if ((activity.compareTo("Crossfit") == 0) || (activity.compareTo("Aerobic") == 0) || (activity.compareTo("Funcional") == 0) || (activity.compareTo("Fitness") == 0)) {
            gym.getShifts_map().getDays().forEach(
                    (k, v) -> {
                        for (int i = 0; i < v.getActivity_list().size(); i++) {
                            if (v.getActivity_list().get(i).getName().equals(activity)) {
                                found.set(true);
                            }
                        }
                    }
            );
            if (found.get()) {
                String[] hours = new String[6];
                int auxi = 0;
                hours[0] = "8-9:30";
                hours[1] = "10-11:30";
                hours[2] = "12-13:30";
                hours[3] = "14-15:30";
                hours[4] = "16-17:30";
                hours[5] = "18-19:30";

                day = gym.chooseDay(persistedSundays);

                String dateAux = LocalDate.now().format(DateTimeFormatter.ofPattern("EEE dd/MM/yyyy"));

                String[] newHours = new String[6];
                if (day.equals(dateAux)) {
                    int aux = LocalDateTime.now().getHour();

                    for (int i = 0, j = 0; i < hours.length; i++) {
                        if (hours[i] == "8-9:30") hours[i] = "08-9:30";

                        int hooooour = Integer.valueOf(hours[i].substring(0, 2));
                        if (hooooour >= aux) {
                            newHours[j] = hours[i];
                            j++;
                            auxi++;
                        }
                    }
                    System.out.println("Dentro de que rango horario?");
                    for (int j = 0; j < newHours.length && newHours[j] != null; j++) {
                        System.out.println(j + " " + newHours[j]);
                    }

                    if (newHours[0] == null) System.out.println("No hay turnos disponibles para el dia de la fecha");
                    else if (newHours[auxi - 1] != null) {
                        try {
                            time = scann.nextInt();
                            scann.nextLine();

                            hour = newHours[time];

                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Dato ingresado no valido");
                        } catch (InputMismatchException e) {
                            System.out.println("Dato ingresado no valido");
                            scann.nextLine();
                        }
                    }

                    gym.reserveShift(client, day, activity, hour);

                } else {
                    try {
                        System.out.println("Dentro de que rango horario?");
                        System.out.println("1 - " + hours[0]);
                        System.out.println("2 - " + hours[1]);
                        System.out.println("3 - " + hours[2]);
                        System.out.println("4 - " + hours[3]);
                        System.out.println("5 - " + hours[4]);
                        System.out.println("6 - " + hours[5]);

                        time = scann.nextInt();
                        scann.nextLine();

                        if (time == 1) hour = hours[0];
                        if (time == 2) hour = hours[1];
                        if (time == 3) hour = hours[2];
                        if (time == 4) hour = hours[3];
                        if (time == 5) hour = hours[4];
                        if (time == 6) hour = hours[5];

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Dato ingresado no valido");
                    } catch (InputMismatchException e) {
                        System.out.println("Dato ingresado no valido");
                        scann.nextLine();
                    } finally {
                        gym.reserveShift(client, day, activity, hour);
                    }
                }
            }
        }

    }

}

