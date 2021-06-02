package Classes;

import Classes.Abstract.Person;

public class Admin extends Person {


    public Admin() {
    }

    public Admin(String dni, String firstName, String lastName, String email, String password) {
        super(dni, firstName, lastName, email, password);
    }
}
