package Classes.Abstract;

import Classes.Instructor;
import Collections.Instructor_list;

import java.util.ArrayList;
import java.util.List;

public abstract class Activity{

    private static int count  = 0;
    private  int idActivity = 0;  //autoincremental
    private String name ;

    private Instructor_list instructors; // < 2

    //region CONSTRUCTORS
    public Activity (){
        incrementalId ();
        this.instructors = new Instructor_list();
    }

    public Activity(String name) {
        incrementalId ();
        this.name = name;
        this.instructors = new Instructor_list();
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

    //endregion

    private void incrementalId () { this.idActivity = count++; }

}

