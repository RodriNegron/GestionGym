package Classes.Abstract;

import Classes.Instructor;

import java.util.ArrayList;
import java.util.List;

public abstract class Activity{

    public int slots = 10;
    private static int count  = 0;
    private  int idActivity = 0;  //autoincremental
    private String name;
    private List<Instructor> instructors;

    //region CONSTRUCTORS
    public Activity (){ incrementalId ();}

    public Activity(String name, List<Instructor> instructors) {
        incrementalId ();
        this.name = name;
        this.instructors = instructors;
    }
    //endregion

    //region GETTERS  SETTERS
   public int getIdActivity() { return idActivity; }

    public void setIdActivity(int id) { this.idActivity = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //endregion

    private void incrementalId () { this.idActivity = count++; }

}

