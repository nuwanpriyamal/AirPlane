package dao;

import dto.AirportDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO {
    public AirportDTO getAirportById(int id) throws SQLException {
        String sql = "SELECT * FROM airports WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToAirportDTO(rs);
            }
        }
        return null;
    }

    public List<AirportDTO> getAllAirports() throws SQLException {
        List<AirportDTO> airports = new ArrayList<>();
        String sql = "SELECT * FROM airports";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                airports.add(mapRowToAirportDTO(rs));
            }
        }
        return airports;
    }

    public boolean addAirport(AirportDTO airport) throws SQLException {
        String sql = "INSERT INTO airports (code, name, location) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, airport.getCode());
            ps.setString(2, airport.getName());
            ps.setString(3, airport.getLocation());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateAirport(AirportDTO airport) throws SQLException {
        String sql = "UPDATE airports SET code = ?, name = ?, location = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, airport.getCode());
            ps.setString(2, airport.getName());
            ps.setString(3, airport.getLocation());
            ps.setInt(4, airport.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteAirport(int id) throws SQLException {
        String sql = "DELETE FROM airports WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private AirportDTO mapRowToAirportDTO(ResultSet rs) throws SQLException {
        return new AirportDTO(
            rs.getInt("id"),
            rs.getString("code"),
            rs.getString("name"),
            rs.getString("location")
        );
    }
} 