import Classes.Customer;
import Collections.Customer_list;
import Collections.Shifts_map;

import java.util.Scanner;

public final class Gym {
    private String name;
    private String location;
    private String cuit;
    private Shifts_map shifts_map;
    private Customer_list customers_list;


    public Gym() {
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
    }

    public Gym(String name, String location, String cuit) {
        this.name = name;
        this.location = location;
        this.cuit = cuit;
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
    }

    //region setter & getters

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getCuit() { return cuit; }

    public void setCuit(String cuit) { this.cuit = cuit; }

    //endregion

    public Customer register(Scanner scann){

        String dni, firstname , lastname, email, password;

        System.out.println("DNI: ");
        dni = scann.nextLine();
        System.out.println("FirstName: ");
        firstname = scann.nextLine();
        System.out.println("LastName: ");
        lastname = scann.nextLine();
        System.out.println("Email: ");
        email = scann.nextLine();
        System.out.println("Password: ");
        password = scann.nextLine();

        Customer cust = new Customer(dni, firstname, lastname, email, password);

        return cust;

    }

    public void harcodeShifts()
    {
        shifts_map.hardcodeShifts();
    }

    public void consultShifts()
    {
        shifts_map.checkWeeklyShifts();
    }

    public void addToCustomerList(Customer customer)
    {
        customers_list.customerRegister(customer);
    }

    public void consultClients()
    {
        customers_list.listAllCostumers();
    }

    public void hardcodeUsers(){
        Customer admin = new Customer("000", "admin", "admin", "admin@outlook.com.ar", "admin");

        addToCustomerList(admin);
    }

}
