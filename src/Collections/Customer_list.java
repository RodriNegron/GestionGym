package Collections;

import Classes.Customer;
import Interfaces.Controller;
import Utils.Password;

import java.util.ArrayList;
import java.util.List;

public class Customer_list implements Controller {

    private List<Customer> customers_list;


    public Customer_list() {
        this.customers_list = new ArrayList<>();
    }


    @Override
    public void add(Object name) {
        boolean createdUser = false;
        name = (Customer) name;
        for (Customer c : customers_list) {
            if (((Customer) name).getEmail().compareTo(c.getEmail())==0) {
                createdUser=true;
                break;
            }
        }
        if (!createdUser) {
            customers_list.add((Customer) name);
        } else {
            System.out.println("This user already exists");
        }
    }

    @Override
    public Object findById(int id) {
        Customer customer = null;
        if (customers_list.size() > 0){
            for (Customer c : customers_list) {
                if (c.getId()==id) {
                    customer = c;
                    break;
                }
            }
        }
        return customer;
    }

    @Override
    public void delete(int id) {        ////////// NO BORRARLO, SINO SETIAR PLAN = 0
            Customer userDelete = (Customer) findById(id);
            if (userDelete!=null){
                userDelete.setTraining_Plan(0);
            }else{
                System.out.println("User not found");
            }
        }

    @Override
    public void consultList() {
        for (Customer c : customers_list) {
            System.out.println(c.toString());
        }
    }

    public boolean passwordMatch(String passwordProvided, String securePassword, String salt){
        return Password.verifyUserPassword(passwordProvided,securePassword, salt);
    }

    public Customer findCustomer(String email, String password) {
        Customer customer = null;
        if (customers_list.size() > 0){
            for (Customer c : customers_list) {
                if (c.getEmail().equals(email)) {
                    if(passwordMatch(password,c.getPassword(),c.getSalt()))
                        customer = c;
                    break;
                }
            }
        }
        return customer;
    }

    public void getUsersByPlan(int trainingPlanId){
        for (Customer c : customers_list) {
            if (c.getTraining_Plan() == trainingPlanId) {
                System.out.println(c.toString());
                break;
            }
        }
    }


}
