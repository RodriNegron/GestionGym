import Classes.MonthlyGain;
import Classes.Sunday;
import Collections.Activity_list;
import Collections.Customer_list;
import Collections.Shifts_map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.HashMap;

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
                System.out.println("Error al cerrar el archivo" + e.getMessage());
            }
        }
        return customers;
    }

    public static HashMap<String, Activity_list> readMapFile (String fileName) {
        HashMap<String, Activity_list> shifts = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new BufferedReader(new FileReader(fileName)));
            shifts = getGson().fromJson(reader, (new TypeToken< HashMap<String, Activity_list>>() {
            }.getType()));
        } catch (IOException e) {
            shifts = new  HashMap<String, Activity_list>();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo" + e.getMessage());
            }
        }
        return shifts;
    }

    public static HashMap<String, Double> readMonthlyGains (String fileName) {
        HashMap<String, Double> gains = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new BufferedReader(new FileReader(fileName)));
            gains = getGson().fromJson(reader, (new TypeToken< HashMap<String, Double>>() {
            }.getType()));
        } catch (IOException e) {
            gains = new  HashMap<String, Double>();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo" + e.getMessage());
            }
        }
        return gains;
    }

    public static Sunday readSundayFile (String fileName) {
        Sunday sunday = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new BufferedReader(new FileReader(fileName)));
            sunday = getGson().fromJson(reader, (new TypeToken<Sunday>() {
            }.getType()));
        } catch (IOException e) {
            sunday = new Sunday();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo" + e.getMessage());
            }
        }
        return sunday;
    }

    public static void writeFile(Object name, String fileName){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(fileName));
            getGson().toJson(name, name.getClass(), writer);
        } catch (IOException e){
            System.out.println("Error al escribir el archivo" + e.getMessage());
        }finally {
            if(writer != null){
                try{
                    writer.close();
                }catch (IOException e){
                    System.out.println("Error al cerrar el archivo" + e.getMessage());
                }
            }
        }
    }

}
