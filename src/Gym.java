import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Gym {
    private String name;
    private String location;
    private String cuit;
    private List<Shift> shiftList;
    private List<Customer> customers_list;


    //region setter & getters

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getCuit() { return cuit; }

    public void setCuit(String cuit) { this.cuit = cuit; }

    //endregion

    public Gym() {}

    public static void listAllCostumers() {
        for (Customer c : customers_list) {
            System.out.println(c.toString() +
                    "\n **** end ****");
        }
    }

    public static void customerRegister(Customer customer) {
        boolean createdUser = false;
        for (Customer c : customer) {
            if (customer.getEmail()==c.email) {
                createdUser=true;
            }
        }
        (!createdUser) ? customers_list.add(customer) : System.out.println("This user already exists");
    }

    public static Customer findByEmail(String email) {
        Customer customer = null;
        if (customers_list.size() > 0){
            for (Customer c : customers_list) {
                if (c.getEmail().equal(email)) {   ///override funcion equals
                    customer = c;
                    break;
                }
            }
        }
        return customer;
    }

    public static void deleteUser(String email){
        Customer destroyUser = findByEmail(email);
        if (destroyUser!=null){
            customers_list.remove(destroyUser);
        }else{
            System.out.println("User not found");
        }
    }

    public void getUsersByPlan(int trainingPlanId){
        for (Customer c : customers_list) {
            if (c.getTraining_Plan().compareTo(trainingPlanId)==0) {
                System.out.println(c.toString());
                break;
            }
        }
    }
    




}
