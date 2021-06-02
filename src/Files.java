
import Classes.Customer;
import Collections.Customer_list;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class Files {
    private static final String Customer_file = "customers.json";
    private static Gson gson ;

    private Files(){
    }

    private static Gson getGson() {
        return gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static Customer_list readCustomerFile () {
        Customer_list customers = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new BufferedReader(new FileReader(Customer_file)));
            customers = getGson().fromJson(reader, (new TypeToken<Customer_list>() {
            }.getType()));
        } catch (IOException e) {
            customers = new Customer_list();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public static void writeCustomersFile( Customer_list customers){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(Customer_file));
            getGson().toJson(customers, customers.getClass(), writer);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                try{
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}