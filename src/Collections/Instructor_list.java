package Collections;

import Classes.Instructor;

import java.util.ArrayList;
import java.util.List;

public class Instructor_list {

    private List<Instructor> instructors_list;

    public  Instructor_list (){ instructors_list =  new ArrayList<>(); }

    public List<Instructor> getInstructors() { return instructors_list; }

    //we can only add 2 instructors for activity
    public void addInstructor(Instructor instructor){

        boolean found = false;

        if(instructors_list.size() == 0){
            instructors_list.add(instructor);
        }else{
            for (Instructor ins: instructors_list){
                if (ins.equals(instructor)){
                    found = true;
                }
            }
            if((found == false ) && (instructors_list.size() < 2)){
                instructors_list.add(instructor);
            }
        }
    }

    public void consultAllInstructors(){
        for (Instructor inst : instructors_list) {
            System.out.println(inst);
        }
    }

    public Instructor findInstructor (String name, String lastName){
        Instructor instructor = null;

        if (instructors_list.size()  > 0) {
            for (Instructor inst : instructors_list) {
                if ((inst.getFirstName().compareTo(name) ==0) && (inst.getLastName().compareTo(lastName))==0){
                    instructor = inst;
                }
            }
        }
        return instructor;
    }

    public void deleteInstructor (String name, String lastName){
        Instructor destroyInstructor = findInstructor(name, lastName);

        if (destroyInstructor != null){
            instructors_list.remove(destroyInstructor);
        }else{
            System.out.println("Instructor not found");
        }
    }

}