package Classes;

import Classes.Abstract.Training_plan;

public class premiumPlan extends Training_plan {

    public premiumPlan(int id, double price){
        super(id, TrainingPlans.PREMIUM_PLAN, price);
    }

}
