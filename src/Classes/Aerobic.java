package Classes;

import Classes.Abstract.Activity;

import java.util.List;

public class Aerobic extends Activity {


    //region CONSTRUCTORS
    public Aerobic() { }

    public Aerobic(String name, List<Instructor> instructors) {
        super(name, instructors);
    }
    //endregion

}
