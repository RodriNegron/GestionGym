package Classes;

import Classes.Abstract.Person;
import Collections.Shift_list;

import java.time.LocalDate;

public class Customer extends Person {
    private int training_Plan;
    protected LocalDate planStartDate;
    protected LocalDate planFinalDate;
    private Shift_list shifts;
    private Wallet wallet;

    /*
            this.startDate = LocalDate.now();
        this.finalDate = startDate.plusDays(30);

            public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

     */

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

    //endregion

    public String consultShiftList()
    {
        return shifts.listAllSfhits();
    }

    public String consultWalletStatus()
    {
        return wallet.toString();
    }

    //use stringbuilder over void functions
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Class.Customer{" +
                "training_Plan=" + training_Plan +
                ", shifts=" + consultShiftList() +
                ", wallet=" + consultWalletStatus()  +
                '}';
    }
}
