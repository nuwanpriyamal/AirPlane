package service;

import dao.FlightDAO;
import dto.FlightDTO;
import dto.FlightDetailsDTO;
import java.util.List;

public class FlightService {
    private FlightDAO flightDAO = new FlightDAO();

    public List<FlightDTO> getAllFlights() {
        try {
            return flightDAO.getAllFlights();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FlightDTO getFlightById(int id) {
        try {
            return flightDAO.getFlightById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FlightDetailsDTO> getAllFlightsWithDetails() {
        try {
            return flightDAO.getAllFlightsWithDetails();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 