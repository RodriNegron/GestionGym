package Collections;

import Classes.Abstract.Activity;
import Interfaces.Controller;
import java.util.ArrayList;
import java.util.List;

public class Activity_list implements Controller {

    private List<Activity> acts;

    //region Constructors, setters & setters

    public Activity_list() { this.acts = new ArrayList<>(); }

    public List<Activity> getActivity_list() { return acts; }

    public void setActivity_list(List<Activity> activity_list) {
        this.acts = activity_list;
    }

    //endregion

    @Override
    public void add(Object name) {
        boolean createdActivity = false;
        name = (Activity) name;
        for (Activity c : acts) {
            if (((Activity) name).equals(c)) {
                createdActivity=true;

            }
        }
        if (!createdActivity) {
            acts.add((Activity) name);
        } else {
            System.out.println("Esta actividad ya existe");
        }
    }

    @Override
    public Object findById(int id) {
        Activity activity = null;
        if (acts.size() > 0){
            for (Activity a : acts) {
                if (a.getIdActivity()==id) {
                    activity = a;
                    break;
                }
            }
        }
        return activity;
    }

    @Override
    public void delete(int id) {
        Activity activityDelete = (Activity) findById(id);
        if (activityDelete!=null){
            acts.remove(activityDelete);
        }else{
            System.out.println("Activity not found");
        }
    }

    @Override
    public void consultList() {
        for (Activity a : acts) {
            System.out.println(a.toString());
        }
    }

    public void deleteActivitie(Activity activity){

        for (int i = 0; i < acts.size(); i++) {

            if (acts.get(i).getName().compareTo(activity.getName())==0){
                acts.remove(acts.get(i));
            }
        }
    }

    public void consultAvailableShifts(){
        for (int a = 0; a < acts.size(); a++) {
            if (acts.get(a).getName().equals("Crossfit")) {
                System.out.println("Crossfit");
                acts.get(a).consultShifts();
            } else if (acts.get(a).getName().equals("Funcional")) {
                System.out.println("Funcional");
                acts.get(a).consultShifts();
            } else if (acts.get(a).getName().equals("Aerobic")) {
                System.out.println("Aerobic");
                acts.get(a).consultShifts();
            }
        }
    }

}