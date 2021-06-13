import Classes.Abstract.Activity;
import Classes.Abstract.Training_plan;
import Classes.*;
import Collections.*;
import Classes.Admin;
import Utils.Password;
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
    private Instructor_list instructor_list;


    //region Constructors, getters & setters

    public Gym() {
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
        this.training_plan_list = new TrainingPlan_list();
        this.instructor_list = new Instructor_list();
    }

    public Gym(String name, String location, String cuit) {
        this.name = name;
        this.location = location;
        this.cuit = cuit;
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
        this.training_plan_list = new TrainingPlan_list();
        this.instructor_list = new Instructor_list();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getCuit() { return cuit; }

    public void setCuit(String cuit) { this.cuit = cuit; }

    public Customer_list getCustomers_list() { return customers_list; }

    public void setCustomers_list(Customer_list customers_list) { this.customers_list = customers_list; }

    public Shifts_map getShifts_map() { return shifts_map; }

    public Instructor_list getInstructor_list() { return instructor_list; }

    public void setShifts_map(Shifts_map shifts_map) { this.shifts_map = shifts_map; }
    //endregion

    public Customer register(Scanner scann, String salt){

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
        password = Password.generateSecurePassword(password,salt);

        return new Customer(dni, firstname, lastname, email, password, salt);
    }

    public void consultShiftsOnClient(Customer cust)
    {
        cust.getShifts().consultList();
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
        Customer admin = new Customer("000", "admin", "admin", "admin@admin", "admin", "salt");
        Customer user = new Customer("111", "user", "user", "user@user", "user", "salt");

        addToCustomerList(admin);
        addToCustomerList(user);
    }

    public void hardcodeTrainingPlans(){

        Training_plan basicPlan = new basicPlan(1, 2500 );
        Training_plan premiumPan = new premiumPlan(2, 3000 );

        addToTrainingPlanList(basicPlan);
        addToTrainingPlanList(premiumPan);
    }

    public void consultTrainingPlanList()
    {
        training_plan_list.consultList();
    }

    public void consultStatusOfUser(Customer cust)
    {
        cust.consultStatus();
    }

    public void addToTrainingPlanList(Training_plan tp)
    {
        training_plan_list.add(tp);
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

    public Admin checkAdmin (Admin administrator){
        Scanner scanner = new Scanner(System.in);
        String adm, pw;
        Admin admin = null;
        System.out.println("Ingresar usuario");
        adm = scanner.nextLine();

        scanner.reset();
        System.out.println("Contraseña administrador");
        pw = scanner.nextLine();

        if (administrator.getEmail().equals(adm)){
            if(administrator.getPassword().equals(pw)){
                admin = administrator;
            }
        }

        return admin;
    }

    public void signUp(Customer cust, int trainingPlan)
    {
        training_plan_list.buyTrainingPlan(cust, trainingPlan);
    }

    public String chooseDay(Sunday persistedSunday){return shifts_map.chooseDay(persistedSunday);}

    public void checkAvailableShifts(){shifts_map.consultAvailableShifts();}

    public void hardcodeInstructor (){
        Instructor instructor1 = new Instructor("Esteban", "38932329", "Ortenzi", "esteban@asd.com");
        Instructor instructor2 = new Instructor("Felipe", "37895114", "Sarten", "felipe@asd.com" );
        Instructor instructor3 = new Instructor("Marcos", "31587786", "Piero", "marcos@asd.com");
        Instructor instructor4 = new Instructor("Juan", "23961588", "Juarez", "juan@asd.com");
        Instructor instructor5 = new Instructor("Franco", "33258968", "Boni", "franco@asd.com");
        Instructor instructor6 = new Instructor("Gonzalo", "37432329", "Yuyo", "gonzalo@asd.com");
        Instructor instructor7 = new Instructor("Carlos", "3244629", "Gomez", "carlitos@asd.com");
        Instructor instructor8 = new Instructor("Jorge", "27436919", "Rodriguez", "jorgito@asd.com");

        instructor_list.add(instructor1);
        instructor_list.add(instructor2);
        instructor_list.add(instructor3);
        instructor_list.add(instructor4);
        instructor_list.add(instructor5);
        instructor_list.add(instructor6);
        instructor_list.add(instructor7);
        instructor_list.add(instructor8);
    }

    public String expired (Customer cust){
        String finalDate;
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/u"));
        StringBuilder builder = new StringBuilder();

        if(cust.getPlanFinalDate() != null){
            finalDate = cust.getPlanFinalDate();

                if (!(finalDate.equals(date))){
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
        shifts_map.addActivity(activity, instructor_list);
    }

    public void resetShiftsInClients()
    {
        for (Customer aux : getCustomers_list().getCustomers_list() ) {
            aux.getShifts().getShift_list().clear();
        }
    }

}

