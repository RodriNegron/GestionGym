package Classes;

import Classes.Abstract.Person;
import Collections.Shift_list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer extends Person {
    private int training_Plan;
    protected LocalDate planStartDate;
    protected LocalDate planFinalDate;
    private Shift_list shifts;
    private Wallet wallet;

    //region constructor

    public Customer() {
        this.shifts = new Shift_list();
        this.wallet =  new Wallet(this.getId());
    }

    public Customer(String dni, String firstName, String lastName, String email, String password) {
        super(dni,firstName, lastName, email, password);
        this.shifts = new Shift_list();
        this.wallet =  new Wallet(this.getId());
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

    public LocalDate getStartDate() {
        return planStartDate;
    }

    public LocalDate getPlanFinalDate() {
        return planFinalDate;
    }

    //endregion


    public void setDatesTrainingPlan()
    {
        this.planStartDate = LocalDate.now();
        this.planFinalDate = planStartDate.plusDays(30);
    }

    public String consultDatesTrainingPlan()
    {
        StringBuilder builder = new StringBuilder();

        if ((planStartDate != null ) && ( planFinalDate != null))
        {
            builder.append(planStartDate.format(DateTimeFormatter.ofPattern("d/M/u")));
            builder.append("\n");
            builder.append(planFinalDate.format(DateTimeFormatter.ofPattern("d/M/u")));
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



    //use stringbuilder over void functions
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
