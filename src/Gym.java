import Collections.Customer_list;
import Collections.Shifts_map;

public final class Gym {
    private String name;
    private String location;
    private String cuit;
    private Shifts_map shifts_map;
    private Customer_list customers_list;


    public Gym() {
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
    }

    public Gym(String name, String location, String cuit) {
        this.name = name;
        this.location = location;
        this.cuit = cuit;
        this.shifts_map = new Shifts_map();
        this.customers_list = new Customer_list();
    }

    //region setter & getters

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getCuit() { return cuit; }

    public void setCuit(String cuit) { this.cuit = cuit; }

    //endregion

    public void harcodeShifts()
    {
        shifts_map.hardcodeShifts();
    }

    public void consultShifts()
    {
        shifts_map.checkWeeklyShifts();
    }

}
