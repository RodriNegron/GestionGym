import Classes.*;
import Classes.Abstract.Activity;
import Classes.Customer;
import Classes.Funcional;
import Classes.Sunday;
import Collections.Activity_list;
import Collections.Customer_list;
import Utils.Password;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import Classes.Admin;

import java.util.InputMismatchException;
import java.util.Scanner;

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
        gym.getMoth().setGains(monthlyGain);

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
            toFiles.writeFile(gym.getMoth().getGains(), gains);

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
            toFiles.writeFile(gym.getMoth().getGains(), gains);

        }
    }
    public static int optionEntry(int number){
        Scanner scanner = new Scanner(System.in);
        int op = 0;
        try{
            op = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Dato ingresado no valido");
        }
        while (number < op && op <= 0){
            scanner.reset();
            System.out.print("Ingrese una opcion valida: ");
            try{
                op = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Datos incorrectos");
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


            option = optionEntry(3);

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
                default:
                    System.out.println("Datos incorrectos!");
            }

        } while (option != 3);

    }

    public static void menuUsuario(Scanner scann, Gym gym, String salt, Sunday persistedSunday, HashMap<String, Double> monthlyGain) {
        int option = 0 ;

        Customer client;
        client = gym.checkClient();

        if (client != null) {
            do {
                System.out.println("Bienvenido " + client.getFirstName() + "," + gym.expired(client));
                System.out.println("1-Inscribirse");
                System.out.println("2-Reservar turno");
                System.out.println("3-Ingresar dinero a su billetera");
                System.out.println("4-Consultar saldo");
                System.out.println("5-Consultar turnos reservados");
                System.out.println("6-Consultar estado de cuenta");
                System.out.println("7-Consultar turnos disponibles");
                System.out.println("8-Regresar");

                option = optionEntry(8);
                switch (option) {
                    case 1:
                        if (client.getTraining_Plan() == 0) {
                            gym.consultTrainingPlanList();
                            System.out.println("A que plan desea inscribirse?");
                            int aux = scann.nextInt();
                            gym.signUp(client, aux, monthlyGain);
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
                        int cash = scann.nextInt();
                        client.getWallet().deposit(cash);
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
                        loggin(gym, salt, persistedSunday, monthlyGain);
                }
            } while (option != 8);

        } else System.out.println("Credenciales invalidas.");

    }

    public static void menuAdmin(Scanner scann, Gym gym, Admin administrator, String salt, Sunday persistedSunday, HashMap<String, Double> monthlyGain) {
        int option =0;
        Admin admin = null;
        admin = gym.checkAdmin(administrator);

        if (admin != null) {
            do {
                System.out.println("Menu Administrador");
                System.out.println("1-Consultar actividades");
                System.out.println("2-Agregar actividad");

                System.out.println("3-Eliminar actividad"); //->ETA

                System.out.println("4-Ganancia Mensual"); //->mensual //anual
                System.out.println("5-Ganancia Anual"); //->mensual //anual
                System.out.println("6-Consultar clientes");
                System.out.println("7-Consultar instructores"); //->Agregar y borrar

                //cambiar precio training plan

                System.out.println("0-Regresar");
                System.out.println("Elija una opcion: ");
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
                        String nameToDelete;
                        gym.getShifts_map().consultActivities();
                        System.out.println("A continuacion elija el nombre de la actividad que desea eliminar");
                        scann.nextLine();
                        nameToDelete = scann.nextLine();

                        Activity_list aux = gym.foundActivity(nameToDelete);

                        for (int i = 0; i < aux.getActivity_list().size(); i++) {
                            System.out.println(aux.getActivity_list().get(i).getIdActivity());
                        }

                        gym.deleteActivity(aux, gym.getCustomers_list());
                        break;
                    case 4:

                        break;
                    case 5:
                        break;
                    case 6:
                        gym.consultClients();
                        break;
                    case 7:
                        gym.consultInstructors();
                        break;
                    case 8:
                        loggin(gym, salt, persistedSunday, monthlyGain);
                }
            } while (option != 8);
        } else System.out.println("Credenciales invalidas");
    }

    public static void scannReserveShift(Gym gym, Scanner scann, Customer client, Sunday persistedSundays) {
        int num = 0;
        int time;
        String hour;
        String activity;
        String day;
        day = gym.chooseDay(persistedSundays);

        System.out.println("En que actividad desea anotarse?");
        System.out.println("1- Funcional");
        System.out.println("2- Aerobic");
        System.out.println("3- Crossfit");

        num = scann.nextInt();

        if (num == 1) activity = "Funcional";
        else if (num == 2) activity = "Aerobic";
        else activity = "Crossfit";

        System.out.println("Dentro de que rango horario?");
        System.out.println("1 - 8-9:30");
        System.out.println("2 - 10-11:30");
        System.out.println("3 - 12-13:30");
        System.out.println("4 - 14-15:30");
        System.out.println("5 - 16-17:30");
        System.out.println("6 - 18-19:30");
        time = scann.nextInt();

        if (time == 1) hour = "8-9:30";
        else if (time == 2) hour = "10-11:30";
        else if (time == 3) hour = "12-13:30";
        else if (time == 4) hour = "14-15:30";
        else if (time == 5) hour = "16-17:30";
        else hour = "18-19:30";

        gym.reserveShift(client, day, activity, hour);
    }

}

