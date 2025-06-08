package ui;

import dto.BookingDTO;
import dto.UserDTO;
import service.BookingService;
import service.FlightService;
import dao.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EditBookingFrame extends JFrame {
    public EditBookingFrame(int bookingId, UserDTO user) {
        super("Edit Booking");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        BookingService bookingService = new BookingService();
        BookingDTO booking = bookingService.getBookingById(bookingId);
        int flightId = booking.getFlightId();
        String currentSeat = booking.getSeatNumber();
        String currentClass = booking.getServiceClass();

        JPanel seatPanel = new JPanel();
        JLabel selectedSeatLabel = new JLabel("Selected Seat: " + currentSeat);
        JScrollPane seatScroll = new JScrollPane(seatPanel);
        seatScroll.setPreferredSize(new Dimension(500, 350));
        add(new JLabel("Seat Selection:"));
        add(seatScroll);
        add(selectedSeatLabel);
        add(new JLabel());
        JComboBox<String> classCombo = new JComboBox<>(new String[]{"FIRST", "BUSINESS", "ECONOMY"});
        classCombo.setSelectedItem(currentClass);
        add(new JLabel("Service Class:"));
        add(classCombo);

        final String[] selectedSeat = {currentSeat};

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT rowCount, seat_letters FROM flights WHERE id = ?");
            ps.setInt(1, flightId);
            ResultSet rs = ps.executeQuery();
            int rows = 30;
            String seatLetters = "ABCDEF";
            if (rs.next()) {
                rows = rs.getInt("rowCount");
                seatLetters = rs.getString("seat_letters");
            }
            Set<String> bookedSeats = new HashSet<>();
            ps = conn.prepareStatement("SELECT seat_number FROM bookings WHERE flight_id = ? AND id != ?");
            ps.setInt(1, flightId);
            ps.setInt(2, bookingId);
            rs = ps.executeQuery();
            while (rs.next()) {
                bookedSeats.add(rs.getString("seat_number"));
            }
            seatPanel.setLayout(new GridLayout(rows, seatLetters.length()));
            ButtonGroup seatGroup = new ButtonGroup();
            for (int r = 1; r <= rows; r++) {
                for (char c : seatLetters.toCharArray()) {
                    String seat = r + String.valueOf(c);
                    JToggleButton btn = new JToggleButton(seat);
                    if (bookedSeats.contains(seat) && !seat.equals(currentSeat)) {
                        btn.setEnabled(false);
                    }
                    if (seat.equals(currentSeat)) {
                        btn.setSelected(true);
                    }
                    btn.addActionListener(e -> {
                        selectedSeat[0] = seat;
                        selectedSeatLabel.setText("Selected Seat: " + seat);
                    });
                    seatGroup.add(btn);
                    seatPanel.add(btn);
                }
            }
        } catch (Exception ex) {}

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        add(saveBtn);
        add(cancelBtn);

        saveBtn.addActionListener(e -> {
            String newSeat = selectedSeat[0];
            String newClass = (String) classCombo.getSelectedItem();
            BookingDTO updated = new BookingDTO(booking.getId(), booking.getUserId(), flightId, newSeat, new Date(), newClass);
            boolean success = bookingService.updateBooking(updated);
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking updated!");
                dispose();
                new BookingListFrame(user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update booking. The seat may have just been booked.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        cancelBtn.addActionListener(e -> {
            dispose();
            new BookingListFrame(user).setVisible(true);
        });
    }
} 