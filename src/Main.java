import Classes.Crossfit;
import Classes.Customer;
import Classes.Shift;
import Collections.Shifts_map;

public class Main {
    public static void main(String[] args) {
        /*
        Customer cust1 = new Customer("38606789", "Alessandro", "Casella", "javi_casella95@outlook.com.ar", "Qwer1379", 0);

        Shift shift1 = new Shift("24/02/71995", "15:30", "Musculacion");

        cust1.getShifts().addShiftToClient(shift1);

        System.out.println(cust1);
        */

        Gym gym = new Gym("Forza Gym", "Mar del Plata", "3120492");
        gym.harcodeShifts();

    }



}
