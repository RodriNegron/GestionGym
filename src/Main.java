import Classes.Abstract.Activity;
import Classes.Customer;
import Classes.Funcional;
import Collections.Activity_list;
import Collections.Customer_list;
import Utils.Password;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import Classes.Admin;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gym gym = new Gym("Forza", "La 39-Mar del Plata", "3120492");
        //region files
        String Customer_file = "customers.json";
        String Shift_file = "shifts.json";
        Customer_list persistedList;
        HashMap<String, Activity_list> persistedMap;
        //endregion

        gym.hardcodeInstructor();
        gym.hardcodeUsers();
        gym.hardcodeTrainingPlans();
        String salt = Password.getSalt(30);


        if (LocalDate.now().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {

            gym.getShifts_map().hardcodeShifts(gym.getInstructor_list());

            persistedList = toFiles.readFile(Customer_file);

            gym.setCustomers_list(persistedList);

            gym.resetShiftsInClients();

            loggin(gym, salt);
            toFiles.writeFile(gym.getShifts_map().getDays(), Shift_file);
            toFiles.writeFile(gym.getCustomers_list(), Customer_file);

        } else {


            persistedList = toFiles.readFile(Customer_file);
            persistedMap = toFiles.readMapFile(Shift_file);

            gym.getShifts_map().setDays(persistedMap);
            gym.setCustomers_list(persistedList);

            loggin(gym, salt);
            toFiles.writeFile(gym.getShifts_map().getDays(), Shift_file);
            toFiles.writeFile(gym.getCustomers_list(), Customer_file);
        }

    }

    public static void loggin(Gym gym, String salt) {

        Scanner scann = new Scanner(System.in);
        Customer cust;
        Admin administrator = new Admin("admin@admin", "admin123");

        int number;
        char var = 's';
        String string;

        System.out.println("\nBienvenido a |" + gym.getName() + " Gym|:");
        do {
            System.out.println("\t1- Ingreso Clientes");
            System.out.println("\t2- Ingreso Admin");
            System.out.println("\t3- Registrarse");


            number = scann.nextInt();
            scann.nextLine();

            switch (number) {
                case 1:
                    menuUsuario(gym, salt);
                    break;
                case 2:
                    menuAdmin(gym, administrator, salt);
                    break;
                case 3:
                    scann.reset();
                    cust = gym.register(scann, salt);
                    gym.addToCustomerList(cust);
                    break;
                default:
                    System.out.println("Datos incorrectos!");
            }

            System.out.println("¿Desea continuar? | Opciones:s/n");
            var = scann.nextLine().charAt(0);

        } while (var == 's');

    }

    public static void menuUsuario(Gym gym, String salt) {
        Scanner scann = new Scanner(System.in);
        int number;
        char var = 's';

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
                System.out.println("0-Regresar");

                number = scann.nextInt();

                switch (number) {
                    case 1:
                        if (client.getTraining_Plan() == 0) {
                            gym.consultTrainingPlanList();
                            System.out.println("A que plan desea inscribirse?");
                            int aux = scann.nextInt();
                            gym.signUp(client, aux);
                        } else System.out.println("Ya te encuentras inscripto al sistema!");
                        break;
                    case 2:
                        int num = 0;
                        int time;
                        String hour;
                        String activity;
                        String day;

                        if (client.getTraining_Plan() != 0) {
                            day = gym.chooseDay();

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
                    case 0:
                        loggin(gym, salt);
                    default:
                        System.out.println("Usted ha intentado consultar un valor erroneo");
                }
                System.out.println("¿Desea continuar operando? | Usuario: " + client.getFirstName() + " | Opciones: s/n");
                scann.nextLine();
                var = scann.nextLine().charAt(0);
            } while (var == 's');

        } else System.out.println("Credenciales invalidas.");

    }

    public static void menuAdmin(Gym gym, Admin administrator, String salt) {
        Scanner scann = new Scanner(System.in);
        int number;
        char var = 's';

        Admin admin = null;
        admin = gym.checkAdmin(administrator);

        if (admin != null) {
            do {
                System.out.println("Menu Administrador");
                System.out.println("1-Consultar actividades");
                System.out.println("2-Agregar actividad");
                System.out.println("3-Consultar clientes");
                System.out.println("0-Regresar");
                System.out.println("Elija una opcion: ");
                number = scann.nextInt();

                switch (number) {
                    case 1:
                        gym.getShifts_map().consultActivities();
                        break;
                    case 2:
                        Activity fitness = new Funcional("Fitness");
                        gym.addActivityToList(fitness);
                        gym.getShifts_map().consultActivities();
                        break;
                    case 3:
                        gym.consultClients();
                        break;
                    case 0:
                        loggin(gym, salt);
                    default:
                        System.out.println("Usted ha intentado consultar un valor erroneo");
                }
                System.out.println("¿Desea continuar operando? | Opciones: s/n");
                scann.nextLine();
                var = scann.nextLine().charAt(0);
            } while (var == 's');
        } else System.out.println("Credenciales invalidas");
    }

}

