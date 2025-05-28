package dto;

import java.util.Date;

public class FlightDTO {
    private int id;
    private int airplaneId;
    private int originId;
    private int destinationId;
    private Date departureTime;
    private Date arrivalTime;

    public FlightDTO() {}

    public FlightDTO(int id, int airplaneId, int originId, int destinationId, Date departureTime, Date arrivalTime) {
        this.id = id;
        this.airplaneId = airplaneId;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAirplaneId() { return airplaneId; }
    public void setAirplaneId(int airplaneId) { this.airplaneId = airplaneId; }
    public int getOriginId() { return originId; }
    public void setOriginId(int originId) { this.originId = originId; }
    public int getDestinationId() { return destinationId; }
    public void setDestinationId(int destinationId) { this.destinationId = destinationId; }
    public Date getDepartureTime() { return departureTime; }
    public void setDepartureTime(Date departureTime) { this.departureTime = departureTime; }
    public Date getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Date arrivalTime) { this.arrivalTime = arrivalTime; }
} 