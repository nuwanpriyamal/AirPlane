package dao;

import dto.BookingDTO;
import dto.BookingWithCustomerDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDAO {
    public BookingDTO getBookingById(int id) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToBookingDTO(rs);
            }
        }
        return null;
    }

    public List<BookingDTO> getAllBookings() throws SQLException {
        List<BookingDTO> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                bookings.add(mapRowToBookingDTO(rs));
            }
        }
        return bookings;
    }

    public boolean addBooking(BookingDTO booking) throws SQLException {
        String sql = "INSERT INTO bookings (user_id, flight_id, seat_number, booking_date, service_class) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getFlightId());
            ps.setString(3, booking.getSeatNumber());
            ps.setTimestamp(4, new Timestamp(booking.getBookingDate().getTime()));
            ps.setString(5, booking.getServiceClass());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateBooking(BookingDTO booking) throws SQLException {
        String sql = "UPDATE bookings SET user_id = ?, flight_id = ?, seat_number = ?, booking_date = ?, service_class = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getFlightId());
            ps.setString(3, booking.getSeatNumber());
            ps.setTimestamp(4, new Timestamp(booking.getBookingDate().getTime()));
            ps.setString(5, booking.getServiceClass());
            ps.setInt(6, booking.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteBooking(int id) throws SQLException {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<BookingWithCustomerDTO> getAllBookingsWithCustomerName() throws SQLException {
        List<BookingWithCustomerDTO> bookings = new ArrayList<>();
        String sql = "SELECT b.id, b.user_id, u.name AS customer_name, b.flight_id, b.seat_number, b.booking_date, b.service_class " +
                "FROM bookings b JOIN users u ON b.user_id = u.id";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                bookings.add(new BookingWithCustomerDTO(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("customer_name"),
                    rs.getInt("flight_id"),
                    rs.getString("seat_number"),
                    rs.getTimestamp("booking_date"),
                    rs.getString("service_class")
                ));
            }
        }
        return bookings;
    }

    private BookingDTO mapRowToBookingDTO(ResultSet rs) throws SQLException {
        return new BookingDTO(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getInt("flight_id"),
            rs.getString("seat_number"),
            rs.getTimestamp("booking_date"),
            rs.getString("service_class")
        );
    }
} 