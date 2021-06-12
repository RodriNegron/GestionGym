package Classes;

public class Wallet {
    private int id;
    private double total_Amount = 0;

    //region contructor
    public Wallet() {
    }

    public Wallet(int id) {
        this.id = id;
    }
    //endregion

    //region gett&&sett
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_Amount() {
        return total_Amount;
    }

    public void setTotal_Amount(double total_Amount) {
        this.total_Amount = total_Amount;
    }
    //endregion

    public void deposit(double amount)
    {
        System.out.println("Deposito realizado con exito!");
        this.total_Amount = this.total_Amount + amount;
    }

    public void debit (double total){
        this.setTotal_Amount(this.getTotal_Amount() - total);
    }

    @Override
    public String toString() {
        return "Billetera{" +
                ", Saldo=" + total_Amount +
                '}';
    }
}
