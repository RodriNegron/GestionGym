package Collections;

import Classes.Abstract.Activity;
import Classes.Customer;

import java.util.ArrayList;
import java.util.List;

public class Activity_list {

    private List<Activity> activity_list;

    public Activity_list() { this.activity_list = new ArrayList<>(); }


    public void addActivity (Activity activity){
        boolean createdActivity = false;

        for (Activity act : activity_list) {
            if (activity.getName().compareTo(act.getName())==0){
                createdActivity = true;
                break;
            }
        }
        if (!createdActivity){
            activity_list.add(activity);
        }else{
            System.out.println("This activity already exist");
        }
    }

    public Activity findActivity (String name){
        Activity activity = null;

        if (activity_list.size() > 0){
            for (Activity act : activity_list) {
                if(act.getName().compareTo(name) == 0){
                    activity = act;
                }
            }
        }
        return activity;
    }

    public void deleteActivity (String name){
        Activity destroyActivity = findActivity(name);

        if (destroyActivity != null){
            activity_list.remove(destroyActivity);
        }else{
            System.out.println("Activity not found");
        }
    }

    public void listActivities (){
        for (Activity act : activity_list) {
            System.out.println(act.getName());
        }
    }

    

}
