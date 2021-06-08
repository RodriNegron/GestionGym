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
        hardcodeAvailableShifts();
    }

    public Activity(String name) {
        incrementalId ();
        this.name = name;
        this.instructors = new Instructor_list();
        this.availableShifts = new HashMap<>();
        hardcodeAvailableShifts();
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

    public String consultShifts() {
        StringBuilder builder = new StringBuilder();
        availableShifts.forEach(
                ( hour , slot)->
                {
                    builder.append("[hour: " + hour + " ");
                    builder.append("slot: " + slot + " ]");
                }
        );
        return builder.toString();
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

    public String consultInstructorList (){
        StringBuilder builder = new StringBuilder();
        builder.append(instructors.getInstructors().get(0));
        builder.append(instructors.getInstructors().get(1));

        return builder.toString();
    }

    public String toString() {
        return "Actividad{" +
                " Nombre='" + name + '\'' +
                ", Turnos disponibles=" + consultShifts() +
                ", Instructores asignados=" + consultInstructorList() +
                '}';
    }
}

