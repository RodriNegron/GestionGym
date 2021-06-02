package Collections;

import Classes.Customer;
import Utils.Password;

import java.util.ArrayList;
import java.util.List;

public class Customer_list {

    private List<Customer> customers_list;


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
            if (customer.getEmail().compareTo(c.getEmail())==0) {
                createdUser=true;
                break;
            }
        }
        if (!createdUser) {
            customers_list.add(customer);
        } else {
            System.out.println("This user already exists");
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

   /* public void deleteUser(String email, String password){
        Customer destroyUser = findCustomer(email,password);
        if (destroyUser!=null){
            customers_list.remove(destroyUser);
        }else{
            System.out.println("User not found");
        }
    }*/

    public void getUsersByPlan(int trainingPlanId){
        for (Customer c : customers_list) {
            if (c.getTraining_Plan() == trainingPlanId) {
                System.out.println(c.toString());
                break;
            }
        }
    }

}
