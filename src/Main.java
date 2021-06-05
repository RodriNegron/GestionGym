import Classes.Customer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gym gym = new Gym ("Forza", "La 39-Mar del Plata", "3120492");
        gym.harcodeShifts();
        gym.hardcodeUsers();
        gym.hardcodeTrainingPlans();

        loggin(gym);

    }


    public static void loggin(Gym gym){


        Scanner scann = new Scanner(System.in);
        Customer cust;

        int number;
        char var = 's';
        String string;


        System.out.println("Bienvenido a " + gym.getName() +" gym:");
        do {
            System.out.println("1- Ingresar");
            System.out.println("2- Registrarse");

            number = scann.nextInt();
            scann.nextLine();

            switch (number) {
                case 1:
                    menu(gym);
                    break;
                case 2:
                    scann.reset();
                    cust = gym.register(scann);
                    gym.addToCustomerList(cust);
                    break;
                default:
                    System.out.println("Datos incorrectos!");
            }

            System.out.println("¿Desea continuar? | Opciones:s/n");
            var = scann.nextLine().charAt(0);

        } while (var == 's');

    }

    public static void menu(Gym gym) {
        Scanner scann = new Scanner(System.in);
        int number;
        char var = 's';

        Customer client;
        client = gym.checkClient();

        if (client != null) {
            if (client.getFirstName().compareTo("admin")!=0 ) {
                do {
                    System.out.println("Bienvenido " + client.getFirstName() + "!:D");
                    System.out.println("1-Inscribirse");
                    System.out.println("2-Reservar turno");
                    System.out.println("3-Ingresar dinero a su billetera");
                    System.out.println("4-Consultar saldo");
                    System.out.println("5-Consultar turnos reservados");
                    System.out.println("6-Consultar estado de cuenta");
                    System.out.println("7-Consultar turnos disponibles");


                    number = scann.nextInt();

                    switch (number) {
                        case 1:
                            if(client.getTraining_Plan() == 0) {
                                gym.consultTrainingPlanList();
                                System.out.println("A que plan desea inscribirse?");
                                int aux = scann.nextInt();
                                gym.signUp(client, aux);
                            }else System.out.println("Ya te encuentras inscripto al sistema!");
                            break;
                        case 2:
                            int num = 0;
                            int time;
                            String hour;
                            String activity;
                            String day;

                            if(client.getTraining_Plan() !=0 ) {
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
                            }
                            else System.out.println("Usted no se encuentra en ningun plan de entrenamiento por el momento");

                            break;

                        case 3:
                            System.out.println("Ingrese monto a depositar");
                            int cash =scann.nextInt();
                            client.getWallet().deposit(cash);
                            break;
                        case 4:
                            System.out.println(client.getWallet().getTotal_Amount());
                            break;
                        case 5:
                            System.out.println(gym.consultShiftsOnClient(client));
                            break;
                        case 6:
                            gym.consultStatusOfUser(client);
                            break;
                        case 7:
                            gym.checkAvailableShifts();
                            break;
                        default:
                            System.out.println("Usted ha intentado consultar un valor erroneo");
                    }
                    System.out.println("¿Desea continuar operando? | Usuario: " + client.getFirstName() + " | Opciones: s/n");
                    scann.nextLine();
                    var = scann.nextLine().charAt(0);
                } while (var == 's');
            }
            else {
                do {
                    System.out.println("Bienvenido " + client.getFirstName() + "!:D");
                    System.out.println("1-admin menu"); //agregar actividad
                    System.out.println("2-admin menu"); //total ganancias
                    System.out.println("3-admin menu"); //consultar actividades
                    System.out.println("4-admin menu"); //consultar clientes
                    System.out.println("5-admin menu"); //modificar y consultar plan entrenamiento
                    System.out.println("Elija una opcion: ");
                    number = scann.nextInt();

                    switch (number) {
                        /*case 1:
                            System.out.println(gym);
                            break;
                        case 2:
                            System.out.println(gym.);
                            break;
                        case 3:
                            System.out.println(gym.);
                            break;
                        case 4:
                            gym.;
                            break;
                        case 5:
                            gym.;
                            break;*/
                        default:
                            System.out.println("Usted ha intentado consultar un valor erroneo");
                    }
                    System.out.println("¿Desea continuar operando? | Usuario: " + client.getFirstName() + " | Opciones: s/n");
                    scann.nextLine();
                    var = scann.nextLine().charAt(0);
                } while (var == 's');
            }
        }else   System.out.println("Credenciales invalidas");
    }

}

