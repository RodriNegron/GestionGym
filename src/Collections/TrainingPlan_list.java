package Collections;

import Classes.Abstract.Training_plan;

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

}
