package Classes.Abstract;

import Classes.TrainingPlans;

import java.time.LocalDate;

public abstract class Training_plan {
    protected int id = 0; //1 for basic plan - 2 for premium plan
    private TrainingPlans tp;
    protected double price;

    public Training_plan(){
    }

    public Training_plan(int id, TrainingPlans tp, double price) {
        this.id = id;
        this.tp = tp;
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

    public TrainingPlans getTp() { return tp; }

    public void setTp(TrainingPlans tp) { this.tp = tp; }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  id + " " + "Training_plan->" +
                "name='" + getTp() + '\'' +
                ", price=" + price ;
    }
}
