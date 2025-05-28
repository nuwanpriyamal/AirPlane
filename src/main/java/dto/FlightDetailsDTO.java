package dto;

import java.util.Date;

public class FlightDetailsDTO {
    private int id;
    private String airplaneCode;
    private String originName;
    private String destinationName;
    private Date departureTime;
    private Date arrivalTime;

    public FlightDetailsDTO(int id, String airplaneCode, String originName, String destinationName, Date departureTime, Date arrivalTime) {
        this.id = id;
        this.airplaneCode = airplaneCode;
        this.originName = originName;
        this.destinationName = destinationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getId() { return id; }
    public String getAirplaneCode() { return airplaneCode; }
    public String getOriginName() { return originName; }
    public String getDestinationName() { return destinationName; }
    public Date getDepartureTime() { return departureTime; }
    public Date getArrivalTime() { return arrivalTime; }
} 