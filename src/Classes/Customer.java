package Classes;

import Classes.Abstract.Person;
import Collections.Shift_list;

public class Customer extends Person {
    private int training_Plan;
    private Shift_list shifts;
    private Wallet wallet;
    private String salt;

    //region constructor

    public Customer() {
        this.shifts = new Shift_list();
        this.wallet =  new Wallet(this.getId());
    }

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

    public String getSalt() { return salt; }

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
