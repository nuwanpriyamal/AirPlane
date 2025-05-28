package service;

import dao.AirplaneDAO;
import dto.AirplaneDTO;
import java.util.List;

public class AirplaneService {
    private AirplaneDAO airplaneDAO = new AirplaneDAO();

    public List<AirplaneDTO> getAllAirplanes() {
        try {
            return airplaneDAO.getAllAirplanes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AirplaneDTO getAirplaneById(int id) {
        try {
            return airplaneDAO.getAirplaneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 