package dto;

import java.util.Date;

public class BookingWithCustomerDTO {
    private int id;
    private int userId;
    private String customerName;
    private int flightId;
    private String seatNumber;
    private Date bookingDate;
    private String serviceClass;

    public BookingWithCustomerDTO(int id, int userId, String customerName, int flightId, String seatNumber, Date bookingDate, String serviceClass) {
        this.id = id;
        this.userId = userId;
        this.customerName = customerName;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.serviceClass = serviceClass;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getCustomerName() { return customerName; }
    public int getFlightId() { return flightId; }
    public String getSeatNumber() { return seatNumber; }
    public Date getBookingDate() { return bookingDate; }
    public String getServiceClass() { return serviceClass; }
} 