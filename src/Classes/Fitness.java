package Classes;

import Classes.Abstract.Activity;

import java.util.List;

public class Fitness extends Activity {


    //region CONSTRUCTORS
    public Fitness() { }

    public Fitness(String name, List<Instructor> instructors) {

        super(name, instructors);
    }
    //endregion

}
