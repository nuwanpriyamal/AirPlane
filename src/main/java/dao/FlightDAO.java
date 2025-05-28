package dao;

import dto.FlightDTO;
import dto.FlightDetailsDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDAO {
    public FlightDTO getFlightById(int id) throws SQLException {
        String sql = "SELECT * FROM flights WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToFlightDTO(rs);
            }
        }
        return null;
    }

    public List<FlightDTO> getAllFlights() throws SQLException {
        List<FlightDTO> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                flights.add(mapRowToFlightDTO(rs));
            }
        }
        return flights;
    }

    public boolean addFlight(FlightDTO flight) throws SQLException {
        String sql = "INSERT INTO flights (airplane_id, origin_id, destination_id, departure_time, arrival_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, flight.getAirplaneId());
            ps.setInt(2, flight.getOriginId());
            ps.setInt(3, flight.getDestinationId());
            ps.setTimestamp(4, new Timestamp(flight.getDepartureTime().getTime()));
            ps.setTimestamp(5, new Timestamp(flight.getArrivalTime().getTime()));
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateFlight(FlightDTO flight) throws SQLException {
        String sql = "UPDATE flights SET airplane_id = ?, origin_id = ?, destination_id = ?, departure_time = ?, arrival_time = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, flight.getAirplaneId());
            ps.setInt(2, flight.getOriginId());
            ps.setInt(3, flight.getDestinationId());
            ps.setTimestamp(4, new Timestamp(flight.getDepartureTime().getTime()));
            ps.setTimestamp(5, new Timestamp(flight.getArrivalTime().getTime()));
            ps.setInt(6, flight.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteFlight(int id) throws SQLException {
        String sql = "DELETE FROM flights WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<FlightDetailsDTO> getAllFlightsWithDetails() throws SQLException {
        List<FlightDetailsDTO> flights = new ArrayList<>();
        String sql = "SELECT f.id, a.code AS airplane_code, ao.name AS origin_name, ad.name AS destination_name, f.departure_time, f.arrival_time " +
                "FROM flights f " +
                "JOIN airplanes a ON f.airplane_id = a.id " +
                "JOIN airports ao ON f.origin_id = ao.id " +
                "JOIN airports ad ON f.destination_id = ad.id";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                flights.add(new FlightDetailsDTO(
                    rs.getInt("id"),
                    rs.getString("airplane_code"),
                    rs.getString("origin_name"),
                    rs.getString("destination_name"),
                    rs.getTimestamp("departure_time"),
                    rs.getTimestamp("arrival_time")
                ));
            }
        }
        return flights;
    }

    private FlightDTO mapRowToFlightDTO(ResultSet rs) throws SQLException {
        return new FlightDTO(
            rs.getInt("id"),
            rs.getInt("airplane_id"),
            rs.getInt("origin_id"),
            rs.getInt("destination_id"),
            rs.getTimestamp("departure_time"),
            rs.getTimestamp("arrival_time")
        );
    }
} 