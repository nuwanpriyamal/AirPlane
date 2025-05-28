package model;

import java.util.Date;

public class Booking {
    private int id;
    private User user;
    private Flight flight;
    private String seatNumber;
    private Date bookingDate;
    private Flight.ServiceClass serviceClass;

    public Booking() {}

    public Booking(int id, User user, Flight flight, String seatNumber, Date bookingDate, Flight.ServiceClass serviceClass) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.serviceClass = serviceClass;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public Flight.ServiceClass getServiceClass() { return serviceClass; }
    public void setServiceClass(Flight.ServiceClass serviceClass) { this.serviceClass = serviceClass; }
} 