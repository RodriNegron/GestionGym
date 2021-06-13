package Collections;

import Classes.Instructor;
import Classes.Shift;

import java.util.ArrayList;
import java.util.List;

public class Shift_list {

    List<Shift> shift_list;

    public Shift_list() {
        this.shift_list = new ArrayList<>();
    }

    public List<Shift> getShift_list() {
        return shift_list;
    }

    public void setShift_list(List<Shift> shift_list) {
        this.shift_list = shift_list;
    }

    public void delete(Shift shift) {

        if (shift!=null){
            shift_list.remove(shift);
        }
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
