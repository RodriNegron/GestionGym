package Collections;

import Classes.Instructor;

import java.util.ArrayList;
import java.util.List;

public class Instructor_list {

    private List<Instructor> instructors;

    public  Instructor_list (){
        instructors =  new ArrayList<>();
    }

    public List<Instructor> getInstructors() { return instructors; }


    //we can only add 2 instructors for activity
    public void addInstructor(Instructor instructor){

        boolean found = false;

        if(instructors.size() <= 0) instructors.add(instructor);
        else
        {
            for (Instructor ins: instructors) {
                if (ins.equals(instructor))
                {
                    found = true;
                }
            }
            if((found == false ) && (instructors.size() < 2)) instructors.add(instructor);
        }
    }

    public void consultAllInstructors(){
        for (Instructor inst : instructors) {
            System.out.println(inst);
        }
    }
}