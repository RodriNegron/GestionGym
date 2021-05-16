public abstract class Activity{

    public int slots = 10;
    private static int count  = 0;
    private  int idActivity = 0;  //autoincremental
    private String name ;
    private List <Instructor> instructors;

    //region CONSTRUCTORS
    public Activity (){ incrementalId ();}

    public Activity(String name, List<Instructor> instructors) {
        incrementalId ();
        this.name = name;
        this.instructors = new ArrayList<>();
    }
    //endregion

    //region GETTERS  SETTERS
   public int getIdActivity() { return id; }

    public void setIdActivity(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Instructor> getInstructors() { return instructors; }

    public void setInstructors(List<Instructor> instructors) { this.instructors = instructors; }
    //endregion

    private void incrementalId () { this.idActivity = count++; }

}

