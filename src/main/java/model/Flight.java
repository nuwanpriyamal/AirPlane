package model;

import java.util.Date;

public class Flight {
    private int id;
    private Airplane airplane;
    private Airport origin;
    private Airport destination;
    private Date departureTime;
    private Date arrivalTime;
    private ServiceClass serviceClass;

    public enum ServiceClass {
        FIRST, BUSINESS, ECONOMY
    }

    public Flight() {}

    public Flight(int id, Airplane airplane, Airport origin, Airport destination, Date departureTime, Date arrivalTime, ServiceClass serviceClass) {
        this.id = id;
        this.airplane = airplane;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.serviceClass = serviceClass;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Airplane getAirplane() { return airplane; }
    public void setAirplane(Airplane airplane) { this.airplane = airplane; }
    public Airport getOrigin() { return origin; }
    public void setOrigin(Airport origin) { this.origin = origin; }
    public Airport getDestination() { return destination; }
    public void setDestination(Airport destination) { this.destination = destination; }
    public Date getDepartureTime() { return departureTime; }
    public void setDepartureTime(Date departureTime) { this.departureTime = departureTime; }
    public Date getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Date arrivalTime) { this.arrivalTime = arrivalTime; }
    public ServiceClass getServiceClass() { return serviceClass; }
    public void setServiceClass(ServiceClass serviceClass) { this.serviceClass = serviceClass; }
} 