package Classes.Abstract;

import Classes.Instructor;
import Collections.Instructor_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Activity{

    private static int count  = 0;
    private  int idActivity = 0;  //autoincremental
    private String name ;
    Map<String, Integer> availableShifts;
    private Instructor_list instructors; // < 2

    //region CONSTRUCTORS
    public Activity (){
        incrementalId ();
        this.instructors = new Instructor_list();
        this.availableShifts = new HashMap<>();
    }

    public Activity(String name) {
        incrementalId ();
        this.name = name;
        this.instructors = new Instructor_list();
        this.availableShifts = new HashMap<>();
    }
    //endregion

    //region GETTERS  SETTERS
    public int getIdActivity() { return idActivity; }

    public void setIdActivity(int id) { this.idActivity = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Instructor_list getInstructors() {
        return instructors;
    }

    public Map<String, Integer> getAvailableShifts() {return availableShifts;}

    //endregion

    public void hardcodeAvailableShifts()
    {
        availableShifts.put("8-9:30", 10);
        availableShifts.put("10-11:30", 10);
        availableShifts.put("12-13:30", 10);
        availableShifts.put("14-15:30", 10);
        availableShifts.put("16-17:30", 10);
        availableShifts.put("18-19:30", 10);
    }

    public void consultShifts()
    {
        availableShifts.forEach(
                ( hour , slot)->
                {
                    System.out.println("hour: " + hour);
                    System.out.println("slot: " + slot);
                }
        );
    }

    public void modifySlot(String hour)
    {
        availableShifts.forEach(
                ( hours , slot)->
                {
                    if(hours.equals(hour))
                    {
                        Integer aux = slot--;
                        availableShifts.put(hour, aux);
                    }
                }
        );
    }

    private void incrementalId () { this.idActivity = count++; }

}

