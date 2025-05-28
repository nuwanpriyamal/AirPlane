package dao;

import dto.AirplaneDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {
    public AirplaneDTO getAirplaneById(int id) throws SQLException {
        String sql = "SELECT * FROM airplanes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToAirplaneDTO(rs);
            }
        }
        return null;
    }

    public List<AirplaneDTO> getAllAirplanes() throws SQLException {
        List<AirplaneDTO> airplanes = new ArrayList<>();
        String sql = "SELECT * FROM airplanes";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                airplanes.add(mapRowToAirplaneDTO(rs));
            }
        }
        return airplanes;
    }

    public boolean addAirplane(AirplaneDTO airplane) throws SQLException {
        String sql = "INSERT INTO airplanes (code, capacity_type) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, airplane.getCode());
            ps.setString(2, airplane.getCapacityType());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateAirplane(AirplaneDTO airplane) throws SQLException {
        String sql = "UPDATE airplanes SET code = ?, capacity_type = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, airplane.getCode());
            ps.setString(2, airplane.getCapacityType());
            ps.setInt(3, airplane.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteAirplane(int id) throws SQLException {
        String sql = "DELETE FROM airplanes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private AirplaneDTO mapRowToAirplaneDTO(ResultSet rs) throws SQLException {
        return new AirplaneDTO(
            rs.getInt("id"),
            rs.getString("code"),
            rs.getString("capacity_type")
        );
    }
} 