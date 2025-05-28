package service;

import dao.AirportDAO;
import dto.AirportDTO;
import java.util.List;

public class AirportService {
    private AirportDAO airportDAO = new AirportDAO();

    public List<AirportDTO> getAllAirports() {
        try {
            return airportDAO.getAllAirports();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AirportDTO getAirportById(int id) {
        try {
            return airportDAO.getAirportById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 