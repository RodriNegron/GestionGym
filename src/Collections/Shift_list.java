package Collections;

import Classes.Shift;

import java.util.ArrayList;
import java.util.List;

public class Shift_list {

    List<Shift> shift_list;


    public Shift_list() {
        this.shift_list = new ArrayList<>();
    }


    public void addShiftToClient(Shift shift)
    {
        boolean found = false;
        if(shift_list.size() <= 0) shift_list.add(shift);
        else
        {
            for (int i = 0; i < shift_list.size(); i++) {
                if(shift_list.get(i).equals(shift)) found = true;
            }
            if (!found) shift_list.add(shift);
        }
    }


    public String listAllSfhits() {
        StringBuilder builder = new StringBuilder();
        for (Shift c : shift_list) {
            builder.append(c);
            builder.append("\n");
        }
        if (builder.length() ==  0) builder.append("Empty list");

        return builder.toString();
    }


}
