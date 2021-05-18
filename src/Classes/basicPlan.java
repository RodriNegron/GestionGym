package Classes;

import Classes.Abstract.training_plan;

import java.time.LocalDate;

public class basicPlan extends training_plan {


    public basicPlan(int id, LocalDate startDate, String typePlan, double price){
       super(id, startDate, typePlan, price);
    }


}
