package Collections;

import Classes.Abstract.Activity;
import Classes.Instructor;
import Interfaces.Controller;

import java.util.ArrayList;
import java.util.List;

public class Instructor_list implements Controller {

    private List<Instructor> instructors_list;


    public  Instructor_list (){ instructors_list =  new ArrayList<>(); }

    public List<Instructor> getInstructors() { return instructors_list; }



    @Override
    public void add(Object name) {
        boolean createdInstructor = false;
        name = (Instructor) name;
        for (Instructor i : instructors_list) {
            if (((Instructor) name).equals(i)) {
                createdInstructor=true;
                break;
            }
        }
        if (!createdInstructor) {
            instructors_list.add((Instructor) name);
        } else {
            System.out.println("This instructor already exists");
        }
    }

    @Override
    public Object findById(int id) {
        Instructor instructor = null;
        if (instructors_list.size() > 0){
            for (Instructor i : instructors_list) {
                if (i.getId() == id) {
                    instructor = i;
                    break;
                }
            }
        }
        return instructor;
    }

    @Override
    public void delete(int id) {
        Instructor instructorDelete = (Instructor) findById(id);
        if (instructorDelete!=null){
            instructors_list.remove(instructorDelete);
        }else{
            System.out.println("Instructor not found");
        }
    }

    @Override
    public void consultList() {
        for (Instructor i : instructors_list) {
            System.out.println(i.toString());
        }
    }



    //we can only add 2 instructors for activity
    public void addInstructor(Instructor instructor){  ////CREO QUE LO DE DOS INSTRUCTORES NO VA EN INSTRUCTOR_LIST;
                                                        /// YA QUE SE AGREGAN A LA COLECCION, NO A LA ACTIVIDAD
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