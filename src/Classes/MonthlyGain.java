package Classes;

import java.util.Calendar;
import java.util.HashMap;

public class MonthlyGain {
    private HashMap<String, Double> gains;

    public HashMap<String, Double> getGains() {
        return gains;
    }

    public void setGains(HashMap<String, Double> gains) {
        this.gains = gains;
    }

    public void addGain(String string, Double value)
    {
        Double val = gains.get(string);
        val = val + value;

        gains.put(string, val);
    }

    public void harcodeMap()
    {
        gains.put("JANUARY", 0.0);
        gains.put("FEBRUARY", 0.0);
        gains.put("MARCH", 0.0);
        gains.put("APRIL", 0.0);
        gains.put("MAY", 0.0);
        gains.put("JUNE", 0.0);
        gains.put("JULY", 0.0);
        gains.put("AUGUST", 0.0);
        gains.put("SEPTEMBER", 0.0);
        gains.put("OCTOBER", 0.0);
        gains.put("NOVEMBER", 0.0);
        gains.put("DECEMBER", 0.0);
    }


}
