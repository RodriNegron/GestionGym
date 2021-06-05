package Collections;

import Classes.Abstract.Training_plan;
import Classes.Customer;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlan_list{
    List<Training_plan> plan_list;

    public TrainingPlan_list()
    {
        plan_list = new ArrayList<>();
    }


    public void addTrainingPlan(Training_plan tp) {
        boolean found = false;
        if(plan_list.size() <= 0) plan_list.add(tp);
        else
        {
            for (int i = 0; i < plan_list.size(); i++) {
                if(plan_list.get(i).getId() == tp.getId()) found = true;
            }
            if (!found) plan_list.add(tp);
        }
    }

    public void deleteTrainingPlan(Training_plan tp) {
        for (int i = 0; i < plan_list.size(); i++) {
            if(plan_list.get(i).getId() == tp.getId()) plan_list.remove(tp);
        }
    }


    public void consultTrainingPlan() {
        for (Training_plan tp : plan_list) {
            System.out.println(tp);
        }
    }

    public Training_plan findTrainingPlanById(int trainingPlan)
    {
        Training_plan aux = null;

        for (Training_plan tp : plan_list) {
            if(tp.getId() == trainingPlan)
            {
                aux = tp;
            }
        }

        return aux;
    }

    public void buyTrainingPlan(Customer customer, int trainingPlan)
    {
        Training_plan tp = findTrainingPlanById(trainingPlan);

        if (tp != null) {
            if (customer.getTraining_Plan() == 0) {
                if (customer.getWallet().getTotal_Amount() >= tp.getPrice()) {
                    customer.setTraining_Plan(tp.getId());
                    customer.setDatesTrainingPlan();
                    System.out.println("sign up complete");
                } else System.out.println("Insufficient balance");
            } else System.out.println("You are already signed");
        }
        else System.out.println("Wrong case!");

    }

}
