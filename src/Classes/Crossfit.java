package Classes;

import Classes.Abstract.Activity;

public class Crossfit extends Activity {

    //region CONSTRUCTORS
    public Crossfit() { }

    public Crossfit(String name, List<Instructor> instructors) {
        super(name, instructors);
    }
    //endregion

}
