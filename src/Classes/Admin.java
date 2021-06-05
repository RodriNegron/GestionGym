package Classes;


import Classes.Abstract.Person;
import org.jetbrains.annotations.NotNull;


public class Admin{
    private String user;
    private String password;


    //region constructor
    public Admin() {
    }

    public Admin(String user, String password) {
        this.user = user;
        this.password = password;

    }
    //endregion

    //get and set
    public String getUser() {return user;}

    public void setUser() { this.user = user;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public String toString() {
        return "Admin{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
