package Classes;

public class Sunday {
    private int aux = 0;
    private String Sunday;
    private String nextSunday;

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    public String getNextSunday() {
        return nextSunday;
    }

    public void setNextSunday(String nextSunday) {
        this.nextSunday = nextSunday;
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    @Override
    public String toString() {
        return "Sunday{" +
                "aux=" + aux +
                ", Sunday='" + Sunday + '\'' +
                ", nextSunday='" + nextSunday + '\'' +
                '}';
    }
}
