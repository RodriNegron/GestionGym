package Classes.Abstract;

import java.time.LocalDate;

public abstract class Training_plan {
    protected int id = 0;
    private String name;
    protected double price;


    public Training_plan(){
    }

    public Training_plan(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
   }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {return name; }

    public void setName(String name) {this.name = name; }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Training_plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
