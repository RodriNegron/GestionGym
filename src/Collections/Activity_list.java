package Collections;

import Classes.Abstract.Activity;
import Classes.Customer;
import Interfaces.Controller;

import java.util.ArrayList;
import java.util.List;

public class Activity_list implements Controller {

    private List<Activity> activity_list;

    public Activity_list() { this.activity_list = new ArrayList<>(); }



    @Override
    public void add(Object name) {
        boolean createdActivity = false;
        name = (Activity) name;
        for (Activity c : activity_list) {
            if (((Activity) name).getName().compareTo(c.getName())==0) {
                createdActivity=true;
                break;
            }
        }
        if (!createdActivity) {
            activity_list.add((Activity) name);
        } else {
            System.out.println("This activity already exists");
        }
    }

    @Override
    public Object findById(int id) {
        Activity activity = null;
        if (activity_list.size() > 0){
            for (Activity a : activity_list) {
                if (a.getIdActivity()==id) {
                    activity = a;
                    break;
                }
            }
        }
        return activity;
    }

    @Override
    public void delete(int id) {      ////////// SE BORRA FISICAMENTE, YA QUE NO SE PUEDE LOGICAMENTE
        Activity activityDelete = (Activity) findById(id);
        if (activityDelete!=null){
            activity_list.remove(activityDelete);
        }else{
            System.out.println("Activity not found");
        }
    }

    @Override
    public void consultList() {
        for (Activity a : activity_list) {
            System.out.println(a.toString() +
                    "\n **** end ****");
        }
    }

}