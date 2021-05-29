package Classes;

import Classes.Abstract.Person;
import Collections.Shift_list;

public class Customer extends Person {
    private int training_Plan;
    private Shift_list shifts;
    private Wallet wallet;

    //region constructor

    public Customer() {
        this.shifts = new Shift_list();
        this.wallet =  new Wallet(this.getId());
    }

    public Customer(String dni, String firstName, String lasName, String email, String password, int training_Plan) {
        super(dni,firstName, lasName, email, password);
        this.training_Plan = training_Plan;
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

    public void consultWalletStatus()
    {
        wallet.toString();
    }

    //use stringbuilder over void functions
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Class.Customer{" +
                "training_Plan=" + training_Plan +
                ", shifts=" + consultShiftList() +
               // ", wallet=" +  consultShiftList()  +
                '}';
    }
}
