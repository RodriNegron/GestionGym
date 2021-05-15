import java.time.LocalDate;

public abstract class training_plan {


    protected int id;
    protected LocalDate startDate;
    protected String typePlan;
    protected double price;


    public training_plan(){

    }


    public training_plan(int id, LocalDate startDate, String typePlan, double price) {
        this.id = id;
        this.startDate = LocalDate.now();
        this.typePlan = typePlan;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getTypePlan() {
        return typePlan;
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

    public void setTypePlan(String typePlan) {
        this.typePlan = typePlan;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plan {" +
                "id: " + id +
                ", Fecha de Inicio: " + startDate +
                ", Tipo de Plan: " + typePlan + '\'' +
                ", Precio: " + price +
                '}';
    }
}
