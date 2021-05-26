package Collections;

import Classes.Instructor;

import java.util.ArrayList;
import java.util.List;

public class Instructor_list {

    private List<Instructor> instructors = new ArrayList<>();

    public  Instructor_list (){}

    public Instructor_list(List<Instructor> instructors) { this.instructors = instructors; }

    public void agregar (Instructor instructor){
        instructors.add(instructor);
    }
    public void mostrarLista (){

        for (Instructor inst : instructors) {
            System.out.println(inst.getName());
        }
    }

    //region GETTERS AND SETTERS
    public List<Instructor> getInstructors() { return instructors; }

    public void setInstructors(List<Instructor> instructors) { this.instructors = instructors; }
    //endregion
}
