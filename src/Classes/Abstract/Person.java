package Classes.Abstract;

public abstract class Person {
    private static int count = 0;
    private int id;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //region Constructors, Getters & setters

    public Person() {
        incrementalId();
    }

    public Person(String dni, String firstName, String lastName, String email) {
        incrementalId();
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String dni, String firstName, String lastName, String email, String password) {
        incrementalId();
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //endregion

    private void incrementalId()
    {
        this.id = count++;
    }

    @Override
    public int hashCode() {
        int result = dni.hashCode();
        result += 31* lastName.hashCode();
        result += 31* lastName.hashCode();
        result += 31* email.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if(!(obj instanceof Person)) return false;

        Person aux = (Person) obj;

        boolean result = dni == aux.dni &&
                         firstName == aux.firstName &&
                         lastName == aux.lastName &&
                         email == aux.email;

        return result;
    }

    @Override
    public String toString() {
        return "Persona: " +
                "id=" + this.id +
                ", nombre='" + this.firstName + '\'' +
                ", D.N.I='" + this.dni + '\'' +
                ", apellido='" + this.lastName + '\'' +
                ", email='" + this.email + '\'';
    }
}

