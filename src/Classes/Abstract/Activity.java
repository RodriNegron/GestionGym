package Classes.Abstract;

import Classes.Instructor;

import java.util.ArrayList;
import java.util.List;

public abstract class Activity{

    //public int slots = 10; //TODO conversar con los chicos, no es necesario por qe se setea al crear el mapa
    private static int count  = 0;
    private  int idActivity = 0;  //autoincremental
    private String name ;
    private List<Instructor> instructors;

    //region CONSTRUCTORS
    public Activity (){ incrementalId ();}

    public Activity(String name, List<Instructor> instructors) {
        incrementalId ();
        this.name = name;
        this.instructors = new ArrayList<>();
    }
    //endregion

    //region GETTERS  SETTERS
   public int getIdActivity() { return idActivity; }

    public void setIdActivity(int id) { this.idActivity = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Instructor> getInstructors() { return instructors; }

    public void setInstructors(List<Instructor> instructors) { this.instructors = instructors; }
    //endregion

    private void incrementalId () { this.idActivity = count++; }

}

