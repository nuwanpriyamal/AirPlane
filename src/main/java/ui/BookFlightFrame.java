package ui;

import dto.BookingDTO;
import dto.FlightDTO;
import dto.UserDTO;
import service.BookingService;
import service.FlightService;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class BookFlightFrame extends JFrame {
    public BookFlightFrame(UserDTO user) {
        super("Book a Flight");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        FlightService flightService = new FlightService();
        BookingService bookingService = new BookingService();
        List<FlightDTO> flights = flightService.getAllFlights();
        JComboBox<FlightDTO> flightCombo = new JComboBox<>();
        for (FlightDTO f : flights) {
            flightCombo.addItem(f);
        }
        JPanel seatPanel = new JPanel();
        JLabel selectedSeatLabel = new JLabel("Selected Seat: ");
        add(new JLabel("Select Flight:"));
        add(flightCombo);
        add(new JLabel("Seat Selection:"));
        JScrollPane seatScroll = new JScrollPane(seatPanel);
        seatScroll.setPreferredSize(new Dimension(500, 350));
        add(seatScroll);
        add(selectedSeatLabel);
        add(new JLabel());
        JComboBox<String> classCombo = new JComboBox<>(new String[]{"FIRST", "BUSINESS", "ECONOMY"});
        add(new JLabel("Service Class:"));
        add(classCombo);

        final String[] selectedSeat = {null};

        Runnable updateSeats = () -> {
            seatPanel.removeAll();
            FlightDTO selectedFlight = (FlightDTO) flightCombo.getSelectedItem();
            if (selectedFlight == null) return;
            FlightDTO flight = flightService.getFlightById(selectedFlight.getId());
            int rows = 30;
            String seatLetters = "ABCDEF";
            try {
                java.sql.Connection conn = dao.DBConnection.getConnection();
                java.sql.PreparedStatement ps = conn.prepareStatement("SELECT rowCount, seat_letters FROM flights WHERE id = ?");
                ps.setInt(1, flight.getId());
                java.sql.ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    rows = rs.getInt("rowCount");
                    seatLetters = rs.getString("seat_letters");
                }
            } catch (Exception ex) {}
            Set<String> bookedSeats = new HashSet<>();
            try {
                java.sql.Connection conn = dao.DBConnection.getConnection();
                java.sql.PreparedStatement ps = conn.prepareStatement("SELECT seat_number FROM bookings WHERE flight_id = ?");
                ps.setInt(1, flight.getId());
                java.sql.ResultSet rs = ps.executeQuery();
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

        JButton bookBtn = new JButton("Book");
        add(new JLabel());
        add(bookBtn);

        bookBtn.addActionListener(e -> {
            FlightDTO selectedFlight = (FlightDTO) flightCombo.getSelectedItem();
            String seat = selectedSeat[0];
            String serviceClass = (String) classCombo.getSelectedItem();
            if (selectedFlight == null || seat == null || serviceClass == null) {
                JOptionPane.showMessageDialog(this, "Please select a flight, seat, and class.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BookingDTO booking = new BookingDTO(0, user.getId(), selectedFlight.getId(), seat, new Date(), serviceClass);
            boolean success = bookingService.addBooking(booking);
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking successful!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Booking failed. The seat may have just been booked by someone else.", "Error", JOptionPane.ERROR_MESSAGE);
                updateSeats.run();
            }
        });
    }
} 