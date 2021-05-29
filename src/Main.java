import Classes.Abstract.Activity;
import Classes.Aerobic;
import Classes.Crossfit;
import Classes.Customer;
import Classes.Shift;
import Collections.Shifts_map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Customer cust1 = new Customer("38606789", "Alessandro", "Casella", "javi_casella95@outlook.com.ar", "Qwer1379", 0);

        Shift shift1 = new Shift("24/02/71995", "15:30", "Musculacion");

        cust1.getWallet().deposit(500);

        cust1.getShifts().addShiftToClient(shift1);

        System.out.println(cust1);
        */

        //gym.consultShifts();

        loggin();

    }


    public static void loggin(){
        Scanner scann = new Scanner(System.in);
        Customer cust;

        Gym gym = new Gym("Forza", "La 39-Mar del Plata", "3120492");
        gym.harcodeShifts();
        gym.hardcodeUsers();

        int number;
        char var = 's';
        String string;


        System.out.println("Welcome to the gym: " + gym.getName());
        do {
            System.out.println("1- Logg-In");
            System.out.println("2- Register");

            number = scann.nextInt();
            scann.nextLine();

            switch (number) {
                case 1:

                    break;

                case 2:
                    cust = gym.register(scann);
                    gym.addToCustomerList(cust);
                    break;

                default:
                    System.out.println("Wrong data!");
            }

            System.out.println("Do you want to continue s/n");
            var = scann.nextLine().charAt(0);

        } while (var == 's');

    }


}
