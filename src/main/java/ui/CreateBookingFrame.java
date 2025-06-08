package ui;

import dto.UserDTO;
import dto.FlightDTO;
import service.UserService;
import service.FlightService;
import service.BookingService;
import dao.DBConnection;
import dto.BookingDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateBookingFrame extends JFrame {
    public CreateBookingFrame(UserDTO admin) {
        super("Create Booking (Admin)");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        UserService userService = new UserService();
        List<UserDTO> users = userService.getAllUsers();
        JComboBox<UserDTO> userCombo = new JComboBox<>();
        for (UserDTO u : users) userCombo.addItem(u);

        FlightService flightService = new FlightService();
        List<FlightDTO> flights = flightService.getAllFlights();
        JComboBox<FlightDTO> flightCombo = new JComboBox<>();
        for (FlightDTO f : flights) flightCombo.addItem(f);

        JPanel seatPanel = new JPanel();
        JLabel selectedSeatLabel = new JLabel("Selected Seat: ");
        JComboBox<String> classCombo = new JComboBox<>(new String[]{"FIRST", "BUSINESS", "ECONOMY"});
        final String[] selectedSeat = {null};

        Runnable updateSeats = () -> {
            seatPanel.removeAll();
            FlightDTO selectedFlight = (FlightDTO) flightCombo.getSelectedItem();
            if (selectedFlight == null) return;
            FlightDTO flight = flightService.getFlightById(selectedFlight.getId());
            int rows = 30;
            String seatLetters = "ABCDEF";
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT rowCount, seat_letters FROM flights WHERE id = ?");
                ps.setInt(1, flight.getId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    rows = rs.getInt("rowCount");
                    seatLetters = rs.getString("seat_letters");
                }
            } catch (Exception ex) {}
            Set<String> bookedSeats = new HashSet<>();
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT seat_number FROM bookings WHERE flight_id = ?");
                ps.setInt(1, flight.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    bookedSeats.add(rs.getString("seat_number"));
                }
            } catch (Exception ex) {}
            seatPanel.setLayout(new GridLayout(rows, seatLetters.length()));
            ButtonGroup seatGroup = new ButtonGroup();
            for (int r = 1; r <= rows; r++) {
                for (char c : seatLetters.toCharArray()) {
                    String seat = r + String.valueOf(c);
                    JToggleButton btn = new JToggleButton(seat);
                    if (bookedSeats.contains(seat)) {
                        btn.setEnabled(false);
                    }
                    btn.addActionListener(e -> {
                        selectedSeat[0] = seat;
                        selectedSeatLabel.setText("Selected Seat: " + seat);
                    });
                    seatGroup.add(btn);
                    seatPanel.add(btn);
                }
            }
            seatPanel.revalidate();
            seatPanel.repaint();
            selectedSeat[0] = null;
            selectedSeatLabel.setText("Selected Seat: ");
        };
        updateSeats.run();
        flightCombo.addActionListener(e -> updateSeats.run());

        JScrollPane seatScroll = new JScrollPane(seatPanel);
        seatScroll.setPreferredSize(new Dimension(500, 350));

        add(new JLabel("Select User:")); add(userCombo);
        add(new JLabel("Select Flight:")); add(flightCombo);
        add(new JLabel("Seat Selection:")); add(seatScroll);
        add(selectedSeatLabel); add(new JLabel());
        add(new JLabel("Service Class:")); add(classCombo);

        JButton saveBtn = new JButton("Create Booking");
        JButton cancelBtn = new JButton("Cancel");
        add(saveBtn); add(cancelBtn);

        saveBtn.addActionListener(e -> {
            UserDTO selectedUser = (UserDTO) userCombo.getSelectedItem();
            FlightDTO selectedFlight = (FlightDTO) flightCombo.getSelectedItem();
            String seat = selectedSeat[0];
            String serviceClass = (String) classCombo.getSelectedItem();
            if (selectedUser == null || selectedFlight == null || seat == null || serviceClass == null) {
                JOptionPane.showMessageDialog(this, "Please select user, flight, seat, and class.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BookingDTO booking = new BookingDTO(0, selectedUser.getId(), selectedFlight.getId(), seat, new Date(), serviceClass);
            BookingService bookingService = new BookingService();
            boolean success = bookingService.addBooking(booking);
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking created!");
                dispose();
                new BookingListFrame(admin).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create booking. The seat may have just been booked.", "Error", JOptionPane.ERROR_MESSAGE);
                updateSeats.run();
            }
        });
        cancelBtn.addActionListener(e -> {
            dispose();
            new BookingListFrame(admin).setVisible(true);
        });
    }
} 