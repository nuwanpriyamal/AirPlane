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

    public boolean addFlight(FlightDTO flight) {
        try {
            return flightDAO.addFlight(flight);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAirplaneAvailable(int airplaneId, java.util.Date dep, java.util.Date arr) {
        try {
            return flightDAO.isAirplaneAvailable(airplaneId, dep, arr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFlight(int id) {
        try {
            return flightDAO.deleteFlight(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFlight(FlightDTO flight) {
        try {
            return flightDAO.updateFlight(flight);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 