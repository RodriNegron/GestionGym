import Collections.Customer_list;
import Collections.Shifts_map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;

class toFiles {

    private toFiles(){
    }

    private static Gson getGson() {
        Gson gson;
        return gson = new GsonBuilder()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .create();
    }

    public static Customer_list readFile (String fileName) {
        Customer_list customers = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new BufferedReader(new FileReader(fileName)));
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

    public static Shifts_map readMapFile (String fileName) {
        Shifts_map shifts = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new BufferedReader(new FileReader(fileName)));
            shifts = getGson().fromJson(reader, (new TypeToken<Shifts_map>() {
            }.getType()));
        } catch (IOException e) {
            shifts = new Shifts_map();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return shifts;
    }

    public static void writeFile(Object name, String fileName){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(fileName));
            getGson().toJson(name, name.getClass(), writer);
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
