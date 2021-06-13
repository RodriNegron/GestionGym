package Collections;

import Classes.Instructor;
import Classes.Shift;
import Interfaces.Controller;

import java.util.ArrayList;
import java.util.List;

public class Shift_list implements Controller {

    List<Shift> shift_list;

    public Shift_list() {
        this.shift_list = new ArrayList<>();
    }

    @Override
    public void add(Object name) {
        boolean createdShift = false;
        name = (Shift) name;
        for (Shift i : shift_list) {
            if (((Shift) name).equals(i)) {
                createdShift=true;
                break;
            }
        }
        if (!createdShift) {
            shift_list.add((Shift) name);
        } else {
            System.out.println("Este turno ya existe !!");
        }
    }

    @Override
    public void consultList() {
        for (Shift i : shift_list) {
            System.out.println(i.toString());
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

    @Override
    public Object findById(int id) {  // NO SE PUEDE USAR PORQUE LOS TURNOS NO TIENEN ID
        return null;
    }

    @Override
    public void delete(int id) {      // NO SE PUEDE USAR PORQUE LOS TURNOS NO TIENEN ID

    }




}
