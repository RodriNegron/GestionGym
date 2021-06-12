package Collections;

import Classes.Abstract.Activity;
import Interfaces.Controller;

import java.util.ArrayList;
import java.util.List;

public class Activity_list implements Controller {

    private List<Activity> acts;

    public Activity_list() { this.acts = new ArrayList<>(); }

    public List<Activity> getActs() {
        return acts;
    }

    public void setActs(List<Activity> acts) {
        this.acts = acts;
    }

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
    public void delete(int id) {      ////////// SE BORRA FISICAMENTE, YA QUE NO SE PUEDE LOGICAMENTE
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
            System.out.println(a.toString() +
                    "\n **** end ****");
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

    public void reduceSlotActivity(int idActivty , String hour)
    {
        acts.get(idActivty).modifySlot(hour);
    }

    public Activity foundActivity(String activity)
    {
       Activity aux = null;

        for (int i = 0; i < acts.size(); i++) {
            if (acts.get(i).getName().equals(activity))
            {
                System.out.println("asd");
                aux = acts.get(i);
            }
        }

       return aux;
    }

}