package Collections;

import Classes.Customer;

import java.util.ArrayList;
import java.util.List;

public class Customer_list {

    List<Customer> customers_list;


    public Customer_list() {
        this.customers_list = new ArrayList<>();
    }



    public void listAllCostumers() {
        for (Customer c : customers_list) {
            System.out.println(c.toString() +
                    "\n **** end ****");
        }
    }

    public void customerRegister(Customer customer) {
        boolean createdUser = false;
        for (Customer c : customers_list) {
            if (customer.getEmail()==c.getEmail()) {
                createdUser=true;
            }
        }
        if ((!createdUser)) {
            customers_list.add(customer);
        } else {
            System.out.println("This user already exists");
        }
    }

    public Customer findByEmail(String email) {
        Customer customer = null;
        if (customers_list.size() > 0){
            for (Customer c : customers_list) {
                if (c.getEmail().equals(email)) {   ///override funcion equals
                    customer = c;
                    break;
                }
            }
        }
        return customer;
    }

    public void deleteUser(String email){
        Customer destroyUser = findByEmail(email);
        if (destroyUser!=null){
            customers_list.remove(destroyUser);
        }else{
            System.out.println("User not found");
        }
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
