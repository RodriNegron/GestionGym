package Classes;

import Classes.Abstract.Person;
import Collections.Shift_list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer extends Person {
    private int training_Plan;
    protected String planStartDate;
    protected String planFinalDate;
    private Shift_list shifts;
    private Wallet wallet;
    private String salt;

    //region constructor

    public Customer(String dni, String firstName, String lastName, String email, String password, String salt) {
        super(dni,firstName, lastName, email, password);
        this.shifts = new Shift_list();
        this.wallet =  new Wallet(this.getId());
        this.salt = salt;
    }
    //endregion

    //region gett&&sett
    public int getTraining_Plan() {
        return training_Plan;
    }

    public void setTraining_Plan(int training_Plan) {
        this.training_Plan = training_Plan;
    }

    public Shift_list getShifts() {
        return shifts;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getStartDate() {
        return planStartDate;
    }

    public String getPlanFinalDate() {
        return planFinalDate;
    }

    public String getSalt() { return salt; }

    //endregion

    public void setDatesTrainingPlan()
    {
        LocalDate aux = LocalDate.now().plusDays(30);
        this.planStartDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/u"));
        this.planFinalDate = aux.format(DateTimeFormatter.ofPattern("d/M/u"));
    }

    public String consultDatesTrainingPlan()
    {
        StringBuilder builder = new StringBuilder();

        if ((planStartDate != null ) && ( planFinalDate != null))
        {
            builder.append(planStartDate);
            builder.append("\n");
            builder.append(planFinalDate);
        }

        return builder.toString();
    }

    public String consultShiftList()
    {
        return shifts.listAllSfhits();
    }

    public String consultWalletStatus()
    {
        return wallet.toString();
    }

    public void consultStatus(){
        if(training_Plan == 0) System.out.println("Usted no esta registrado en ningun plan");
        else if(training_Plan == 1) System.out.println("Usted se encuentra inscripto al plan basico");
        else if(training_Plan == 2) System.out.println("Usted se encuentra inscripto al plan premium");
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Class.Customer{" +
                "training_Plan=" + training_Plan +
                "stard && final date plan=" + consultDatesTrainingPlan() +
                ", shifts=" + consultShiftList() +
                ", wallet=" + consultWalletStatus()  +
                '}';
    }
}
