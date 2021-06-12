package Classes;

import Classes.Abstract.Training_plan;

public class basicPlan extends Training_plan {

    public basicPlan(int id , double price){
        super(id, TrainingPlans.BASIC_PLAN, price);
    }

}
