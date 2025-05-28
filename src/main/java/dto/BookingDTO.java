package dto;

import java.util.Date;

public class BookingDTO {
    private int id;
    private int userId;
    private int flightId;
    private String seatNumber;
    private Date bookingDate;
    private String serviceClass;

    public BookingDTO() {}

    public BookingDTO(int id, int userId, int flightId, String seatNumber, Date bookingDate, String serviceClass) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.serviceClass = serviceClass;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public String getServiceClass() { return serviceClass; }
    public void setServiceClass(String serviceClass) { this.serviceClass = serviceClass; }
} 