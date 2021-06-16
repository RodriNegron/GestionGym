package Classes;

import Classes.Abstract.Person;

public class Instructor extends Person {

    public Instructor() {
    }

    public Instructor(String dni,String firstName,  String lastName, String email) {
        super(dni, firstName, lastName, email);
    }

}