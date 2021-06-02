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

    public void setCustomers_list(Customer_list customers_list) {
        this.customers_list = customers_list;
    }

    public Customer_list getCustomers_list() {
        return customers_list;
    }
    //endregion

    public Customer register(Scanner scann){

        String dni, firstname , lastname, email, password;

        System.out.println("DNI: ");
        dni = scann.nextLine();
        System.out.println("Nombre: ");
        firstname = scann.nextLine();
        System.out.println("Apellido: ");
        lastname = scann.nextLine();
        System.out.println("Email: ");
        email = scann.nextLine();
        System.out.println("Contraseña: ");
        password = scann.nextLine();

        return new Customer(dni, firstname, lastname, email, password);
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
        Customer admin = new Customer("000", "admin", "admin", "admin@admin", "admin");

        addToCustomerList(admin);
    }

    public Customer checkClient(){
        Scanner scanner = new Scanner(System.in);
        String str,pw;
        Customer customer;
        System.out.println("Escriba su email");
        str = scanner.nextLine();
        scanner.reset();
        System.out.println("Escriba su contraseña");
        pw = scanner.nextLine();

        return customer = customers_list.findCustomer(str,pw);
    }

}
