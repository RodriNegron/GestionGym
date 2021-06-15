package Classes;

import Classes.Abstract.Activity;

public class Shift {
    private String shift_date;
    private String shift_hour;
    private String activity;

    //region constructors
    public Shift() {
    }

    public Shift(String shift_date, String shift_hour, String activity) {
        this.shift_date = shift_date;
        this.shift_hour = shift_hour;
        this.activity = activity;
    }
    //endregion

    //region gett&sett
    public String getShift_date() {
        return shift_date;
    }

    public void setShift_date(String shift_date) {
        this.shift_date = shift_date;
    }

    public String getShift_hour() {
        return shift_hour;
    }

    public void setShift_hour(String shift_hour) {
        this.shift_hour = shift_hour;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
    //endregion


    @Override
    public int hashCode() {
        int result = shift_date.hashCode();
        result += 31* shift_hour.hashCode();
        result += 31* activity.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Shift)) return false;

        Shift shift = (Shift) obj;

        boolean result = this.shift_date == shift.shift_date &&
                         this.shift_hour == shift.shift_hour &&
                         this.activity == shift.activity;

        return result;
    }

    @Override
    public String toString() {
        return  "Fecha='" + shift_date + '\'' +
                ", horario='" + shift_hour + '\'' +
                ", actividad=" + activity +
                ' ';
    }

}
