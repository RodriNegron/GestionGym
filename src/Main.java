import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


    training_plan basic = new basicPlan(0, LocalDate.now(), "Basico", 3000);
    training_plan premium = new premiumPlan(1, LocalDate.now(), "Premium", 4000);
        System.out.println(basic);
        System.out.println(premium);


    }
}
