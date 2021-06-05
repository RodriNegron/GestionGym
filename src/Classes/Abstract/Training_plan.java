package Classes.Abstract;

import java.time.LocalDate;

public abstract class Training_plan {
    protected int id = 0; //1 for basic plan - 2 for premium plan
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
        return  id + " " + "Training_plan->" +
                "name='" + name + '\'' +
                ", price=" + price ;
    }
}
