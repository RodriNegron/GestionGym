import Classes.Abstract.Activity;
import Classes.Abstract.Training_plan;
import Classes.*;
import Collections.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class Gym {
    private String name;
    private String location;
    private String cuit;
    private Shifts_map shifts_map;
    private Customer_list customers_list;
    private TrainingPlan_list training_plan_list;
    private Activity_list activities_list;
    private Instructor_list instructor_list;


    public Gym() {
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
        this.training_plan_list = new TrainingPlan_list();
        this.activities_list = new Activity_list();
        this.instructor_list = new Instructor_list();
    }

    public Gym(String name, String location, String cuit) {
        this.name = name;
        this.location = location;
        this.cuit = cuit;
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
        this.training_plan_list = new TrainingPlan_list();
        this.activities_list = new Activity_list();
        this.instructor_list = new Instructor_list();
    }

    //region setter & getters

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getCuit() { return cuit; }

    public void setCuit(String cuit) { this.cuit = cuit; }

    public Activity_list getActivities_list() {
        return activities_list;
    }

    public void setActivities_list(Activity_list activities_list) {
        this.activities_list = activities_list;
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

    public String consultShiftsOnClient(Customer cust)
    {
        return cust.getShifts().listAllSfhits();
    }

    public void reserveShift(Customer cust, String day , String activity, String hour)
    {
        shifts_map.reserveShift(cust, day, activity, hour);
    }


    public void addToCustomerList(Customer customer)
    {
        customers_list.add(customer);
    }

    public void consultClients()
    {
        customers_list.consultList();
    }

    public void hardcodeUsers(){
        Customer admin = new Customer("000", "admin", "admin", "admin@admin", "admin");
        Customer user = new Customer("111", "user", "user", "user@user", "user");

        addToCustomerList(admin);
        addToCustomerList(user);
    }

    public void hardcodeTrainingPlans(){

        Training_plan basicPlan = new basicPlan(1, 2500 );
        Training_plan premiumPan = new basicPlan(2, 3000 );

        addToTrainingPlanList(basicPlan);
        addToTrainingPlanList(premiumPan);
    }

    public void harcodeShifts()
    {
        harcodeActivityList();
        shifts_map.hardcodeShifts(activities_list);
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

    public void checkAvailableShifts(){shifts_map.consultAvailableShifts();}

    public void harcodeActivityList()
    {
        Activity crossfit = new Crossfit("Crossfit");
        Activity funcional = new Funcional("Funcional");
        Activity aerobic = new Crossfit("Aerobic");

        crossfit.getInstructors().add(instructor_list.getInstructors().get(0));
        crossfit.getInstructors().add(instructor_list.getInstructors().get(1));
        funcional.getInstructors().add(instructor_list.getInstructors().get(2));
        funcional.getInstructors().add(instructor_list.getInstructors().get(3));
        aerobic.getInstructors().add(instructor_list.getInstructors().get(4));
        aerobic.getInstructors().add(instructor_list.getInstructors().get(5));


        activities_list.add(crossfit);
        activities_list.add(funcional);
        activities_list.add(aerobic);
    }

    public void hardcodeInstructor (){
        Instructor instructor1 = new Instructor("Esteban", "38932329", "Ortenzi", "esteban@asd.com", "12345");
        Instructor instructor2 = new Instructor("Felipe", "37895114", "Sarten", "felipe@asd.com", "12345");
        Instructor instructor3 = new Instructor("Marcos", "31587786", "Piero", "marcos@asd.com", "12345");
        Instructor instructor4 = new Instructor("Juan", "23961588", "Juarez", "juan@asd.com", "12345");
        Instructor instructor5 = new Instructor("Franco", "33258968", "Boni", "franco@asd.com", "12345");
        Instructor instructor6 = new Instructor("Gonzalo", "37432329", "Yuyo", "gonzalo@asd.com", "12345");

        instructor_list.add(instructor1);
        instructor_list.add(instructor2);
        instructor_list.add(instructor3);
        instructor_list.add(instructor4);
        instructor_list.add(instructor5);
        instructor_list.add(instructor6);
    }

    public String expired (Customer cust){
        String finalDate;
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/u"));
        StringBuilder builder = new StringBuilder();

        if(cust.getPlanFinalDate() != null){
            finalDate = cust.getPlanFinalDate().format(DateTimeFormatter.ofPattern("d/M/u"));

                if (finalDate.compareTo(date)!=0){
                    builder.append(" la fecha de caducidad de su plan es el: " + finalDate);
                }else{
                    builder.append(" se le ha terminado su plan");
                    cust.setTraining_Plan(0);
                }
        }else{
            builder.append(" usted no se encuentra asignado a ningun plan.");
        }
        return builder.toString();
    }

    public void addActivityToList (Activity activity){
        activity.hardcodeAvailableShifts();
        activities_list.add(activity);
        shifts_map.addActivity(activity);
        activities_list.consultList();
    }
}

