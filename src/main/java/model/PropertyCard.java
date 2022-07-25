package model;

public class PropertyCard {
    private String address;
    private String checkInMonth;
    private String checkOutMonth;
    private int checkInDay;
    private int checkOutDay;

    public String getDestination() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCheckInMonth() {
        return checkInMonth;
    }
    public void setCheckInMonth(String month) {
        this.checkInMonth = month;
    }
    public int getCheckInDay() {
        return checkInDay;
    }
    public void setCheckInDay(int day) {
        this.checkInDay = day;
    }
    public String getCheckOutMonth() {
        return checkOutMonth;
    }
    public void setCheckOutMonth(String month) {
        this.checkOutMonth = month;
    }
    public int getCheckOutDay() {
        return checkOutDay;
    }
    public void setCheckOutDay(int day) {
        this.checkOutDay = day;
    }
}
