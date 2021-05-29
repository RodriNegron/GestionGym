package Classes.Abstract;

import java.time.LocalDate;

public abstract class training_plan {


    protected int id;
    protected LocalDate startDate;
    protected LocalDate finalDate;
    protected double price;


    public training_plan(){

    }

    public training_plan(int id) {
        this.id = id;
        this.startDate = LocalDate.now();
        this.finalDate = startDate.plusDays(30);
   }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plan {" +
                "id: " + id +
                ", Fecha de Inicio: " + startDate +
                ", Precio: " + price +
                '}';
    }
}
