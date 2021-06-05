import Classes.Abstract.Activity;
import Classes.Abstract.Training_plan;
import Classes.Crossfit;
import Classes.Customer;
import Classes.Funcional;
import Classes.basicPlan;
import Collections.Activity_list;
import Collections.Customer_list;
import Collections.Shifts_map;
import Collections.TrainingPlan_list;

import java.util.Scanner;

public final class Gym {
    private String name;
    private String location;
    private String cuit;
    private Shifts_map shifts_map;
    private Customer_list customers_list;
    private TrainingPlan_list training_plan_list;
    private Activity_list activities;

    public Gym() {
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
        this.training_plan_list = new TrainingPlan_list();
        this.activities = new Activity_list();

    }

    public Gym(String name, String location, String cuit) {
        this.name = name;
        this.location = location;
        this.cuit = cuit;
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
        this.training_plan_list = new TrainingPlan_list();
        this.activities = new Activity_list();

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

    public String consultShiftsOnClient(Customer cust)
    {
        return cust.getShifts().listAllSfhits();
    }

    public void reserveShift(Customer cust, String day , String activity, String hour)
    {
        activities = shifts_map.reserveShift(cust, day, activity, hour);
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
        Customer user = new Customer("111", "user", "user", "user@user", "user");

        addToCustomerList(admin);
        addToCustomerList(user);
    }

    public void hardcodeTrainingPlans(){

        Training_plan basicPlan = new basicPlan(1, "Basic Plan", 2500 );
        Training_plan premiumPan = new basicPlan(2, "Premium Plan", 3000 );

        addToTrainingPlanList(basicPlan);
        addToTrainingPlanList(premiumPan);
    }

    public void harcodeShifts()
    {
        harcodeActivityList();
        shifts_map.hardcodeShifts(activities);
    }

    public void consultTrainingPlanList()
    {
        training_plan_list.consultTrainingPlan();
    }

    public void consultStatusOfUser(Customer cust)
    {
        cust.consultStatus();
    }

    public void addToTrainingPlanList(Training_plan tp)
    {
        training_plan_list.addTrainingPlan(tp);
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

    public void signUp(Customer cust, int trainingPlan)
    {
        training_plan_list.buyTrainingPlan(cust, trainingPlan);
    }

    public String chooseDay(){return shifts_map.chooseDay();}

    public void checkAvailableShifts(){activities.consultAvailableShifts();}

    public void harcodeActivityList()
    {
        Activity crossfit = new Crossfit("Crossfit");
        Activity funcional = new Funcional("Funcional");
        Activity aerobic = new Crossfit("Aerobic");

        crossfit.hardcodeAvailableShifts();
        funcional.hardcodeAvailableShifts();
        aerobic.hardcodeAvailableShifts();

        activities.add(crossfit);
        activities.add(funcional);
        activities.add(aerobic);
    }




}

