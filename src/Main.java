import Classes.*;
import Classes.Abstract.Activity;
import Classes.Abstract.Training_plan;
import Collections.Shifts_map;
import Collections.TrainingPlan_list;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        Customer cust1 = new Customer("38606789", "Alessandro", "Casella", "javi_casella95@outlook.com.ar", "Qwer1379");

        Shift shift1 = new Shift("24/02/71995", "15:30", "Musculacion");

        cust1.getWallet().deposit(500);

        cust1.getShifts().addShiftToClient(shift1);

        System.out.println(cust1);
        */

       loggin();


    }


    public static void loggin(){

        Gym gym = new Gym("Forza", "La 39-Mar del Plata", "3120492");
        gym.harcodeShifts();
        gym.hardcodeUsers();

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
                    System.out.println("2-Consultar turnos disponibles");
                    System.out.println("3-Ingresar dinero a su billetera");
                    System.out.println("4-Consultar saldo");
                    System.out.println("5-Consultar estado de cuenta");

                    number = scann.nextInt();

                    switch (number) {
                        case 1:
                            Training_plan basicPlan = new basicPlan(1, "Basic Plan", 2500 );
                            Training_plan premiumPan = new basicPlan(2, "Premium Plan", 3000 );

                            TrainingPlan_list training_plan_list = new TrainingPlan_list();
                            training_plan_list.addTrainingPlan(basicPlan);
                            training_plan_list.addTrainingPlan(premiumPan);

                            training_plan_list.consultTrainingPlan();

                            break;
                        case 2:
                            gym.consultShifts();
                            break;
                        case 3:
                            System.out.println("Ingrese monto a depositar");
                            int cash =scann.nextInt();
                            client.getWallet().deposit(cash);
                            break;
                        case 4:
                            System.out.println(client.getWallet().getTotal_Amount());
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
                    System.out.println("1-admin menu");
                    System.out.println("2-admin menu");
                    System.out.println("3-admin menu");
                    System.out.println("4-admin menu");
                    System.out.println("5-admin menu");
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

